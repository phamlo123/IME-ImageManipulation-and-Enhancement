package controller;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import javax.print.attribute.standard.MediaSize.ISO;
import model.ImageRepresentation.multiLayers.MultiLayeredImages;
import model.ImageRepresentation.multiLayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the SimpleImageController class and all of its methods function
 * correctly
 */
public class SimpleImageControllerTest {

  /**
   * invalid Appendable class used to test exceptions thrown from render methods.
   */
  public static class InvalidAppendable implements Appendable {

    @Override
    public Appendable append(CharSequence csq) throws IOException {
      throw new IOException();
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
      throw new IOException();
    }

    @Override
    public Appendable append(char c) throws IOException {
      throw new IOException();
    }
  }

  MultiLayers model;
  Appendable appendable;

  InvalidAppendable invalidAppendable;

  MultiLayers mockModel;
  StringBuilder log;

  // initialize examples
  @Before
  public void setup() {
    model = new MultiLayeredImages();
    appendable = new StringBuilder();
    invalidAppendable = new InvalidAppendable();
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImages(log);
  }

  // constructor tests

  // throws an exception because the model passed in is null
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorModelNull() {
    new SimpleImageController(null, new StringReader("end"), appendable);
  }

  // throws an exception because the Readable passed in is null
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorReadableNull() {
    new SimpleImageController(model, null, appendable);
  }

  // throws an exception because the Appendable passed in is null
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAppendableNull() {
    new SimpleImageController(model, new StringReader("end"), null);
  }

  // processInteractive tests

  // throws an exception because the appendable has an error appending (using a custom faulty
  // appendable)
  @Test(expected = IllegalStateException.class)
  public void testProcessInteractiveInvalidAppendable() {
    new SimpleImageController(model, new StringReader("blur end"), invalidAppendable)
        .processInteractive();
  }

  // processFile tests

  // throws an exception because the file given is null
  @Test(expected = IllegalArgumentException.class)
  public void testProcessFileNull() throws FileNotFoundException {
    new SimpleImageController(model, new StringReader("blur end"), appendable).processFile(null);
  }

  // throws an exception because the appendable has an error appending (using a custom faulty
  // appendable)
  @Test(expected = IllegalStateException.class)
  public void testProcessFileInvalidAppendable() throws FileNotFoundException {
    new SimpleImageController(model, new StringReader("blur end"), invalidAppendable)
        .processFile(new File("Command.txt"));
  }

  // throws an exception because the file cannot be found
  @Test(expected = FileNotFoundException.class)
  public void testProcessFileNoFile() throws FileNotFoundException {
    new SimpleImageController(model, new StringReader("blur end"), appendable)
        .processFile(new File("doesNotExist.txt"));
  }
}
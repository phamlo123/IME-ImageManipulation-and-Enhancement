package controller;

import static org.junit.Assert.*;

import java.io.StringReader;
import model.ImageRepresentation.multiLayers.MultiLayeredImages;
import model.ImageRepresentation.multiLayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the SimpleImageController class and all of its methods
 * function correctly
 */
public class SimpleImageControllerTest {

  MultiLayers model;
  Appendable appendable;

  // initialize examples
  @Before
  public void setup() {
    model = new MultiLayeredImages();
    appendable = new StringBuilder();
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
}
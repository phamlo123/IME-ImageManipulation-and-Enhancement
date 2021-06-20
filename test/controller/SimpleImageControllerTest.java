package controller;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import model.ImageRepresentation.Image;
import model.ImageRepresentation.multiLayers.MultiLayeredImagesOperations;
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

  ControllerExamples examples;

  MultiLayers model;
  Appendable appendable;

  InvalidAppendable invalidAppendable;

  MultiLayers mockModel;
  StringBuilder log;
  StringBuilder dontCareOutput;

  Readable dontCareInput;

  // initialize examples
  @Before
  public void setup() {
    examples = new ControllerExamples();
    model = new MultiLayeredImagesOperations(
        new ArrayList<>(Arrays.asList(new Image("Koala.ppm"),
            new Image("sample.ppm"), new Image("abc.jpg"))));
    appendable = new StringBuilder();
    invalidAppendable = new InvalidAppendable();
    log = new StringBuilder();
    dontCareOutput = new StringBuilder();
    mockModel = new MockMultiLayeredImagesOperations(log);
    dontCareInput = new StringReader("");
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

  // tests that the controller asks for a valid command when an invalid command is given (with
  // end to stop the program)
  @Test
  public void testProcessInteractiveInvalidCommand() {
    new SimpleImageController(model, new StringReader("invalid end"), appendable)
        .processInteractive();
    assertEquals(examples.toString1, appendable.toString());
  }

  // tests that the controller asks for a valid number when something other than a number
  // is given for a command that requires a number (with number to avoid exception and
  // end to stop the program)
  @Test
  public void testProcessInteractiveInvalidNumber() {
    new SimpleImageController(model, new StringReader("current notNumber 2 end"), appendable)
        .processInteractive();
    assertEquals(examples.toString2, appendable.toString());
  }

  // tests that the controller asks for a valid file format when an invalid format is given for a
  // command that requires a file format (with valid format to avoid exception and
  // end to stop the program)
  @Test
  public void testProcessInteractiveInvalidFileFormat() {
    new SimpleImageController(model, new StringReader("save hello notFormat png end"), appendable)
        .processInteractive();
    assertEquals(examples.toString3, appendable.toString());
  }

  // tests that the controller displays the error message given by the model and asks for another
  // command when the types of input given to the controller are correct, but are unreasonable to
  // use for the model (with end to stop the program)
  @Test
  public void testProcessInteractiveInvalidModelParameters() {
    new SimpleImageController(model, new StringReader("remove 5 end"), appendable)
        .processInteractive();
    assertEquals(examples.toString7, appendable.toString());
  }

  // mock tests for processInteractive

  // tests that the model receives the correct inputs for a valid blur command (with end to stop
  // the program)
  @Test
  public void testProcessInteractiveBlur() {
    new SimpleImageController(mockModel, new StringReader("blur end"), dontCareOutput)
        .processInteractive();
    assertEquals("blurringImage called.", log.toString());
  }

  // tests that the model receives the correct inputs for a valid sharpen command (with end to stop
  // the program)
  @Test
  public void testProcessInteractiveSharpen() {
    new SimpleImageController(mockModel, new StringReader("sharpen end"), dontCareOutput)
        .processInteractive();
    assertEquals("sharpeningImage called.", log.toString());
  }

  // tests that the model receives the correct inputs for a valid gray command (with end to stop
  // the program)
  @Test
  public void testProcessInteractiveGray() {
    new SimpleImageController(mockModel, new StringReader("gray end"), dontCareOutput)
        .processInteractive();
    assertEquals("createMonochrome called.", log.toString());
  }

  // tests that the model receives the correct inputs for a valid sepia command (with end to stop
  // the program)
  @Test
  public void testProcessInteractiveSepia() {
    new SimpleImageController(mockModel, new StringReader("sepia end"), dontCareOutput)
        .processInteractive();
    assertEquals("createSepia called.", log.toString());
  }

  // tests that the model receives the correct inputs for a valid create command (with end to stop
  // the program)
  @Test
  public void testProcessInteractiveCreate() {
    new SimpleImageController(mockModel, new StringReader("create end"), dontCareOutput)
        .processInteractive();
    assertEquals("addLayer called.", log.toString());
  }

  // tests that the model receives the correct inputs for a valid remove command (with end to stop
  // the program)
  @Test
  public void testProcessInteractiveRemove() {
    new SimpleImageController(mockModel, new StringReader("remove 0 end"), dontCareOutput)
        .processInteractive();
    assertEquals("removeLayer called. layerIndex = 0", log.toString());
  }

  // tests that the model receives the correct inputs for a valid load command (with end to stop
  // the program)
  @Test
  public void testProcessInteractiveLoad() {
    new SimpleImageController(mockModel, new StringReader("load Northeastern.ppm 0 end"),
        dontCareOutput)
        .processInteractive();
    assertEquals("loadImages called. layerIndex = 0", log.toString());
  }

  // tests that the model receives the correct inputs for a valid save command (with end to stop
  // the program)
  @Test
  public void testProcessInteractiveSave() {
    new SimpleImageController(mockModel, new StringReader("save toSave png end"), dontCareOutput)
        .processInteractive();
    assertEquals("saveImages called. fileName = toSave, fileFormat = PNG", log.toString());
  }

  // tests that the model receives the correct inputs for a valid export command (with end to stop
  // the program)
  @Test
  public void testProcessInteractiveExport() {
    new SimpleImageController(mockModel, new StringReader("export toExport png end"),
        dontCareOutput)
        .processInteractive();
    assertEquals("exportAll called. baseName = toExport, fileFormat = PNG", log.toString());
  }

  // tests that the model receives the correct inputs for a valid visible command (with end to stop
  // the program)
  @Test
  public void testProcessInteractiveVisible() {
    new SimpleImageController(mockModel, new StringReader("visible 0 true end"), dontCareOutput)
        .processInteractive();
    assertEquals("setInvisibility called. layerIndex = 0, visible = true", log.toString());
  }

  // tests that the model receives the correct inputs for a valid current command (with end to stop
  // the program)
  @Test
  public void testProcessInteractiveCurrent() {
    new SimpleImageController(mockModel, new StringReader("current 0 end"), dontCareOutput)
        .processInteractive();
    assertEquals("setCurrent called. index = 0", log.toString());
  }

  // processFile tests

  // throws an exception because the file given is null
  @Test(expected = IllegalArgumentException.class)
  public void testProcessFileNull() throws FileNotFoundException {
    new SimpleImageController(model, dontCareInput, appendable).processFile(null);
  }

  // throws an exception because the appendable has an error appending (using a custom faulty
  // appendable)
  @Test(expected = IllegalStateException.class)
  public void testProcessFileInvalidAppendable() throws FileNotFoundException {
    new SimpleImageController(model, dontCareInput, invalidAppendable)
        .processFile(new File("InvalidCommand.txt"));
  }

  // throws an exception because the file cannot be found
  @Test(expected = FileNotFoundException.class)
  public void testProcessFileNoFile() throws FileNotFoundException {
    new SimpleImageController(model, dontCareInput, appendable)
        .processFile(new File("doesNotExist.txt"));
  }

  // tests that the controller asks for a valid command when an invalid command is given (with
  // end to stop the program)
  @Test
  public void testProcessFileInvalidCommand() throws FileNotFoundException {
    new SimpleImageController(model, dontCareInput, appendable)
        .processFile(new File("InvalidCommand.txt"));
    assertEquals(examples.toString4, appendable.toString());
  }

  // tests that the controller asks for a valid number when something other than a number
  // is given for a command that requires a number (with number to avoid exception and
  // end to stop the program)
  @Test
  public void testProcessFileInvalidNumber() throws FileNotFoundException {
    new SimpleImageController(model, dontCareInput, appendable)
        .processFile(new File("InvalidNumber.txt"));
    assertEquals(examples.toString5, appendable.toString());
  }

  // tests that the controller asks for a valid file format when an invalid format is given for a
  // command that requires a file format (with valid format to avoid exception and
  // end to stop the program)
  @Test
  public void testProcessFileInvalidFileFormat() throws FileNotFoundException {
    new SimpleImageController(model, dontCareInput, appendable)
        .processFile(new File("InvalidFormat.txt"));
    assertEquals(examples.toString6, appendable.toString());
  }

  // tests that the controller displays the error message given by the model and asks for another
  // command when the types of input given to the controller are correct, but are unreasonable to
  // use for the model (with end to stop the program)
  @Test
  public void testProcessFileInvalidModelParameters() throws FileNotFoundException {
    new SimpleImageController(model, dontCareInput, appendable)
        .processFile(new File("InvalidRemove.txt"));
    assertEquals(examples.toString8, appendable.toString());
  }

  // mock tests for processFile

  // tests that the model receives the correct inputs for a valid blur command (with end to stop
  // the program)
  @Test
  public void testProcessFileBlur() throws FileNotFoundException {
    new SimpleImageController(mockModel, dontCareInput, dontCareOutput)
        .processFile(new File("Blur.txt"));
    assertEquals("blurringImage called.", log.toString());
  }

  // tests that the model receives the correct inputs for a valid sharpen command (with end to stop
  // the program)
  @Test
  public void testProcessFileSharpen() throws FileNotFoundException {
    new SimpleImageController(mockModel, dontCareInput, dontCareOutput)
        .processFile(new File("Sharpen.txt"));
    assertEquals("sharpeningImage called.", log.toString());
  }

  // tests that the model receives the correct inputs for a valid gray command (with end to stop
  // the program)
  @Test
  public void testProcessFileGray() throws FileNotFoundException {
    new SimpleImageController(mockModel, dontCareInput, dontCareOutput)
        .processFile(new File("Gray.txt"));
    assertEquals("createMonochrome called.", log.toString());
  }

  // tests that the model receives the correct inputs for a valid sepia command (with end to stop
  // the program)
  @Test
  public void testProcessFileSepia() throws FileNotFoundException {
    new SimpleImageController(mockModel, dontCareInput, dontCareOutput)
        .processFile(new File("Sepia.txt"));
    assertEquals("createSepia called.", log.toString());
  }

  // tests that the model receives the correct inputs for a valid create command (with end to stop
  // the program)
  @Test
  public void testProcessFileCreate() throws FileNotFoundException {
    new SimpleImageController(mockModel, dontCareInput, dontCareOutput)
        .processFile(new File("Create.txt"));
    assertEquals("addLayer called.", log.toString());
  }

  // tests that the model receives the correct inputs for a valid remove command (with end to stop
  // the program)
  @Test
  public void testProcessFileRemove() throws FileNotFoundException {
    new SimpleImageController(mockModel, dontCareInput, dontCareOutput)
        .processFile(new File("Remove.txt"));
    assertEquals("removeLayer called. layerIndex = 0", log.toString());
  }

  // tests that the model receives the correct inputs for a valid load command (with end to stop
  // the program)
  @Test
  public void testProcessFileLoad() throws FileNotFoundException {
    new SimpleImageController(mockModel, dontCareInput, dontCareOutput)
        .processFile(new File("Load.txt"));
    assertEquals("loadImages called. layerIndex = 0", log.toString());
  }

  // tests that the model receives the correct inputs for a valid save command (with end to stop
  // the program)
  @Test
  public void testProcessFileSave() throws FileNotFoundException {
    new SimpleImageController(mockModel, dontCareInput, dontCareOutput)
        .processFile(new File("Save.txt"));
    assertEquals("saveImages called. fileName = toSave, fileFormat = PNG", log.toString());
  }

  // tests that the model receives the correct inputs for a valid export command (with end to stop
  // the program)
  @Test
  public void testProcessFileExport() throws FileNotFoundException {
    new SimpleImageController(mockModel, dontCareInput, dontCareOutput)
        .processFile(new File("Export.txt"));
    assertEquals("exportAll called. baseName = toExport, fileFormat = PNG", log.toString());
  }

  // tests that the model receives the correct inputs for a valid visible command (with end to stop
  // the program)
  @Test
  public void testProcessFileVisible() throws FileNotFoundException {
    new SimpleImageController(mockModel, dontCareInput, dontCareOutput)
        .processFile(new File("Visible.txt"));
    assertEquals("setInvisibility called. layerIndex = 0, visible = true", log.toString());
  }

  // tests that the model receives the correct inputs for a valid current command (with end to stop
  // the program)
  @Test
  public void testProcessFileCurrent() throws FileNotFoundException {
    new SimpleImageController(mockModel, dontCareInput, dontCareOutput)
        .processFile(new File("Current.txt"));
    assertEquals("setCurrent called. index = 0", log.toString());
  }
}
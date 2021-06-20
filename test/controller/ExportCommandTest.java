package controller;

import static org.junit.Assert.*;

import controller.command.ImageCommand;
import controller.command.ExportCommand;
import model.enumTypes.FileFormat;
import model.ImageRepresentation.multiLayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the ExportCommand class and all of its methods
 * function correctly
 */
public class ExportCommandTest {

  ImageCommand save;
  StringBuilder log;
  MultiLayers mockModel;

  @Before
  public void setup() {
    save = new ExportCommand("hello", FileFormat.PNG);
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImagesOperations(log);
  }

  // constructor tests

  // throws an exception because the given fileName is null
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFileNameNull() {
    new ExportCommand(null, FileFormat.PNG);
  }

  // throws an exception because the given FileFormat is null
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFileFormatNull() {
    new ExportCommand("hello", null);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    save.go(null);
  }

  // tests the normal functionality of the SaveCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    save.go(mockModel);
    assertEquals("saveImages called. fileName = hello, fileFormat = PNG",log.toString());
  }

}
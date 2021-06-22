package controller;

import static org.junit.Assert.assertEquals;

import controller.command.ExportAllCommand;
import controller.command.ImageCommand;
import model.imagerepresentation.multilayers.MultiLayers;
import model.enums.FileFormat;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the ExportAllCommand class and all of its methods function correctly.
 */
public class ExportAllCommandTest {

  ImageCommand export;
  StringBuilder log;
  MultiLayers mockModel;

  @Before
  public void setup() {
    export = new ExportAllCommand("hello", FileFormat.PPM);
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImagesOperations(log);
  }

  // constructor test

  // throws an exception because the given String is null
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNull() {
    new ExportAllCommand(null, FileFormat.PPM);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    export.execute(null);
  }

  // tests the normal functionality of the ExportAllCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    export.execute(mockModel);
    assertEquals("exportAll called. baseName = hello, fileFormat = PPM", log.toString());
  }
}
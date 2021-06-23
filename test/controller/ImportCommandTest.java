package controller;

import static org.junit.Assert.assertEquals;

import controller.command.ImageCommand;
import controller.command.ImportCommand;
import model.ImageExamples;
import model.imagerepresentation.Image;
import model.imagerepresentation.multilayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the ImportCommand class and all of its methods function correctly.
 */
public class ImportCommandTest {

  ImageCommand load;
  StringBuilder log;
  MultiLayers mockModel;

  @Before
  public void setup() {
    load = new ImportCommand(new Image(ImageExamples.checkerboard()), 0);
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImagesOperations(log);
  }

  // constructor test

  // throws an exception because the ImageFormat given is null

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNull() {
    new ImportCommand(null,0);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    load.execute(null);
  }

  // tests the normal functionality of the LoadCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    load.execute(mockModel);
    assertEquals("loadImages called. layerIndex = 0", log.toString());
  }
}
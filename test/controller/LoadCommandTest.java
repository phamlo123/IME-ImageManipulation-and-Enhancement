package controller;

import static org.junit.Assert.*;

import controller.command.ImageCommand;
import controller.command.LoadCommand;
import model.ImageExamples;
import model.ImageRepresentation.Image;
import model.ImageRepresentation.multiLayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the LoadCommand class and all of its methods function correctly
 */
public class LoadCommandTest {

  ImageCommand load;
  StringBuilder log;
  MultiLayers mockModel;

  @Before
  public void setup() {
    load = new LoadCommand(new Image(ImageExamples.checkerboard()), 0);
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImagesOperations(log);
  }

  // constructor test

  // throws an exception because the ImageFormat given is null

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNull() {
    new LoadCommand(null,0);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    load.go(null);
  }

  // tests the normal functionality of the LoadCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    load.go(mockModel);
    assertEquals("loadImages called. layerIndex = 0", log.toString());
  }
}
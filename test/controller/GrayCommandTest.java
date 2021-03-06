package controller;

import static org.junit.Assert.assertEquals;

import controller.command.GrayCommand;
import controller.command.ImageCommand;
import model.imagerepresentation.multilayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the GrayCommand class and all of its methods
 * function correctly.
 */
public class GrayCommandTest {

  ImageCommand gray;
  StringBuilder log;
  MultiLayers mockModel;

  @Before
  public void setup() {
    gray = new GrayCommand();
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImagesOperations(log);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    gray.execute(null);
  }

  // tests the normal functionality of the GrayCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    gray.execute(mockModel);
    assertEquals("createMonochrome called.",log.toString());
  }
}
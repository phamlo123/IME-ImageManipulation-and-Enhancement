package controller;

import static org.junit.Assert.assertEquals;

import controller.command.ImageCommand;
import controller.command.SharpenCommand;
import model.imagerepresentation.multilayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the SharpenCommand class and all of its methods
 * function correctly.
 */
public class SharpenCommandTest {

  ImageCommand sharpen;
  StringBuilder log;
  MultiLayers mockModel;

  @Before
  public void setup() {
    sharpen = new SharpenCommand();
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImagesOperations(log);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    sharpen.execute(null);
  }

  // tests the normal functionality of the SharpenCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    sharpen.execute(mockModel);
    assertEquals("sharpeningImage called.",log.toString());
  }
}
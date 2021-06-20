package controller;

import static org.junit.Assert.*;

import controller.command.ImageCommand;
import controller.command.SharpenCommand;
import model.ImageRepresentation.multiLayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the SharpenCommand class and all of its methods
 * function correctly
 */
public class SharpenCommandTest {

  ImageCommand sharpen;
  StringBuilder log;
  MultiLayers mockModel;

  @Before
  public void setup() {
    sharpen = new SharpenCommand();
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImages(log);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    sharpen.go(null);
  }

  // tests the normal functionality of the SharpenCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    sharpen.go(mockModel);
    assertEquals("sharpeningImage called.",log.toString());
  }
}
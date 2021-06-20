package controller;

import static org.junit.Assert.*;

import controller.command.ImageCommand;
import controller.command.SepiaCommand;
import model.ImageRepresentation.multiLayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the SepiaCommand class and all of its methods
 * function correctly
 */
public class SepiaCommandTest {

  ImageCommand sepia;
  StringBuilder log;
  MultiLayers mockModel;

  @Before
  public void setup() {
    sepia = new SepiaCommand();
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImages(log);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    sepia.go(null);
  }

  // tests the normal functionality of the SepiaCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    sepia.go(mockModel);
    assertEquals("createSepia called.",log.toString());
  }
}
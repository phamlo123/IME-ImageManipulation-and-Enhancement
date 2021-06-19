package controller;

import static org.junit.Assert.*;

import model.ImageRepresentation.multiLayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the CurrentCommand class and all of its methods
 * function correctly
 */
public class CurrentCommandTest {

  ImageCommand current;
  StringBuilder log;
  MultiLayers mockModel;

  @Before
  public void setup() {
    current = new CurrentCommand(1);
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImages(log);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    current.go(null);
  }

  // tests the normal functionality of the CurrentCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    current.go(mockModel);
    assertEquals("setCurrent called. index = 1",log.toString());
  }
}
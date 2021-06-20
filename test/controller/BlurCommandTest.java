package controller;

import static org.junit.Assert.*;

import controller.command.BlurCommand;
import controller.command.ImageCommand;
import model.ImageRepresentation.multiLayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the BlurCommand class and all of its methods function correctly
 */
public class BlurCommandTest {

  ImageCommand blur;
  StringBuilder log;
  MultiLayers mockModel;

  @Before
  public void setup() {
    blur = new BlurCommand();
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImagesOperations(log);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    blur.go(null);
  }

  // tests the normal functionality of the BlurCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    blur.go(mockModel);
    assertEquals("blurringImage called.",log.toString());
  }
}
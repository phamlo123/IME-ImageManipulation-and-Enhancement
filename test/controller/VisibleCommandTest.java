package controller;

import static org.junit.Assert.assertEquals;

import controller.command.ImageCommand;
import controller.command.VisibleCommand;
import model.imagerepresentation.multilayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the VisibleCommand class and all of its methods
 * function correctly.
 */
public class VisibleCommandTest {

  ImageCommand visible;
  StringBuilder log;
  MultiLayers mockModel;

  @Before
  public void setup() {
    visible = new VisibleCommand(0,true);
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImagesOperations(log);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    visible.execute(null);
  }

  // tests the normal functionality of the VisibleCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    visible.execute(mockModel);
    assertEquals("setInvisibility called. layerIndex = 0, visible = true",log.toString());
  }
}
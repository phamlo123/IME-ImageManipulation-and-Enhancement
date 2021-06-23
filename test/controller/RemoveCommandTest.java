package controller;

import static org.junit.Assert.assertEquals;

import controller.command.ImageCommand;
import controller.command.RemoveCommand;
import model.imagerepresentation.multilayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the RemoveCommand class and all of its methods
 * function correctly.
 */
public class RemoveCommandTest {

  ImageCommand remove;
  StringBuilder log;
  MultiLayers mockModel;

  @Before
  public void setup() {
    remove = new RemoveCommand(0);
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImagesOperations(log);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    remove.execute(null);
  }

  // tests the normal functionality of the RemoveCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    remove.execute(mockModel);
    assertEquals("removeLayer called. layerIndex = 0",log.toString());
  }
}
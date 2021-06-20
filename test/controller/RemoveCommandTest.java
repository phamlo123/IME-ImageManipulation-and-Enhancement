package controller;

import static org.junit.Assert.*;

import controller.command.ImageCommand;
import controller.command.RemoveCommand;
import model.ImageRepresentation.multiLayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the RemoveCommand class and all of its methods
 * function correctly
 */
public class RemoveCommandTest {

  ImageCommand remove;
  StringBuilder log;
  MultiLayers mockModel;

  @Before
  public void setup() {
    remove = new RemoveCommand(0);
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImages(log);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    remove.go(null);
  }

  // tests the normal functionality of the RemoveCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    remove.go(mockModel);
    assertEquals("removeLayer called. layerIndex = 0",log.toString());
  }
}
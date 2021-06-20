package controller;

import static org.junit.Assert.*;

import controller.command.CreateCommand;
import controller.command.ImageCommand;
import model.ImageRepresentation.multiLayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the CreateCommand class and all of its methods
 * function correctly
 */
public class CreateCommandTest {

  ImageCommand create;
  StringBuilder log;
  MultiLayers mockModel;

  @Before
  public void setup() {
    create = new CreateCommand();
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImagesOperations(log);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    create.go(null);
  }

  // tests the normal functionality of the CreateCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    create.go(mockModel);
    assertEquals("addLayer called.",log.toString());
  }
}
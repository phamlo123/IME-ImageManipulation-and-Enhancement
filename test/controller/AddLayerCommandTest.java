package controller;

import static org.junit.Assert.assertEquals;

import controller.command.AddLayerCommand;
import controller.command.ImageCommand;
import model.imagerepresentation.multilayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the AddLayerCommand class and all of its methods function correctly.
 */
public class AddLayerCommandTest {

  ImageCommand create;
  StringBuilder log;
  MultiLayers mockModel;

  @Before
  public void setup() {
    create = new AddLayerCommand();
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImagesOperations(log);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    create.execute(null);
  }

  // tests the normal functionality of the CreateCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    create.execute(mockModel);
    assertEquals("addLayer called.", log.toString());
  }
}
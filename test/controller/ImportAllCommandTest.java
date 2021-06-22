package controller;

import static org.junit.Assert.assertEquals;

import controller.command.ImportAllCommand;
import model.imagerepresentation.multilayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the ImportAllCommand class and all of its methods function correctly.
 */
public class ImportAllCommandTest {

  ImportAllCommand importAll;
  StringBuilder log;
  MultiLayers mockModel;

  @Before
  public void setup() {
    importAll = new ImportAllCommand("hello");
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImagesOperations(log);
  }

  // constructor test

  // throws an exception because the ImageFormat given is null

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNull() {
    new ImportAllCommand(null);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    importAll.execute(null);
  }

  // tests the normal functionality of the LoadCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    importAll.execute(mockModel);
    assertEquals("importAll called. textFile = hello", log.toString());
  }

}
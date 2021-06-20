package controller;

import static org.junit.Assert.*;

import controller.command.ExportAllCommand;
import controller.command.ImageCommand;
import model.ImageRepresentation.multiLayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the ExportAllCommand class and all of its methods
 * function correctly
 */
public class ExportAllCommandTest {

  ImageCommand export;
  StringBuilder log;
  MultiLayers mockModel;

  @Before
  public void setup() {
    export = new ExportAllCommand("hello");
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImages(log);
  }

  // constructor test

  // throws an exception because the given String is null
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNull() {
    new ExportAllCommand(null);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    export.go(null);
  }

  // tests the normal functionality of the ExportAllCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    export.go(mockModel);
    assertEquals("exportAll called. baseName = hello",log.toString());
  }
}
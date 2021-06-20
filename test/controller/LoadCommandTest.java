package controller;

import static org.junit.Assert.*;

import controller.command.ImageCommand;
import controller.command.LoadCommand;
import model.ImageExamples;
import model.ImageRepresentation.Image;
import model.ImageRepresentation.multiLayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the LoadCommand class and all of its methods function correctly
 */
public class LoadCommandTest {

  ImageCommand load;
  StringBuilder log;
  MultiLayers mockModel;
  String toString;

  @Before
  public void setup() {
    load = new LoadCommand(new Image(ImageExamples.checkerboard()), 0);
    log = new StringBuilder();
    mockModel = new MockMultiLayeredImages(log);
    toString = "loadImages called. imageFormat = [[java.awt.Color[r=255,g=255,b=255], java.awt.Col"
        + "or[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.a"
        + "wt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b="
        + "255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt.Color[r="
        + "0,g=0,b=0]], [java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt."
        + "Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], jav"
        + "a.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255"
        + ",b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255]], [java.awt.Col"
        + "or[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], "
        + "java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,"
        + "b=0], java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r="
        + "255,g=255,b=255], java.awt.Color[r=0,g=0,b=0]], [java.awt.Color[r=0,g=0,b=0], java.awt."
        + "Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255"
        + "], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g"
        + "=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color"
        + "[r=255,g=255,b=255]], [java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], "
        + "java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g="
        + "255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt.Co"
        + "lor[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0]], [jav"
        + "a.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0"
        + "], java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255"
        + ",g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt"
        + ".Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255]], [java.awt.Color[r=255,g=255,b="
        + "255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt.Color[r="
        + "0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Co"
        + "lor[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255],"
        + " java.awt.Color[r=0,g=0,b=0]], [java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255"
        + ",b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt.Color"
        + "[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt"
        + ".Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=25"
        + "5]], [java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r="
        + "255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], java."
        + "awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0],"
        + " java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0]], [java.awt.Color[r=0,g"
        + "=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color"
        + "[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], ja"
        + "va.awt.Color[r=0,g=0,b=0], java.awt.Color[r=255,g=255,b=255], java.awt.Color[r=0,g=0,b="
        + "0], java.awt.Color[r=255,g=255,b=255]]], layerIndex = 0";
  }

  // constructor test

  // throws an exception because the ImageFormat given is null

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNull() {
    new LoadCommand(null,0);
  }

  // go tests

  // throws an exception because the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testGoNull() {
    load.go(null);
  }

  // tests the normal functionality of the LoadCommand go method, makes sure the model calls
  // the correct method
  @Test
  public void testGoNormal() {
    load.go(mockModel);
    assertEquals(toString, log.toString());
  }
}
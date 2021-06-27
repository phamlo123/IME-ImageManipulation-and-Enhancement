package controller;

import static org.junit.Assert.*;

import gui.SwingFeaturesFrame;
import java.util.ArrayList;
import java.util.Arrays;
import model.imagerepresentation.Image;
import model.imagerepresentation.multilayers.MultiLayeredImagesOperations;
import model.imagerepresentation.multilayers.MultiLayers;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the NewController class and all of its methods function
 *  * correctly.
 */
public class NewControllerTest {

  MultiLayers images;
  SwingFeaturesFrame view;
  StringBuilder log;
  MultiLayers mock;

  // initialize examples
  @Before
  public void setup() {
    images = new MultiLayeredImagesOperations(new ArrayList<>(
        Arrays.asList(new Image("Koala.jpg"), new Image("Jellyfish.jpg"))));
    view = new SwingFeaturesFrame();
    log = new StringBuilder();
    mock = new MockMultiLayeredImagesOperations(log);
  }

  // constructor tests

  // throws an exception if the model given is null
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorModelNull() {
    new NewController(null, view);
  }


  // throws an exception if the view given is null
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorViewNull() {
    new NewController(images, null);
  }

  // mock tests

  // tests that the model receives the correct inputs for a valid blur command
  @Test
  public void testActionPerformedBlur() {
    new NewController(mock, view)
        .actionHelper("Blur");
    assertEquals("blurringImage called.", log.toString());
  }

  // tests that the model receives the correct inputs for a valid sharpen command
  @Test
  public void testActionPerformedSharpen() {
    new NewController(mock, view)
        .actionHelper("Sharpen");
    assertEquals("sharpeningImage called.", log.toString());
  }

  // tests that the model receives the correct inputs for a valid gray command
  @Test
  public void testActionPerformedGray() {
    new NewController(mock, view)
        .actionHelper("Gray");
    assertEquals("createMonochrome called.", log.toString());
  }

  // tests that the model receives the correct inputs for a valid sepia command
  @Test
  public void testActionPerformedSepia() {
    new NewController(mock, view)
        .actionHelper("Sepia");
    assertEquals("createSepia called.", log.toString());
  }

  // tests that the model receives the correct inputs for a valid import command
  // (with Koala.jpg and 0 entered in the second window)
  @Test
  public void testActionPerformedImport() {
    new NewController(mock, view)
        .actionHelper("Import");
    assertEquals("loadImages called. layerIndex = 0", log.toString());
  }

  // tests that the model receives the correct inputs for a valid import all command
  // with (test.txt given in second window)
  @Test
  public void testActionPerformedImportAll() {
    new NewController(mock, view)
        .actionHelper("Import All");
    assertEquals("importAll called. textFile = test.txt", log.toString());
  }

  // tests that the model receives the correct inputs for a valid export command
  // (with test and jpg entered in the second window)
  @Test
  public void testActionPerformedExport() {
    new NewController(mock, view)
        .actionHelper("Export");
    assertEquals("saveImages called. fileName = test, fileFormat = JPEG", log.toString());
  }

  // tests that the model receives the correct inputs for a valid export all command
  // (with test and jpg entered in the second window)
  @Test
  public void testActionPerformedExportAll() {
    new NewController(mock, view)
        .actionHelper("Export All");
    assertEquals("exportAll called. baseName = test, fileFormat = JPEG", log.toString());
  }

  // tests that the model receives the correct inputs for a valid add layer command
  @Test
  public void testActionPerformedAddLayer() {
    new NewController(mock, view)
        .actionHelper("Add Layer");
    assertEquals("addLayer called.", log.toString());
  }

  // tests that the model receives the correct inputs for a valid remove layer command
  // (with 0 entered in the second window)
  @Test
  public void testActionPerformedRemoveLayer() {
    new NewController(mock, view)
        .actionHelper("Remove Layer");
    assertEquals("removeLayer called. layerIndex = 0", log.toString());
  }

  // tests that the model receives the correct inputs for a valid visible command
  // (with 0 entered in the second window)
  @Test
  public void testActionPerformedVisible() {
    new NewController(mock, view)
        .actionHelper("Visible");
    assertEquals("setInvisibility called. layerIndex = 0, visible = true", log.toString());
  }

  // tests that the model receives the correct inputs for a valid invisible command
  // (with 0 entered in the second window)
  @Test
  public void testActionPerformedInvisible() {
    new NewController(mock, view)
        .actionHelper("Invisible");
    assertEquals("setInvisibility called. layerIndex = 0, visible = false", log.toString());
  }

  // tests that the model receives the correct inputs for a valid set current command
  // (with 0 entered in the second window)
  @Test
  public void testActionPerformedSetCurrent() {
    new NewController(mock, view)
        .actionHelper("Set Current");
    assertEquals("setCurrent called. index = 0", log.toString());
  }

  // tests that the model receives the correct inputs for a valid mosaic command
  // (with 100 entered in the second window)
  @Test
  public void testActionPerformedMosaic() {
    new NewController(mock, view)
        .actionHelper("Mosaic");
    assertEquals("createMosaic called. numSeeds = 100", log.toString());
  }

  // tests that the model receives the correct inputs for a valid downsize command
  // (with 0.5 and 0.5 entered in the second window)
  @Test
  public void testActionPerformedDownsize() {
    new NewController(mock, view)
        .actionHelper("Downsize");
    assertEquals("downSize called. ratioWidth = 0.5, ratioHeight = 0.5", log.toString());
  }
}
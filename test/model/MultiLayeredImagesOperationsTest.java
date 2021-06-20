package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.ImageRepresentation.Image;
import model.ImageRepresentation.ImageFormat;
import model.ImageRepresentation.ImagesOperationsImpl;
import model.ImageRepresentation.multiLayers.MultiLayeredImagesOperations;
import model.ImageRepresentation.multiLayers.MultiLayers;
import model.enumTypes.FileFormat;
import org.junit.Test;

public class MultiLayeredImagesOperationsTest {

  Image northeastern = new Image("Northeastern.ppm");
  Image abc = new Image("abc.jpg");
  Image dragon = new Image("dragon.png");

  List<ImageFormat> listOfImage = new ArrayList<>(Arrays.asList(northeastern, abc, dragon));

  MultiLayers multiLayers = new MultiLayeredImagesOperations(listOfImage);

  @Test
  public void testGetCurrentIndex() {

    MultiLayers multiLayers = new MultiLayeredImagesOperations(listOfImage);

    assertEquals(2, multiLayers.getCurrentLayerIndex());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullConstructor1() {
    MultiLayers multiLayers = new MultiLayeredImagesOperations(
        new ArrayList<>(Arrays.asList(null, new Image("Koala.ppm"))));
  }

  @Test
  public void testGetCurrentIndex2() {
    multiLayers.setCurrent(1);
    assertEquals(1, multiLayers.getCurrentLayerIndex());
  }

  @Test
  public void testSaveImages() {
    MultiLayers multiLayers = new MultiLayeredImagesOperations(new ArrayList<>(Arrays.asList(abc)));
    multiLayers.saveImages("ForTesting", FileFormat.PNG);
    Image image = new Image("ForTesting.png");
    assertEquals(image, abc);
  }

  @Test
  public void testSaveImagesPass() {
    MultiLayers multiLayers = new MultiLayeredImagesOperations(new ArrayList<>(Arrays.asList(abc)));
    multiLayers.saveImages("ForTesting.ppm", FileFormat.PPM);
    Image image = new Image("ForTesting.ppm");
    assertEquals(image, abc);
  }


  @Test
  public void testLoadImages() {
    MultiLayers multiLayers = new MultiLayeredImagesOperations();
    multiLayers.addLayer();
    multiLayers.addLayer();
    multiLayers.loadImages(abc, 0);
    multiLayers.setCurrent(0);
    assertEquals(abc, multiLayers.getLayer(0));
  }

  @Test
  public void testAddLayer() {
    MultiLayers multiLayers2 = new MultiLayeredImagesOperations();
    assertEquals(0, multiLayers2.getNumLayers());
    multiLayers2.addLayer();
    multiLayers2.addLayer();
    assertEquals(2, multiLayers2.getNumLayers());
    multiLayers.addLayer();
    assertEquals(4, multiLayers.getNumLayers());
  }

  @Test
  public void testRemoveLayer() {
    assertEquals(3, multiLayers.getNumLayers());
    assertEquals(northeastern, multiLayers.getLayer(0));
    multiLayers.removeLayer(0);
    assertEquals(2, multiLayers.getNumLayers());
    assertEquals(abc, multiLayers.getLayer(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveLayerCurrent() {
    multiLayers.removeLayer(2);
  }

  @Test
  public void testSetInvisibility() {
    assertTrue(multiLayers.isVisible(0));
    assertTrue(multiLayers.isVisible(1));
    assertTrue(multiLayers.isVisible(2));
    multiLayers.setInvisibility(0, false);
    assertFalse(multiLayers.isVisible(0));
    multiLayers.setInvisibility(1, false);
    assertFalse(multiLayers.isVisible(1));
    multiLayers.setInvisibility(2, false);
    assertFalse(multiLayers.isVisible(2));

    multiLayers.setInvisibility(0, true);
    assertTrue(multiLayers.isVisible(0));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetInvisibilityOutofBounds() {
    multiLayers.setInvisibility(11, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetInvisibilityOutofBounds2() {
    multiLayers.setInvisibility(-1, false);
  }

  @Test
  public void testSetCurrent() {
    assertEquals(2, multiLayers.getCurrentLayerIndex());
    multiLayers.setCurrent(1);
    assertEquals(1, multiLayers.getCurrentLayerIndex());
  }

  @Test
  public void testgetCurrentLayerIndex() {
    assertEquals(2, multiLayers.getCurrentLayerIndex());
    multiLayers.setCurrent(0);
    assertEquals(0, multiLayers.getCurrentLayerIndex());
  }

  @Test
  public void testBlurringImage() {
    multiLayers.blurringImage();
    ImagesOperations imagesOperations = new ImagesOperationsImpl(dragon);
    imagesOperations.blurringImage();
    ImageFormat image = imagesOperations.getImage();
    assertEquals(image, multiLayers.getLayer(multiLayers.getCurrentLayerIndex()));
  }


  @Test
  public void testBlurringImageMultiple() {
    multiLayers.blurringImage();
    multiLayers.blurringImage();
    ImagesOperations imagesOperations = new ImagesOperationsImpl(dragon);
    imagesOperations.blurringImage();
    imagesOperations.blurringImage();
    ImageFormat image = imagesOperations.getImage();
    assertEquals(image, multiLayers.getLayer(multiLayers.getCurrentLayerIndex()));
  }

  @Test
  public void testSharpeningImage() {
    multiLayers.setCurrent(1);
    multiLayers.sharpeningImage();
    ImagesOperations imagesOperations = new ImagesOperationsImpl(abc);
    imagesOperations.sharpeningImage();
    ImageFormat image = imagesOperations.getImage();
    assertEquals(image, multiLayers.getLayer(multiLayers.getCurrentLayerIndex()));
  }

  @Test
  public void testCreateMonochrome() {
    multiLayers.createMonochrome();
    ImagesOperations imagesOperations = new ImagesOperationsImpl(dragon);
    imagesOperations.createMonochrome();
    ImageFormat image = imagesOperations.getImage();
    assertEquals(image, multiLayers.getLayer(multiLayers.getCurrentLayerIndex()));
  }

  @Test
  public void testCreateSepia() {
    multiLayers.createSepia();
    ImagesOperations imagesOperations = new ImagesOperationsImpl(dragon);
    imagesOperations.createSepia();
    ImageFormat image = imagesOperations.getImage();
    assertEquals(image, multiLayers.getLayer(multiLayers.getCurrentLayerIndex()));
  }

  @Test
  public void testDoMultipleLayers() {
    multiLayers.createSepia();
    multiLayers.setCurrent(1);
    multiLayers.createMonochrome();
    multiLayers.setCurrent(0);
    multiLayers.blurringImage();
    ImagesOperations imagesOperations = new ImagesOperationsImpl(dragon);
    imagesOperations.createSepia();
    ImagesOperations imagesOperations2 = new ImagesOperationsImpl(abc);
    imagesOperations2.createMonochrome();
    ImagesOperations imagesOperations3 = new ImagesOperationsImpl(northeastern);
    imagesOperations3.blurringImage();
    assertEquals(imagesOperations.getImage(), multiLayers.getLayer(2));
    assertEquals(imagesOperations2.getImage(), multiLayers.getLayer(1));
    assertEquals(imagesOperations3.getImage(), multiLayers.getLayer(0));
  }

  @Test
  public void testDoMultipleLayers2() {
    multiLayers.createSepia();
    multiLayers.setCurrent(1);
    multiLayers.createMonochrome();
    multiLayers.setCurrent(0);
    multiLayers.sharpeningImage();
    ImagesOperations imagesOperations = new ImagesOperationsImpl(dragon);
    imagesOperations.createSepia();
    ImagesOperations imagesOperations2 = new ImagesOperationsImpl(abc);
    imagesOperations2.createMonochrome();
    ImagesOperations imagesOperations3 = new ImagesOperationsImpl(northeastern);
    imagesOperations3.sharpeningImage();
    assertEquals(imagesOperations.getImage(), multiLayers.getLayer(2));
    assertEquals(imagesOperations2.getImage(), multiLayers.getLayer(1));
    assertEquals(imagesOperations3.getImage(), multiLayers.getLayer(0));
  }

  @Test
  public void testGetImage() {
    assertEquals(dragon, multiLayers.getImage());
  }

  @Test
  public void testExportAll() {
    MultiLayers multiLayers = new MultiLayeredImagesOperations(listOfImage);
    multiLayers.exportAll("cdf", FileFormat.PNG);
    MultiLayers multiLayers1 = new MultiLayeredImagesOperations("cdf.txt");
    assertEquals(3, multiLayers1.getNumLayers());
    assertEquals(northeastern, multiLayers1.getLayer(0));
    assertEquals(abc, multiLayers1.getLayer(1));
    assertEquals(dragon, multiLayers1.getLayer(2));
  }


  @Test
  public void testLoadingMultiLayer() {
    MultiLayers multiLayers = new MultiLayeredImagesOperations(listOfImage);
    multiLayers.exportAll("cdf", FileFormat.PPM);
    MultiLayers multiLayers1 = new MultiLayeredImagesOperations("cdf.txt");
    assertEquals(3, multiLayers1.getNumLayers());
    assertEquals(northeastern, multiLayers1.getLayer(0));
    assertEquals(abc, multiLayers1.getLayer(1));
    assertEquals(dragon, multiLayers1.getLayer(2));
  }

  @Test
  public void testGetLayer() {
    assertEquals(abc, multiLayers.getLayer(1));
    assertEquals(northeastern, multiLayers.getLayer(0));
    assertEquals(dragon, multiLayers.getLayer(2));
  }


  @Test(expected = IllegalArgumentException.class)
  public void testGetLayerIllegal() {
   multiLayers.getLayer(11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetLayerIllegal2() {
    multiLayers.getLayer(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetEmptyLayer() {
    multiLayers.addLayer();
    multiLayers.getLayer(4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetEmptyLayer2() {
    multiLayers.removeLayer(2);
    multiLayers.getLayer(2);
  }

  @Test
  public void testImportAll() {
    MultiLayers m = new MultiLayeredImagesOperations(listOfImage);
    m.exportAll("ForTest", FileFormat.PPM);
    MultiLayers m1 = new MultiLayeredImagesOperations();
    m1.importAll("ForTest.txt");
    assertEquals(3, m1.getNumLayers());
    assertEquals(m1.getLayer(0), m.getLayer(0));
    assertEquals(m1.getLayer(1), m.getLayer(1));
    assertEquals(m1.getLayer(2), m.getLayer(2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImportNonExistent() {
    MultiLayers m = new MultiLayeredImagesOperations();
    m.importAll("something.txt");
  }







}
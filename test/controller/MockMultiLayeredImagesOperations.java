package controller;

import java.awt.image.BufferedImage;
import model.enums.FileFormat;
import model.imagerepresentation.ImageFormat;
import model.imagerepresentation.multilayers.MultiLayers;

/**
 * Mock class of MultiLayeredImages used to test the communication between the controller and
 * model.
 */
public class MockMultiLayeredImagesOperations implements MultiLayers {

  StringBuilder log;

  public MockMultiLayeredImagesOperations(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void saveImages(String fileName, FileFormat fileFormat) throws IllegalArgumentException {
    log.append("saveImages called. fileName = ").append(fileName).append(", fileFormat = ")
        .append(fileFormat);
  }

  @Override
  public void loadImages(ImageFormat imageFormat, int layerIndex) {
    log.append("loadImages called. layerIndex = ")
        .append(layerIndex);
  }

  @Override
  public void addLayer() {
    log.append("addLayer called.");
  }

  @Override
  public void removeLayer(int layerIndex) throws IllegalArgumentException {
    log.append("removeLayer called. layerIndex = ").append(layerIndex);
  }

  @Override
  public void setInvisibility(int layerIndex, boolean visible) throws IllegalArgumentException {
    log.append("setInvisibility called. layerIndex = ").append(layerIndex).append(", visible = ")
        .append(visible);
  }

  @Override
  public void setCurrent(int index) {
    log.append("setCurrent called. index = ").append(index);
  }


  @Override
  public int getCurrentLayerIndex() {
    return 0;
  }

  @Override
  public void exportAll(String baseName, FileFormat fileFormat) {
    log.append("exportAll called. baseName = ").append(baseName).append(", fileFormat = ")
        .append(fileFormat);
  }

  @Override
  public ImageFormat getLayer(int layerIndex) {
    return null;
  }

  @Override
  public int getNumLayers() {
    return 0;
  }

  @Override
  public boolean isVisible(int layerIndex) throws IllegalArgumentException {
    return false;
  }

  @Override
  public void importAll(String textFile) {
    log.append("importAll called. textFile = ").append(textFile);
  }

  @Override
  public BufferedImage getTopmost() throws IllegalArgumentException {
    return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
  }

  @Override
  public void blurringImage() {
    log.append("blurringImage called.");
  }

  @Override
  public void sharpeningImage() {
    log.append("sharpeningImage called.");
  }

  @Override
  public void createMonochrome() {
    log.append("createMonochrome called.");
  }

  @Override
  public void createSepia() {
    log.append("createSepia called.");
  }

  @Override
  public ImageFormat getImage() {
    return null;
  }

  @Override
  public void downSize(double ratioWidth, double ratioHeight) throws IllegalArgumentException {
    log.append("downSize called. ratioWidth = ").append(ratioWidth).append(", ratioHeight = ")
        .append(ratioHeight);
  }

  @Override
  public void createMosaic(int numSeeds) throws IllegalArgumentException {
    log.append("createMosaic called. numSeeds = ").append(numSeeds);
  }
}

package controller;

import model.enumTypes.FileFormat;
import model.ImageRepresentation.ImageFormat;
import model.ImageRepresentation.multiLayers.MultiLayers;

/**
 * Mock class of MultiLayeredImages used to test the communication between the controller and
 * model.
 */
public class MockMultiLayeredImages implements MultiLayers {

  StringBuilder log;

  public MockMultiLayeredImages(StringBuilder log) {
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
  public void exportAll(String baseName) {
    log.append("exportAll called. baseName = ").append(baseName);
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
}

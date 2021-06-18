package model.ImageRepresentation.multiLayers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import model.FileFormat;
import model.ImageRepresentation.Image;
import model.ImageRepresentation.ImageFormat;
import model.ImageRepresentation.ImageImplPPM;
import model.Images;

public class MultiLayeredImages implements MultiLayers, Images {

  private List<ImageFormat> imageLayers;
  private List<Boolean> listVisibility;
  private ImageFormat currentLayer;
  private Images imageOp;

  public MultiLayeredImages(List<ImageFormat> imageLayers) {
    Objects.requireNonNull(imageLayers);
    this.imageLayers = imageLayers;
    this.listVisibility = setUpVisibility(imageLayers);
    this.currentLayer = imageLayers.get(imageLayers.size() - 1);
    this.imageOp = new ImageImplPPM(currentLayer);
  }

  public MultiLayeredImages() {
    this.currentLayer = null;
    this.imageLayers = new ArrayList<>();
    this.listVisibility = new ArrayList<>();
    this.imageOp = null;
  }


  private List<Boolean> setUpVisibility(List<ImageFormat> imageLayers) {
    List<Boolean> listOfVisibility = new ArrayList<>();
    for (int i = 0; i < imageLayers.size(); i++) {
      if (imageLayers.get(i) != null) {
        listOfVisibility.add(true);
      } else {
        listOfVisibility.add(false);
      }
    }
    return listOfVisibility;
  }


  @Override
  public void saveImages(String fileName, FileFormat fileFormat) throws IllegalArgumentException {
    int size = imageLayers.size();
    for (int i = size - 1; i >= 0; i--) {
      if (listVisibility.get(i)) {
        ImageFormat image = imageLayers.get(i);
        image.getConverter().exportImage(fileName, fileFormat);
        return;
      }
    }
  }

  @Override
  public void loadImages(ImageFormat imageFormat, int layerIndex) {
    if (layerIndex < 0 || layerIndex >= this.imageLayers.size()) {
      throw new IllegalArgumentException("Invalid layer Index");
    }
    if (imageFormat == null) {
      throw new IllegalArgumentException("Provided image to be loaded is null");
    }
    imageLayers.set(layerIndex, imageFormat);
  }


  @Override
  public void addLayer() {
    this.imageLayers.add(null);
    this.listVisibility.add(null);
  }

  @Override
  public void removeLayer(int layerIndex) throws IllegalArgumentException {
    if (layerIndex < 0 || layerIndex >= this.imageLayers.size()) {
      throw new IllegalArgumentException("Invalid layer Index");
    }
    imageLayers.remove(layerIndex);
  }

  @Override
  public void setInvisibility(int layerIndex, boolean visible) throws IllegalArgumentException {
    if (layerIndex < 0 || layerIndex >= listVisibility.size()) {
      throw new IllegalArgumentException();
    } else {
      listVisibility.set(layerIndex, visible);
    }
  }


  @Override
  public void setCurrent(int index) throws IllegalArgumentException {
    if (index < 0 || index >= imageLayers.size()) {
      throw new IllegalArgumentException();
    }
    currentLayer = imageLayers.get(index);
  }

  @Override
  public ImageFormat getCurrentLayer() {
    return new Image(currentLayer.getImage());
  }

  @Override
  public int getCurrentLayerIndex() {
    return imageLayers.indexOf(currentLayer);
  }


  @Override
  public void blurringImage() {
    imageOp.blurringImage();
  }


  @Override
  public void sharpeningImage() {
    imageOp.sharpeningImage();
    currentLayer = imageOp.getImage();
  }


  @Override
  public void createMonochrome() {
    imageOp.createMonochrome();
    currentLayer = imageOp.getImage();
  }

  @Override
  public void createSepia() {
    imageOp.createSepia();
    currentLayer = imageOp.getImage();
  }


  @Override
  public ImageFormat getImage() {
    return imageOp.getImage();
  }

  public static void main(String[] args) {
    MultiLayers multiLayers = new MultiLayeredImages(new ArrayList<>(Arrays.asList(new Image("Koala.ppm"),
        new Image("sample.ppm"), new Image("abc.jpg"))));

    multiLayers.createSepia();

    multiLayers.getCurrentLayer().getConverter().exportImage("Tdig", FileFormat.PPM);


  }
}

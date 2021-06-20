package model.ImageRepresentation.multiLayers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import model.ImageRepresentation.ImagesOperationsImpl;
import model.ImagesOperations;
import model.enumTypes.FileFormat;
import model.ImageRepresentation.Image;
import model.ImageRepresentation.ImageFormat;

/**
 * This class implements the MultiLayers interface to provide mechanisms to represent multiple
 * layers, each can contain an individual image (ImageFormat). Filtering and Coloring operations can
 * also be performed on the image that is in the current layer
 */
public class MultiLayeredImagesOperations implements MultiLayers {

  private List<ImageFormat> imageLayers;
  private List<Boolean> listVisibility;
  private ImageFormat currentLayer;
  private ImagesOperations imageOp;

  /**
   * This create an instance of this class by assigning the list of images to the layers of this
   * object. And set the current layer to be the top most layer and all layers are set to be
   * visible.
   *
   * @param imageLayers is the list of images being passed in.
   * @throws IllegalArgumentException if the list of images is null or any of its element is null
   */
  public MultiLayeredImagesOperations(List<ImageFormat> imageLayers)
      throws IllegalArgumentException {
    if (imageLayers == null) {
      throw new IllegalArgumentException("List is null");
    }
    for (ImageFormat i : imageLayers) {
      if (i == null) {
        throw new IllegalArgumentException("image is null");
      }
    }
    this.imageLayers = imageLayers;
    this.listVisibility = setUpVisibility(imageLayers);
    this.currentLayer = imageLayers.get(imageLayers.size() - 1);
    this.imageOp = new ImagesOperationsImpl(currentLayer);
  }

  /**
   * Create an instance of this class with an empty list of layers and everything else being empty
   * or null.
   */
  public MultiLayeredImagesOperations() {
    this.currentLayer = null;
    this.imageLayers = new ArrayList<>();
    this.listVisibility = new ArrayList<>();
    this.imageOp = null;
  }

  public MultiLayeredImagesOperations(String textFile) throws IllegalArgumentException {
    if (textFile == null) {
      throw new IllegalArgumentException("text file name is null");
    } else {
      importAll(textFile);
    }
  }

  @Override
  public void importAll(String textFile) {
    if (textFile == null) {
      throw new IllegalArgumentException("text file name is null");
    }
    this.imageLayers = helperForProcessingTextFile(textFile);
    this.currentLayer = imageLayers.get(imageLayers.size() - 1);
    this.imageOp = new ImagesOperationsImpl(currentLayer);
    this.listVisibility = setUpVisibility(imageLayers);
  }

  private List<ImageFormat> helperForProcessingTextFile(String textFile)
      throws IllegalArgumentException {
    List<ImageFormat> listOfImages = new ArrayList<>();
    try {
      Scanner scan = new Scanner(new File(textFile));
      while (scan.hasNext()) {
        String path = scan.next();
        File f = new File(path);
        String s = f.getName();
        ImageFormat image = new Image(s);
        listOfImages.add(image);
      }
      return listOfImages;
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    }
  }


  /**
   * Helper method to help set up visibility status of the given list of images. All non-null images
   * are set to be visible by default, and non-visible otherwise.
   *
   * @param imageLayers is the given list of images
   * @return a list of booleans representing visibility of all the images in this list.
   */
  private List<Boolean> setUpVisibility(List<ImageFormat> imageLayers) {
    List<Boolean> listOfVisibility = new ArrayList<>();
    for (ImageFormat imageLayer : imageLayers) {
      if (imageLayer != null) {
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
      throw new IllegalArgumentException("Index " + layerIndex + " out of bounds");
    }
    if (imageFormat == null) {
      throw new IllegalArgumentException("Provided image to be loaded is null");
    }
    imageLayers.set(layerIndex, imageFormat);
    listVisibility.set(layerIndex, true);
  }


  @Override
  public void addLayer() {
    this.imageLayers.add(null);
    this.listVisibility.add(null);
  }

  @Override
  public void removeLayer(int layerIndex) throws IllegalArgumentException {
    if (layerIndex == getCurrentLayerIndex()) {
      throw new IllegalArgumentException("Cannot remove current layer, set new current layer "
          + "before removing this one");
    }
    if (layerIndex < 0 || layerIndex >= this.imageLayers.size()) {
      throw new IllegalArgumentException("Index " + layerIndex + " out of bounds");
    }
    imageLayers.remove(layerIndex);
  }

  @Override
  public void setInvisibility(int layerIndex, boolean visible) throws IllegalArgumentException {
    if (layerIndex < 0 || layerIndex >= listVisibility.size()) {
      throw new IllegalArgumentException("Index " + layerIndex + " out of bounds");
    } else {
      listVisibility.set(layerIndex, visible);
    }
  }


  @Override
  public void setCurrent(int index) throws IllegalArgumentException {
    if (index < 0 || index >= imageLayers.size()) {
      throw new IllegalArgumentException("Index " + index + " out of bounds");
    }
    currentLayer = imageLayers.get(index);
    imageOp = new ImagesOperationsImpl(currentLayer);
  }


  @Override
  public int getCurrentLayerIndex() {
    return imageLayers.indexOf(currentLayer);
  }


  @Override
  public void blurringImage() {
    imageOp.blurringImage();
    int index = getCurrentLayerIndex();
    currentLayer = imageOp.getImage();
    imageLayers.set(index, currentLayer);
  }


  @Override
  public void sharpeningImage() {
    imageOp.sharpeningImage();
    int index = getCurrentLayerIndex();
    currentLayer = imageOp.getImage();
    imageLayers.set(index, currentLayer);

  }


  @Override
  public void createMonochrome() {
    imageOp.createMonochrome();
    int index = getCurrentLayerIndex();
    currentLayer = imageOp.getImage();
    imageLayers.set(index, currentLayer);

  }

  @Override
  public void createSepia() {
    imageOp.createSepia();
    int index = getCurrentLayerIndex();
    currentLayer = imageOp.getImage();
    imageLayers.set(index, currentLayer);
  }

  @Override
  public ImageFormat getImage() {
    return imageOp.getImage();
  }


  @Override
  public void exportAll(String baseName, FileFormat fileFormat) throws IllegalArgumentException {
    String extension;
    switch (fileFormat) {
      case JPEG:
        extension = ".jpg";
        break;
      case PNG:
        extension = ".png";
        break;
      case PPM:
        extension = ".ppm";
        break;
      default:
        throw new IllegalArgumentException("Not supported");
    }
    int t = 0;
    StringBuilder stringBuilder = new StringBuilder();
    for (ImageFormat i : this.imageLayers) {
      t = t + 1;
      String name = baseName + "" + t + extension;
      stringBuilder.append(i.getConverter().exportWithPath(name, fileFormat));
      stringBuilder.append("\n");
    }
    try {
      FileWriter fileWriter = new FileWriter(baseName + ".txt");
      fileWriter.write(stringBuilder.toString());
      fileWriter.close();
    } catch (IOException e) {
      System.out.print(e.getMessage());
    }
  }

  @Override
  public ImageFormat getLayer(int layerIndex) throws IllegalArgumentException {
    if (layerIndex < 0 || layerIndex >= imageLayers.size()) {
      throw new IllegalArgumentException();
    }
    try {
      return new Image(this.imageLayers.get(layerIndex).getImage());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("there is no image in this layer");
    }
  }

  @Override
  public int getNumLayers() {
    return imageLayers.size();
  }


  @Override
  public boolean isVisible(int layerIndex) throws IllegalArgumentException {
    if (layerIndex < 0 || layerIndex >= imageLayers.size()) {
      throw new IllegalArgumentException("Invalid index");
    }
    return listVisibility.get(layerIndex);
  }


  public static void main(String[] args) {
    MultiLayers multiLayers = new MultiLayeredImagesOperations(
        new ArrayList<>(Arrays.asList(new Image("Koala.ppm"),
            new Image("sample.ppm"), new Image("abc.jpg"))));

    multiLayers.exportAll("TGB", FileFormat.JPEG);
    MultiLayers multiLayers1 = new MultiLayeredImagesOperations("TGB.txt");
    System.out.println(multiLayers1.getLayer(0));
  }
}

package model.ImageRepresentation.multiLayers;

import java.util.List;
import model.FileFormat;
import model.ImageRepresentation.ImageFormat;
import model.Images;

public interface MultiLayers extends Images {

  /**
   *  Export the top most and visible layer into a certain image format
   */
  public void saveImages(String fileName, FileFormat fileFormat) throws IllegalArgumentException;

  /**
   * Load given image into the current layer
   */
  void loadImages(ImageFormat imageFormat, int layerIndex);


  void addLayer();


  void removeLayer(int layerIndex) throws IllegalArgumentException;


  void setInvisibility(int layerIndex, boolean visible) throws IllegalArgumentException;

  void setCurrent(int index);

  ImageFormat getCurrentLayer();

  public int getCurrentLayerIndex();
}

package model.ImageRepresentation.multiLayers;

import model.enumTypes.FileFormat;
import model.ImageRepresentation.ImageFormat;
import model.Images;

/**
 * This interface extends the Images interface to include methods that can be applied to multiple
 * layers of image objects.
 */
public interface MultiLayers extends Images {

  /**
   * Export the top most and visible layer into a certain image format.
   */
  void saveImages(String fileName, FileFormat fileFormat) throws IllegalArgumentException;

  /**
   * Load the given image into a specified layer using its index.
   */
  void loadImages(ImageFormat imageFormat, int layerIndex);

  /**
   * Add an empty layer (represented as null) into this list of layers
   */
  void addLayer();

  /**
   * Remove the layer with the specified index from this multi-layers object, cannot remove current
   * layer.
   *
   * @param layerIndex is the index of the layer to be removed
   * @throws IllegalArgumentException if the index is out of bounds or if the layer to be removed is
   *                                  the current layer.
   */
  void removeLayer(int layerIndex) throws IllegalArgumentException;

  /**
   * Set the visibility status for a given layer.
   *
   * @param layerIndex is the layer to be set
   * @param visible    is the visible status of the layer
   * @throws IllegalArgumentException if the index is out of bounds.
   */
  void setInvisibility(int layerIndex, boolean visible) throws IllegalArgumentException;


  /**
   * Set the layer with the given index to be the current layer
   *
   * @param index index of the layer
   */
  void setCurrent(int index);

  /**
   * Get the layer index of the current layer.
   *
   * @return the index of the current layer.
   */
  int getCurrentLayerIndex();

  /**
   * Export all of the non-null images in the layers of this object. The exported file is a text
   * file that contains the locations of all the exported image files.
   *
   * @param baseName the base for the File names of the exported image files
   */
  void exportAll(String baseName);

  /**
   * Get the image in the given layer
   *
   * @param layerIndex the index of the given layer
   * @return the image of the given layer
   */
  ImageFormat getLayer(int layerIndex);

  /**
   * Get the number of layers in this object
   *
   * @return the number of layers
   */
  int getNumLayers();

  /**
   * Find out if the given layer is visible
   *
   * @param layerIndex is the index of the given layer
   * @return a boolean whether the given layer is visible
   * @throws IllegalArgumentException if the index is out of bounds
   */
  boolean isVisible(int layerIndex) throws IllegalArgumentException;

}

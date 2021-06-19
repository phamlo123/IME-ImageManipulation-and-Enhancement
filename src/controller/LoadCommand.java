package controller;

import java.util.Objects;
import model.ImageRepresentation.ImageFormat;
import model.ImageRepresentation.multiLayers.MultiLayers;

/**
 * Command class used for loading the images in the model.
 */
public class LoadCommand implements ImageCommand {

  private final ImageFormat imageFormat;
  private final int layerIndex;

  /**
   * Constructs a LoadCommand object with the current layer index and image as private fields.
   * @param imageFormat the image to be set at the given layer index
   * @param layerIndex  the layer index determining where to set the given image
   */
  public LoadCommand(ImageFormat imageFormat, int layerIndex) {
    Objects.requireNonNull(imageFormat);
    this.imageFormat = imageFormat;
    this.layerIndex = layerIndex;
  }

  @Override
  public void go(MultiLayers model) {
    Objects.requireNonNull(model);
    model.loadImages(imageFormat, layerIndex);
  }
}

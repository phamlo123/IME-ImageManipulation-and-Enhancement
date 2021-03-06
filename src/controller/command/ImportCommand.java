package controller.command;

import model.imagerepresentation.ImageFormat;
import model.imagerepresentation.multilayers.MultiLayers;
import model.util.ImageUtil;

/**
 * Command class used for importing an image into the model.
 */
public class ImportCommand implements ImageCommand {

  private final ImageFormat imageFormat;
  private final int layerIndex;

  /**
   * Constructs a LoadCommand object with the current layer index and image as private fields.
   *
   * @param imageFormat the image to be set at the given layer index
   * @param layerIndex  the layer index determining where to set the given image
   */
  public ImportCommand(ImageFormat imageFormat, int layerIndex) {
    ImageUtil.checkNull(imageFormat);
    this.imageFormat = imageFormat;
    this.layerIndex = layerIndex;
  }

  @Override
  public void execute(MultiLayers model) throws IllegalArgumentException {
    ImageUtil.checkNull(model);
    model.loadImages(imageFormat, layerIndex);
  }
}

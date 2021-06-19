package controller;

import java.util.Objects;
import model.ImageRepresentation.multiLayers.MultiLayers;

/**
 * Command class used for sharpening the images in the model.
 */
public class SharpenCommand implements ImageCommand {

  @Override
  public void go(MultiLayers model) {
    Objects.requireNonNull(model);
    model.sharpeningImage();
  }
}

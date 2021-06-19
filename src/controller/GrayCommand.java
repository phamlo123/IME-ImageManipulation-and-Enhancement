package controller;

import java.util.Objects;
import model.ImageRepresentation.multiLayers.MultiLayers;

/**
 * Command class used for making the images in the model monochrome.
 */
public class GrayCommand implements ImageCommand {

  @Override
  public void go(MultiLayers model) {
    Objects.requireNonNull(model);
    model.createMonochrome();
  }
}

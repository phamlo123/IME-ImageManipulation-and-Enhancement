package controller;

import model.ImageRepresentation.ImageFormat;
import model.ImageRepresentation.multiLayers.MultiLayers;

public class LoadCommand implements ImageCommand {

  private final ImageFormat imageFormat;
  private final int layerIndex;

  public LoadCommand(ImageFormat imageFormat, int layerIndex) {
    this.imageFormat = imageFormat;
    this.layerIndex = layerIndex;
  }

  @Override
  public void go(MultiLayers model) {
    model.loadImages(imageFormat, layerIndex);
  }
}

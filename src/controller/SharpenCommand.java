package controller;

import model.ImageRepresentation.multiLayers.MultiLayers;

public class SharpenCommand implements ImageCommand {

  @Override
  public void go(MultiLayers model) {
    model.sharpeningImage();
  }
}

package controller;

import model.ImageRepresentation.multiLayers.MultiLayers;

public class BlurCommand implements ImageCommand {

  @Override
  public void go(MultiLayers model) {
    model.blurringImage();
  }
}

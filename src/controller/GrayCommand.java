package controller;

import model.ImageRepresentation.multiLayers.MultiLayers;

public class GrayCommand implements ImageCommand {

  @Override
  public void go(MultiLayers model) {
    model.createMonochrome();
  }
}

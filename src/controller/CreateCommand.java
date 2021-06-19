package controller;

import model.ImageRepresentation.multiLayers.MultiLayers;

public class CreateCommand implements ImageCommand {

  @Override
  public void go(MultiLayers model) {
    model.addLayer();
  }
}

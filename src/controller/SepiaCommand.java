package controller;

import model.ImageRepresentation.multiLayers.MultiLayers;

public class SepiaCommand implements ImageCommand {

  @Override
  public void go(MultiLayers model) {
    model.createSepia();
  }
}

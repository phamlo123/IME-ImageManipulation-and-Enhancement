package controller;

import model.ImageRepresentation.multiLayers.MultiLayers;

public class CurrentCommand implements ImageCommand {

  private final int index;

  public CurrentCommand(int index) {
    this.index = index;
  }

  @Override
  public void go(MultiLayers model) {
    model.setCurrent(index);
  }
}

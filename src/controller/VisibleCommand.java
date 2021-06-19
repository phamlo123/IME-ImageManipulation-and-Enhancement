package controller;

import model.ImageRepresentation.multiLayers.MultiLayers;

public class VisibleCommand implements ImageCommand {

  private final int index;
  private final boolean visible;

  public VisibleCommand(int index, boolean visible) {
    this.index = index;
    this.visible = visible;
  }

  @Override
  public void go(MultiLayers model) {
    model.setInvisibility(index, visible);
  }
}

package controller;

import model.ImageRepresentation.multiLayers.MultiLayers;

public interface Command {

  void go(MultiLayers multiLayers);
}

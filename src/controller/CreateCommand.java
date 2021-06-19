package controller;

import java.util.Objects;
import model.ImageRepresentation.multiLayers.MultiLayers;

/**
 * Command class used for adding a layer to the model.
 */
public class CreateCommand implements ImageCommand {

  @Override
  public void go(MultiLayers model) {
    Objects.requireNonNull(model);
    model.addLayer();
  }
}

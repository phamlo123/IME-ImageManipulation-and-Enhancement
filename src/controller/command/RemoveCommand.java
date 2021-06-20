package controller.command;

import model.ImageRepresentation.multiLayers.MultiLayers;
import model.util.ImageUtil;

/**
 * Command class used for removing the specified layer from the model.
 */
public class RemoveCommand implements ImageCommand {

  private final int layerIndex;

  /**
   * Constructs a RemoveCommand object with the given layer index as a private field.
   * @param layerIndex the index of the layer to remove from the model.
   */
  public RemoveCommand(int layerIndex) {
    this.layerIndex = layerIndex;
  }

  @Override
  public void go(MultiLayers model) throws IllegalArgumentException {
    ImageUtil.checkNull(model);
    model.removeLayer(layerIndex);
  }
}

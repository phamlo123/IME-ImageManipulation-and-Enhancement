package controller.command;

import model.ImageRepresentation.multiLayers.MultiLayers;
import model.util.ImageUtil;

/**
 * Command class used for setting the given layer as the current layer.
 */
public class CurrentCommand implements ImageCommand {

  private final int layerIndex;

  /**
   * Constructs a CurrentCommand object with the given layer index as a private field.
   * @param layerIndex the layer to set as the current
   */
  public CurrentCommand(int layerIndex) {
    this.layerIndex = layerIndex;
  }

  @Override
  public void go(MultiLayers model) throws IllegalArgumentException {
    ImageUtil.checkNull(model);
    model.setCurrent(layerIndex);
  }
}

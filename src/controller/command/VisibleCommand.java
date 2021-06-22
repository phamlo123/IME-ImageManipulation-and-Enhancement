package controller.command;

import model.imagerepresentation.multilayers.MultiLayers;
import model.util.ImageUtil;

/**
 * Command class used for setting images in the model as visible or invisible.
 */
public class VisibleCommand implements ImageCommand {

  private final int layerIndex;
  private final boolean visible;

  /**
   * Creates a VisibleCommand with the layer index and boolean determining whether to make it
   * visible or not as private fields.
   *
   * @param layerIndex the index of the lay to set as visible or invisible
   * @param visible    boolean determining to set the given layer index as visible or invisible.
   *                   True for visible, false for invisible
   */
  public VisibleCommand(int layerIndex, boolean visible) {
    this.layerIndex = layerIndex;
    this.visible = visible;
  }

  @Override
  public void execute(MultiLayers model) {
    ImageUtil.checkNull(model);
    model.setInvisibility(layerIndex, visible);
  }
}

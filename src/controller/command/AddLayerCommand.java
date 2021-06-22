package controller.command;

import model.imagerepresentation.multilayers.MultiLayers;
import model.util.ImageUtil;

/**
 * Command class used for adding a layer to the model.
 */
public class AddLayerCommand implements ImageCommand {

  @Override
  public void execute(MultiLayers model) throws IllegalArgumentException {
    ImageUtil.checkNull(model);
    model.addLayer();
  }
}

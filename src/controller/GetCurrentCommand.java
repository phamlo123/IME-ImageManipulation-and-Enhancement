package controller;

import java.util.Objects;
import model.ImageRepresentation.multiLayers.MultiLayers;
import model.util.ImageUtil;

/**
 * Command class used for getting the current layer in the model.
 */
public class GetCurrentCommand implements ImageCommand {

  @Override
  public void go(MultiLayers model) throws IllegalArgumentException {
    ImageUtil.checkNull(model);
    model.getCurrentLayer();
  }
}

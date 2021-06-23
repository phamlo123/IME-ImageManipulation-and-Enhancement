package controller.command;

import model.imagerepresentation.multilayers.MultiLayers;
import model.util.ImageUtil;

/**
 * Command class used for making the images in the model monochrome.
 */
public class GrayCommand implements ImageCommand {

  @Override
  public void execute(MultiLayers model) throws IllegalArgumentException {
    ImageUtil.checkNull(model);
    model.createMonochrome();
  }
}

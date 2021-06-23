package controller.command;

import model.imagerepresentation.multilayers.MultiLayers;
import model.util.ImageUtil;

/**
 * Command class used for making the images in the model sepia.
 */
public class SepiaCommand implements ImageCommand {

  @Override
  public void execute(MultiLayers model) {
    ImageUtil.checkNull(model);
    model.createSepia();
  }
}

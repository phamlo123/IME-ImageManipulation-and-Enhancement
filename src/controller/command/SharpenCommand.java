package controller.command;

import model.imagerepresentation.multilayers.MultiLayers;
import model.util.ImageUtil;

/**
 * Command class used for sharpening the images in the model.
 */
public class SharpenCommand implements ImageCommand {

  @Override
  public void execute(MultiLayers model) {
    ImageUtil.checkNull(model);
    model.sharpeningImage();
  }
}

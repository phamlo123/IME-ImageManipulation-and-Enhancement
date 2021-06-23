package controller.command;

import model.imagerepresentation.multilayers.MultiLayers;
import model.util.ImageUtil;

/**
 * Command class used for blurring the images in the model.
 */
public class BlurCommand implements ImageCommand {

  @Override
  public void execute(MultiLayers model) throws IllegalArgumentException {
    ImageUtil.checkNull(model);
    model.blurringImage();
  }
}

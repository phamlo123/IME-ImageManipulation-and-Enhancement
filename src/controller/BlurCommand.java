package controller;

import java.util.Objects;
import model.ImageRepresentation.multiLayers.MultiLayers;
import model.util.ImageUtil;

/**
 * Command class used for blurring the images in the model.
 */
public class BlurCommand implements ImageCommand {

  @Override
  public void go(MultiLayers model) throws IllegalArgumentException {
    ImageUtil.checkNull(model);
    model.blurringImage();
  }
}

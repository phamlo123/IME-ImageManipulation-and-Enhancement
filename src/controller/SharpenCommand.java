package controller;

import java.util.Objects;
import model.ImageRepresentation.multiLayers.MultiLayers;
import model.util.ImageUtil;

/**
 * Command class used for sharpening the images in the model.
 */
public class SharpenCommand implements ImageCommand {

  @Override
  public void go(MultiLayers model) {
    ImageUtil.checkNull(model);
    model.sharpeningImage();
  }
}

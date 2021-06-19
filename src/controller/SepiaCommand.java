package controller;

import java.util.Objects;
import model.ImageRepresentation.multiLayers.MultiLayers;
import model.util.ImageUtil;

/**
 * Command class used for making the images in the model sepia.
 */
public class SepiaCommand implements ImageCommand {

  @Override
  public void go(MultiLayers model) {
    ImageUtil.checkNull(model);
    model.createSepia();
  }
}

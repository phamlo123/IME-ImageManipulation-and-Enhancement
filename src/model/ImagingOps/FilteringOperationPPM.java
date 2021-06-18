package model.ImagingOps;

import java.awt.Color;
import java.util.List;
import model.ImageOps;
import model.ImageRepresentation.ImageFormat;
import model.util.Arithmetic;
import model.util.ImageUtil;

/**
 * This class inherits members of its abstract super class and contains methods to filter a PPM
 * object.
 */
public class FilteringOperationPPM extends ImagingOPsPPM implements ImagingOperation<ImageFormat> {

  /**
   * Construct an object of FilteringOperationPPM given the ppm parameter
   *
   * @param imageFormat a imageFormat object to be operated on.
   */
  public FilteringOperationPPM(ImageFormat imageFormat) {
    super(imageFormat, ImageOps.FILTERING);
  }

  @Override
  public List<List<Color>> helperForColoringAndFiltering(List<List<Double>> matrix) {
    List<List<Integer>> red, blue, green;
    red = Arithmetic.helperForMultiplying(redChannel, matrix);
    green = Arithmetic.helperForMultiplying(greenChannel, matrix);
    blue = Arithmetic.helperForMultiplying(blueChannel, matrix);
    return ImageUtil.getLists(red, green, blue);
  }

}

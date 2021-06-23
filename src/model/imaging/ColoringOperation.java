package model.imaging;

import java.awt.Color;
import java.util.List;
import model.enums.Coloring;
import model.enums.ImageOps;
import model.imagerepresentation.ImageFormat;
import model.util.Arithmetic;
import model.util.ImageUtil;

/**
 * This class inherits members of its abstract super class and contains methods to color a PPM
 * object.
 */
public class ColoringOperation extends ImagingOPs implements ImagingOperation<ImageFormat> {


  /**
   * Construct an object of FilteringOperationPPM given the ppm parameter.
   *
   * @param imageFormat a PPM object to be operated on.
   */
  public ColoringOperation(ImageFormat imageFormat) {
    super(imageFormat, ImageOps.COLORING);

  }

  @Override
  public List<List<Color>> helperForColoringAndFiltering(List<List<Double>> matrix) {
    List<List<Integer>> red;
    List<List<Integer>> blue;
    List<List<Integer>> green;

    red = Arithmetic.helperForMultiplyingEigen(Coloring.RED, redChannel,
        greenChannel, blueChannel, matrix);
    green = Arithmetic.helperForMultiplyingEigen(Coloring.GREEN, redChannel,
        greenChannel, blueChannel, matrix);
    blue = Arithmetic.helperForMultiplyingEigen(Coloring.BLUE, redChannel,
        greenChannel, blueChannel, matrix);
    return ImageUtil.getLists(red, green, blue);
  }


}

package model.ImagingOps;

import java.awt.Color;
import java.util.List;
import model.Coloring;
import model.ImageOps;
import model.ImageRepresentation.ImageFormat;
import model.util.Arithmetic;
import model.util.ImageUtil;

/**
 * This class inherits members of its abstract super class and contains methods to color a PPM
 * object.
 */
public class ColoringOperationPPM extends ImagingOPsPPM implements ImagingOperation<ImageFormat> {


  /**
   * Construct an object of FilteringOperationPPM given the ppm parameter
   *
   * @param imageFormat a PPM object to be operated on.
   */
  public ColoringOperationPPM(ImageFormat imageFormat) {
    super(imageFormat, ImageOps.COLORING);

  }

  public List<List<Color>> helperForColoringAndFiltering(List<List<Double>> matrix) {
    List<List<Integer>> red, blue, green;
    red = Arithmetic.helperForMultiplyingEigen(Coloring.RED, redChannel,
        greenChannel, blueChannel, matrix);
    green = Arithmetic.helperForMultiplyingEigen(Coloring.GREEN, redChannel,
        greenChannel, blueChannel, matrix);
    blue = Arithmetic.helperForMultiplyingEigen(Coloring.BLUE, redChannel,
        greenChannel, blueChannel, matrix);
    return ImageUtil.getLists(red, green, blue);
  }


}

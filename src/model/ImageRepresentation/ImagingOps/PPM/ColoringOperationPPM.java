package model.ImageRepresentation.ImagingOps.PPM;

import java.awt.Color;
import java.util.List;
import model.Coloring;
import model.ImageOps;
import model.ImageRepresentation.ImagingOps.ImagingOperation;
import model.ImageRepresentation.PPM;
import model.ImageRepresentation.util.Arithmetic;

/**
 *
 */
public class ColoringOperationPPM extends ImagingOPsPPM implements ImagingOperation<PPM> {


  /**
   *
   * @param ppm
   */
  public ColoringOperationPPM(PPM ppm) {
    super(ppm, ImageOps.COLORING);

  }

  public List<List<Color>> helperForColoringAndFiltering(List<List<Double>> matrix) {
    List<List<Integer>> red, blue, green;
    red = Arithmetic.helperForMultiplyingEigen(Coloring.RED, redChannel,
        greenChannel, blueChannel, matrix);
    green = Arithmetic.helperForMultiplyingEigen(Coloring.GREEN, redChannel,
        greenChannel, blueChannel, matrix);
    blue = Arithmetic.helperForMultiplyingEigen(Coloring.BLUE, redChannel,
        greenChannel, blueChannel, matrix);
    return getLists(red, green, blue);
  }


}

package model.ImageRepresentation.ImagingOps.PPM;

import java.awt.Color;
import java.util.List;
import model.ImageOps;
import model.ImageRepresentation.ImagingOps.ImagingOperation;
import model.ImageRepresentation.PPM;
import model.ImageRepresentation.util.Arithmetic;
import model.ImageRepresentation.util.ImageUtil;

/**
 *
 */
public class FilteringOperationPPM extends ImagingOPsPPM implements ImagingOperation<PPM> {

  /**
   *
   * @param ppm
   */
  public FilteringOperationPPM(PPM ppm) {
    super(ppm, ImageOps.FILTERING);
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

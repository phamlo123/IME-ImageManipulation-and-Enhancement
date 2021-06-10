package model.ImageRepresentation.ImagingOps;

import java.awt.Color;
import java.util.List;

public interface ImagingOperation<T> {

  /**
   * Delegate the operations into the right type of helper functions.
   *
   * @param matrix the kernel that is used to process the PPM image.
   * @return a List of list of Colors that can be used to represent a PPM image.
   */

  List<List<Color>> helperForColoringAndFiltering(List<List<Double>> matrix);

}

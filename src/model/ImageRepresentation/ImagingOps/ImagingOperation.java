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


  /**
   * this method creates a List of List of Colors based on the given lists of red, green, and blue
   * value.
   * @param red is the list of list of red values.
   * @param green is the list of lists of green values.
   * @param blue is the list of list of blue values.
   * @return a list of list of colors that is a combination of the three red, green, and blue
   *         parameters.
   */
  List<List<Color>> getLists(List<List<Integer>> red, List<List<Integer>> green,
      List<List<Integer>> blue);
}

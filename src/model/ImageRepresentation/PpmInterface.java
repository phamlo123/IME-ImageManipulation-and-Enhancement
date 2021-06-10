package model.ImageRepresentation;

import java.awt.Color;
import java.util.List;
import model.Coloring;

/**
 *
 */
public interface PpmInterface {

  void exportPPM(String fileName);
  /**
   * Creates a copy of this PPM's image.
   *
   * @return the copy of this PPM's image as a list of list of colors
   */
  List<List<Color>> getImage();

  /**
   * Returns a copy of this PPM's specified color channel.
   *
   * @param coloring the Coloring type desired
   * @return the color channel of this PPM as a list of list of Integer
   * @throws IllegalArgumentException if an invalid Coloring is passed
   */
  List<List<Integer>> getColorChannel(Coloring coloring) throws IllegalArgumentException;
}

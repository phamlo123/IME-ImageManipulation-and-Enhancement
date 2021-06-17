package model.ImageRepresentation.JPEG;

import java.awt.Color;
import java.util.List;
import model.Coloring;
import model.ImageRepresentation.ImageFormat;

public class JPEG implements ImageFormat {

  /**
   * @param fileName
   */
  @Override
  public void exportPPM(String fileName) {

  }

  /**
   * Creates a copy of this PPM's image.
   *
   * @return the copy of this PPM's image as a list of list of colors
   */
  @Override
  public List<List<Color>> getImage() {
    return null;
  }

  /**
   * Returns a copy of this PPM's specified color channel.
   *
   * @param coloring the Coloring type desired
   * @return the color channel of this PPM as a list of list of Integer
   * @throws IllegalArgumentException if an invalid Coloring is passed
   */
  @Override
  public List<List<Integer>> getColorChannel(Coloring coloring) throws IllegalArgumentException {
    return null;
  }
}

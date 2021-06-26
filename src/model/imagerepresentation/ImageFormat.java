package model.imagerepresentation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;
import model.enums.Coloring;
import model.converter.Converter;

/**
 * This interface contains methods that can be performed on an image object.
 */
public interface ImageFormat {

  /**
   * Get the color content of this image.
   *
   * @return the color content of this image in list of lists.
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

  /**
   * Get the converter of this image.
   *
   * @return the Converter object of this image
   */
  Converter getConverter();

}

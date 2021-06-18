package model.ImageRepresentation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import model.Coloring;
import model.FileFormat;
import model.converter.Converter;

/**
 * This interface contains methods that can be performed on a ppm object.
 */
public interface ImageFormat {


  List<List<Color>> getImage();
  /**
   * Returns a copy of this PPM's specified color channel.
   *
   * @param coloring the Coloring type desired
   * @return the color channel of this PPM as a list of list of Integer
   * @throws IllegalArgumentException if an invalid Coloring is passed
   */
  List<List<Integer>> getColorChannel(Coloring coloring) throws IllegalArgumentException;


  Converter getConverter();

}

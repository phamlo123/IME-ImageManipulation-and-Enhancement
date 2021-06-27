package model.imaging;

import java.awt.Color;
import java.util.List;
import model.imagerepresentation.ImageFormat;

/**
 * This interface provides methods to help perform downsizing and creating mosaic on an ImageFormat.
 */
public interface ImagingOperationExtra extends ImagingOperation<ImageFormat> {


  /**
   * Perform downsizing of an image.
   * @param ratioWidth is the ratio of the new width with respect to the original width
   * @param ratioHeight is the ratio of the new height with respect to the original height
   * @return the new List of list of colors that represent the new image that is the downsized
   *         version of the this one.
   * @throws IllegalArgumentException if the ratio width and height are invalid.
   */
  List<List<Color>> downSize(double ratioWidth, double ratioHeight) throws IllegalArgumentException;

  /**
   * This method helps create a Mosaic filtered version of an image with the specified number of
   * random seeds
   * @param numSeed is the number of random seeds in the image.
   * @return the new List of list of colors that represent the new image that is the mosaic version
   *         of this one.
   * @throws IllegalArgumentException if the number of seeds is less than or equal to 0.
   */
  List<List<Color>> createMosaic(int numSeed) throws IllegalArgumentException;
}

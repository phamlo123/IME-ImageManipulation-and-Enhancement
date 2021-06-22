package model;

public interface ImagesOperationsExtra extends ImagesOperations {


  /**
   * Downsize this image with new width and height according to the ratios with respect to the
   * original width and height of the image in pixel.
   *
   * @param ratioWidth  is the ratio of the new width divided by the original width (ranging from 0
   *                    to 1).
   * @param ratioHeight is the ratio of the new height divided by the original height (ranging from
   *                    0 to 1).
   */
  void downSize(double ratioWidth, double ratioHeight) throws IllegalArgumentException;

  /**
   * Create the Mosaic version of this image with the given number of seeds.
   * @param numSeeds is the number of random seeds in the new image
   */
  void createMosaic(int numSeeds) throws IllegalArgumentException;
}

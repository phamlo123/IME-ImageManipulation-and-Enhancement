package model;

/**
 * This interface contains all operations to be performed on an image.
 */
public interface Images<T> {


  /**
   * Blur the image.
   */
  void blurringImage();

  /**
   * sharpen the image.
   */
  void sharpeningImage();

  /**
   * create a greyscale version of the image.
   */
   void createMonochrome();

  /**
   * create a sepia version of the image.
   */
  void createSepia();

  /**
   * Returns the image of this image representation.
   * @return the image
   */
  T getImage();

}


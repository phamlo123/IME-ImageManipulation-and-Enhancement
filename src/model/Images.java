package model;

/**
 * this interface contains all operations to be performed on an image
 */
public interface Images<T> {


  /**
   * Blur this image
   */
  void blurringImage();

  /**
   * sharpen this image
   */
  void sharpeningImage();

  /**
   * create a greyscale version of this image
   */
   void createMonochrome();

  /**
   * create a sepia version of this image.
   */
  void createSepia();

  T getImage();

}


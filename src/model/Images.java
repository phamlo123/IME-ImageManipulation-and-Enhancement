package model;

/**
 * this interface contains all operations to be performed on an image
 */
public interface Images<T> {


  /**
   * Blur this image
   */
  T blurringImage();

  /**
   * sharpen this image
   */
  T sharpeningImage();

  /**
   * create a greyscale version of this image
   */
   void createMonochrome();

  /**
   * create a sepia version of this image.
   */
  void createSepia();


}


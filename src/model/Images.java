package model;

/**
 * this interface contains all operations to be performed on an image
 */
public interface Images<T> {


  /**
   * Display the given image
   *
   * @return
   */
  T displayImage(Images images);


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
   T createMonochrome();

  /**
   * create a sepia version of this image.
   */
  T createSepia();


}


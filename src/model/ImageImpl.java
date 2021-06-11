package model;

/**
 * This class implements the Images interface and is parameterized by type T, which is an
 * unspecified image type.
 *
 * @param <T>
 */
public abstract class ImageImpl<T> implements Images<T> {

  protected T image;

  /**
   * Constructor to create an abstract object of ImageImpl
   *
   * @param image is the image to be used as the field.
   * @throws IllegalArgumentException if image is null;
   */
  public ImageImpl(T image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image is null");
    }
    this.image = image;
  }

  @Override
  public abstract void blurringImage();

  @Override
  public abstract void sharpeningImage();

  @Override
  public abstract void createMonochrome();

  @Override
  public abstract void createSepia();


}

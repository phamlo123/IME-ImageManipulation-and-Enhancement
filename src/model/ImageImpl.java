package model;

import java.util.List;

/**
 *
 * @param <T>
 */
public abstract class ImageImpl<T> implements Images<T> {
  protected T image;

  public ImageImpl(T image) {
    this.image = image;
  }
  @Override
  public abstract T blurringImage();

  @Override
  public abstract T sharpeningImage();

  @Override
  public abstract T createMonochrome();

  @Override
  public abstract T createSepia();

}

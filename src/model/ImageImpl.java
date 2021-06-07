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
  public abstract void blurringImage();

  @Override
  public abstract void sharpeningImage();

  @Override
  public abstract void createMonochrome();

  @Override
  public abstract void createSepia();

}

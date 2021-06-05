package model;


public abstract class ImageImpl<T> implements Images<T> {


  @Override
  public T displayImage(Images images) {
    return null;
  }

  @Override
  public void blurringImage() {

  }

  @Override
  public void sharpeningImage() {

  }

  @Override
  public T createMonochrome() {
    return null;
  }

  @Override
  public T createSepia() {
    return null;
  }


}

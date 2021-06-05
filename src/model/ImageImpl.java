package model;

import java.util.List;

/**
 *
 * @param <T>
 */
public abstract class ImageImpl<T> implements Images<T> {


  @Override
  public T displayImage(Images images) {
    return null;
  }

  @Override
  public T blurringImage() {
    List<List<Double>> a = Matrices.MATRIX_FOR_BLURRING.getMatrix();
    return null;
  }

  @Override
  public T sharpeningImage() {
    List<List<Double>> a = Matrices.MATRIX_FOR_SHARPENING.getMatrix();
    return null;
  }

  @Override
  public T createMonochrome() {
    List<List<Double>> a =  Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix();
    return null;
  }

  @Override
  public T createSepia() {
    List<List<Double>> a =  Matrices.MATRIX_FOR_SEPIA.getMatrix();
    return null;
  }


}

package model.ImageRepresentation;

import java.awt.Color;
import java.util.List;
import model.ImageImpl;
import model.Matrices;

public class ImageImplPPM extends ImageImpl<PPM> {
  public ImageImplPPM(PPM ppm) {
    super(ppm);
  }

  @Override
  public PPM blurringImage() {
    List<List<Color>> temp = this.image.getImage();
    List<List<Double>> a = Matrices.MATRIX_FOR_BLURRING.getMatrix();
    for(int row = 0; row < temp.size(); row++) {
      for (int column = 0; column<temp.get(row).size(); column++) {
      }
    }

    return new PPM(temp);
  }

  @Override
  public PPM sharpeningImage() {
    List<List<Double>> a = Matrices.MATRIX_FOR_SHARPENING.getMatrix();

    return null;
  }

  @Override
  public PPM createMonochrome() {
    List<List<Double>> a = Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix();

    return null;
  }

  @Override
  public PPM createSepia() {
    List<List<Double>> a = Matrices.MATRIX_FOR_SEPIA.getMatrix();
    return null;
  }
}

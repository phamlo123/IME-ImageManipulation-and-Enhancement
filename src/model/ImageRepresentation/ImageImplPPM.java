package model.ImageRepresentation;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Coloring;
import model.ImageImpl;
import model.ImageOps;
import model.ImageRepresentation.util.Arithmetic;
import model.Matrices;

/**
 *
 */
public class ImageImplPPM extends ImageImpl<PPM> {

  public ImageImplPPM(PPM ppm) {
    super(ppm);
  }

  @Override
  public void blurringImage() {

    List<List<Double>> matrix = Matrices.MATRIX_FOR_BLURRING.getMatrix();
    this.image = new PPM(helperForColoringAndFiltering(ImageOps.FILTERING, this.image, matrix));
  }

  @Override
  public void sharpeningImage() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_SHARPENING.getMatrix();
    this.image = new PPM(helperForColoringAndFiltering(ImageOps.FILTERING, this.image, matrix));
  }

  @Override
  public void createMonochrome() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix();
    this.image = new PPM(helperForColoringAndFiltering(ImageOps.COLORING, this.image, matrix));
  }

  @Override
  public void createSepia() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_SEPIA.getMatrix();
    this.image = new PPM(helperForColoringAndFiltering(ImageOps.COLORING, this.image, matrix));
  }



  /**
   *
   * @param imageOps
   * @param ppm
   * @param matrix
   * @return
   */
  private List<List<Color>> helperForColoringAndFiltering(ImageOps imageOps, PPM ppm,
      List<List<Double>> matrix) {
    List<List<Integer>> redChannel = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> blueChannel = ppm.getColorChannel(Coloring.BLUE);
    List<List<Integer>> greenChannel = ppm.getColorChannel(Coloring.GREEN);
    List<List<Integer>> red, blue, green;
    switch (imageOps) {
      case COLORING:
        red = Arithmetic.helperForMultiplyingEigen(Coloring.RED, redChannel,
            greenChannel, blueChannel, matrix);
        green = Arithmetic.helperForMultiplyingEigen(Coloring.GREEN, redChannel,
            greenChannel, blueChannel, matrix);
        blue = Arithmetic.helperForMultiplyingEigen(Coloring.BLUE, redChannel,
            greenChannel, blueChannel, matrix);
        break;
      case FILTERING:
        red = Arithmetic.helperForMultiplying(redChannel, matrix);
        green = Arithmetic.helperForMultiplying(greenChannel, matrix);
        blue = Arithmetic.helperForMultiplying(blueChannel, matrix);
        break;
      default:
        throw new IllegalArgumentException();
    }
    return getLists(red, green, blue);
  }

  /**
   *
   * @param red
   * @param green
   * @param blue
   * @return
   */
  private List<List<Color>> getLists(List<List<Integer>> red, List<List<Integer>> green,
      List<List<Integer>> blue) {
    List<List<Color>> temp = new ArrayList<>();

    int height = this.image.getImage().size();
    int width = this.image.getImage().get(0).size();

    for (int row = 0; row < height; row++) {
      temp.add(new ArrayList<>());
      for (int column = 0; column < width; column++) {
        temp.get(row).add(new Color(red.get(row).get(column), green.get(row).get(column),
            blue.get(row).get(column)));
      }
    }
    return temp;
  }




  public static void main(String[] args) {

    PPM ppm = PPM.importImageFile("Koala.ppm");
    ImageImplPPM t = new ImageImplPPM(ppm);

    t.createSepia();
    t.getImage().exportPPM("s.ppm");
  }
}

package model.ImageRepresentation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import model.Coloring;
import model.ImageImpl;
import model.Matrices;

public class ImageImplPPM extends ImageImpl<PPM> {

  public ImageImplPPM(PPM ppm) {
    super(ppm);
  }

  @Override
  public void blurringImage() {

    List<List<Double>> matrix = Matrices.MATRIX_FOR_BLURRING.getMatrix();
    this.image = new PPM(helperForFiltering(this.image, matrix));
  }

  @Override
  public void sharpeningImage() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_SHARPENING.getMatrix();
    this.image = new PPM(helperForFiltering(this.image, matrix));
  }

  @Override
  public void createMonochrome() {
    List<List<Double>> a = Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix();
  }

  @Override
  public void createSepia() {
    List<List<Double>> a = Matrices.MATRIX_FOR_SEPIA.getMatrix();
  }

  private List<List<Color>> helperForFiltering(PPM ppm,
      List<List<Double>> matrix) {
    List<List<Integer>> redChannel = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> blueChannel = ppm.getColorChannel(Coloring.BLUE);
    List<List<Integer>> greenChannel = ppm.getColorChannel(Coloring.GREEEN);

    List<List<Integer>> red = helperForMultiplying(redChannel, matrix);
    List<List<Integer>> blue = helperForMultiplying(blueChannel, matrix);
    List<List<Integer>> green = helperForMultiplying(greenChannel, matrix);

    List<List<Color>> temp = new ArrayList<>();
    for (int row = 0; row < ppm.getImage().size(); row++) {
      temp.add(new ArrayList<>());
      for (int column = 0; column < ppm.getImage().get(row).size(); column++) {
        temp.get(row).add(new Color(red.get(row).get(column), blue.get(row).get(column),
            green.get(row).get(column)));
      }
    }
    return temp;
  }

  private static List<List<Integer>> helperForMultiplying(List<List<Integer>> channel,
      List<List<Double>> matrix) {
    for (int row = 1; row < channel.size() - 1; row++) {
      for (int column = 1; column < channel.get(row).size() - 1; column++) {
        double temp = channel.get(row - 1).get(column - 1) * matrix.get(0).get(0)
            + channel.get(row - 1).get(column) * matrix.get(0).get(1)
            + channel.get(row - 1).get(column + 1) * matrix.get(0).get(2)
            + channel.get(row).get(column - 1) * matrix.get(1).get(0)
            + channel.get(row).get(column) * matrix.get(1).get(1)
            + channel.get(row).get(column + 1) * matrix.get(1).get(2)
            + channel.get(row + 1).get(column - 1) * matrix.get(2).get(0)
            + channel.get(row + 1).get(column) * matrix.get(2).get(1)
            + channel.get(row + 1).get(column + 1) * matrix.get(2).get(2);

        int a = (int) Math.round(Math.min(temp, 255));
        channel.get(row).set(column, a);
      }
    }
    return channel;
  }


  public static void main(String[] args) {

  }
}

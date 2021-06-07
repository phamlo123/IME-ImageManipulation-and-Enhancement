package model.ImageRepresentation;

import java.awt.Color;
import java.io.IOException;
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
    this.image = new PPM (helperForFiltering(this.image, matrix));
  }

  @Override
  public void sharpeningImage() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_SHARPENING.getMatrix();
    this.image = new PPM (helperForFiltering(this.image, matrix));
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
    int rowSize = ppm.getImage().size();
    int columnSize = ppm.getImage().get(0).size();
    for (int row = 0; row < rowSize; row++) {
      temp.add(new ArrayList<>());
      for (int column = 0; column < columnSize; column++) {
        temp.get(row).add(new Color(red.get(row).get(column), green.get(row).get(column),
            blue.get(row).get(column)));
      }
    }
    return temp;
  }

  private static List<List<Integer>> helperForMultiplying(List<List<Integer>> channel,
      List<List<Double>> matrix) {
    for (int row = 0; row < channel.size(); row++) {
      for (int column = 0; column < channel.get(row).size(); column++) {
        double temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9;
        try {
          temp1 = channel.get(row - 1).get(column - 1) * matrix.get(0).get(0);
        } catch (IndexOutOfBoundsException e) {
          temp1 = 0;
        }
        try {
          temp2 = channel.get(row - 1).get(column) * matrix.get(0).get(1);
        } catch (IndexOutOfBoundsException e) {
          temp2 = 0;
        }
        try {
          temp3 = channel.get(row - 1).get(column + 1) * matrix.get(0).get(2);
        } catch (IndexOutOfBoundsException e) {
          temp3 = 0;
        }
        try {
          temp4 = channel.get(row).get(column - 1) * matrix.get(1).get(0);
        } catch (IndexOutOfBoundsException e) {
          temp4 = 0;
        }
        try {
          temp5 =channel.get(row).get(column) * matrix.get(1).get(1);
        } catch (IndexOutOfBoundsException e) {
          temp5=0;
        }
        try {
          temp6 = channel.get(row).get(column + 1) * matrix.get(1).get(2);
        }catch (IndexOutOfBoundsException e) {
          temp6=0;
        }
        try {
          temp7 = channel.get(row + 1).get(column - 1) * matrix.get(2).get(0);
        } catch (IndexOutOfBoundsException e) {
          temp7 = 0;
        }
        try {
          temp8 = channel.get(row + 1).get(column) * matrix.get(2).get(1);
        } catch (IndexOutOfBoundsException e) {
          temp8 = 0;
        }
        try {
          temp9 = channel.get(row + 1).get(column + 1) * matrix.get(2).get(2);
        } catch (IndexOutOfBoundsException e) {
          temp9=0;
        }
        double sum = temp1+temp2+temp3+temp4+temp5+temp6+temp7+temp8+temp9;

        int a;
        if(sum > 0) {
           a = (int) (Math.min(sum, 255));
        } else {
           a = (int) (Math.max(sum, -256));
        }
        channel.get(row).set(column, a);
      }
    }
    return channel;
  }




  public static void main(String[] args) throws IOException {

    PPM ppm =  PPM.importImageFile("Koala.ppm");
    ImageImplPPM t = new ImageImplPPM(ppm);
    t.blurringImage();
    ppm.exportPPM();

  }
}

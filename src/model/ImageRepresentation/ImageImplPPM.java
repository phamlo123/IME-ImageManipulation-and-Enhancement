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

    List<List<Integer>> red = helperForMultiplying2(redChannel, matrix);
    List<List<Integer>> green = helperForMultiplying2(greenChannel, matrix);

    List<List<Integer>> blue = helperForMultiplying2(blueChannel, matrix);

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


  private static List<List<Integer>> helperForMultiplying2 (List<List<Integer>> channel,
      List<List<Double>> matrix) {
    int height = channel.size();
    int offset = (matrix.size() - 1)/2;
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < channel.get(row).size(); column++) {
        double sum = 0;
        for(int i = 0; i < matrix.size(); i++) {
          inner:
          for (int j = 0; j < matrix.get(i).size(); j++) {
            try {
              sum = sum + channel.get(row + i - offset).get(column + j - offset)
                  * matrix.get(i).get(j);
            } catch (IndexOutOfBoundsException e) {
              continue inner;
            }
          }
        }
        int a;
        if(sum > 0) {
          a = (int) (Math.min(sum, 255));
        } else {
          a = (int) (Math.max(sum, 0));
        }
        channel.get(row).set(column, a);
      }
    }

    return channel;
  }



  public static void main(String[] args) throws IOException {

    PPM ppm =  PPM.importImageFile("Koala.ppm");
    ImageImplPPM t = new ImageImplPPM(ppm);

    t.sharpeningImage();
    t.image.exportPPM();
  }
}

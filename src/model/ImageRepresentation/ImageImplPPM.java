package model.ImageRepresentation;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Coloring;
import model.ImageImpl;
import model.ImageOps;
import model.Matrices;

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





  private static List<List<Integer>> helperForMultiplying2(List<List<Integer>> channel,
      List<List<Double>> matrix) {
    int height = channel.size();
    int offset = (matrix.size() - 1) / 2;
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < channel.get(row).size(); column++) {
        double sum = 0;
        for (int i = 0; i < matrix.size(); i++) {
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
        if (sum > 0) {
          a = (int) (Math.min(sum, 255));
        } else {
          a = (int) (Math.max(sum, 0));
        }
        channel.get(row).set(column, a);
      }
    }

    return channel;
  }

  private List<List<Color>> helperForColoringAndFiltering(ImageOps imageOps, PPM ppm,
      List<List<Double>> matrix) {
    List<List<Integer>> redChannel = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> blueChannel = ppm.getColorChannel(Coloring.BLUE);
    List<List<Integer>> greenChannel = ppm.getColorChannel(Coloring.GREEEN);
    List<List<Integer>> red, blue, green;
    switch (imageOps) {
      case COLORING:
        red = anotherHelper(Coloring.RED, redChannel, greenChannel, blueChannel, matrix);
        green = anotherHelper(Coloring.GREEEN, redChannel, greenChannel, blueChannel, matrix);
        blue = anotherHelper(Coloring.BLUE, redChannel, greenChannel, blueChannel, matrix);
        break;
      case FILTERING:
        red = helperForMultiplying2(redChannel, matrix);
        green = helperForMultiplying2(greenChannel, matrix);
        blue = helperForMultiplying2(blueChannel, matrix);
        break;
      default:
        throw new IllegalArgumentException();
    }
    return getLists(red, green, blue);
  }


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

  private List<List<Integer>> anotherHelper(Coloring coloring, List<List<Integer>> redChannel,
      List<List<Integer>> greenChannel, List<List<Integer>> blueChannel,
      List<List<Double>> matrix) throws IllegalArgumentException {
    switch (coloring) {
      case RED:
        for (int i = 0; i < redChannel.size(); i++) {
          for (int j = 0; j < redChannel.get(i).size(); j++) {
            double sum = matrix.get(0).get(0) * redChannel.get(i).get(j)
                + matrix.get(0).get(1) * greenChannel.get(i).get(j)
                + matrix.get(0).get(2) * blueChannel.get(i).get(j);
            int a;
            if (sum > 0) {
              a = (int) Math.min(sum, 255);
            } else {
              a = (int) Math.max(sum, 0);
            }
            redChannel.get(i).set(j, a);
          }
        }
        return redChannel;
      case GREEEN:
        for (int i = 0; i < greenChannel.size(); i++) {
          for (int j = 0; j < greenChannel.get(i).size(); j++) {
            double sum = matrix.get(1).get(0) * redChannel.get(i).get(j)
                + matrix.get(1).get(1) * greenChannel.get(i).get(j)
                + matrix.get(1).get(2) * blueChannel.get(i).get(j);
            int a;
            if (sum > 0) {
              a = (int) Math.min(sum, 255);
            } else {
              a = (int) Math.max(sum, 0);
            }
            greenChannel.get(i).set(j, a);
          }
        }
        return greenChannel;
      case BLUE:
        for (int i = 0; i < greenChannel.size(); i++) {
          for (int j = 0; j < greenChannel.get(i).size(); j++) {
            double sum = matrix.get(2).get(0) * redChannel.get(i).get(j)
                + matrix.get(2).get(1) * greenChannel.get(i).get(j)
                + matrix.get(2).get(2) * blueChannel.get(i).get(j);
            int a;
            if (sum > 0) {
              a = (int) Math.min(sum, 255);
            } else {
              a = (int) Math.max(sum, 0);
            }
            blueChannel.get(i).set(j, a);
          }
        }
        return blueChannel;
      default:
        throw new IllegalArgumentException();
    }
  }




  public static void main(String[] args) throws IOException {

    PPM ppm = PPM.importImageFile("Koala.ppm");
    PPM ppm2 = new PPM();
    ImageImplPPM t = new ImageImplPPM(ppm);

    t.createMonochrome();

    t.image.exportPPM();
  }
}

package model.ImageRepresentation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import model.Coloring;
import model.FileFormat;
import model.Images;
import model.ImagingOps.ColoringOperationPPM;
import model.ImagingOps.FilteringOperationPPM;
import model.ImagingOps.ImagingOperation;
import model.Matrices;

/**
 * This class extends the ImageImpl abstract class that is parameterized by type PPM. This class
 * will implements the Images interface and will therefore implements all the methods in the
 * interface onto a PPM object.
 */
public class ImageImplPPM implements Images {
  private ImageFormat image;
  public ImageImplPPM(ImageFormat imageFormat) {
    if (imageFormat == null) {
      throw new IllegalArgumentException("Provided image is null");
    }
    this.image = imageFormat;
  }

  @Override
  public void blurringImage() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_BLURRING.getMatrix();
    ImagingOperation<ImageFormat> imagingOperation = new FilteringOperationPPM(this.image);
    this.image = new Image(imagingOperation.helperForColoringAndFiltering(matrix));
  }

  @Override
  public void sharpeningImage() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_SHARPENING.getMatrix();
    ImagingOperation<ImageFormat> imagingOperation = new FilteringOperationPPM(this.image);
    this.image = new Image(imagingOperation.helperForColoringAndFiltering(matrix));
  }

  @Override
  public void createMonochrome() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix();
    ImagingOperation<ImageFormat> imagingOperation = new ColoringOperationPPM(this.image);
    this.image = new Image(imagingOperation.helperForColoringAndFiltering(matrix));
  }

  @Override
  public void createSepia() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_SEPIA.getMatrix();
    ImagingOperation<ImageFormat> imagingOperation = new ColoringOperationPPM(this.image);
    this.image = new Image(imagingOperation.helperForColoringAndFiltering(matrix));
  }

  @Override
  public ImageFormat getImage() {
    List<List<Color>> temp = new ArrayList<>();
    List<List<Integer>> red = this.image.getColorChannel(Coloring.RED);
    List<List<Integer>> green = this.image.getColorChannel(Coloring.GREEN);
    List<List<Integer>> blue = this.image.getColorChannel(Coloring.BLUE);
    int size = red.size();
    for (int i = 0; i < size; i++) {
      temp.add(new ArrayList<>());
      for (int j = 0; j < red.get(i).size(); j++) {
        temp.get(i).add(new Color(red.get(i).get(j), green.get(i).get(j), blue.get(i).get(j)));
      }
    }
    return new Image(temp);
  }

  public static void main(String[] args) {
    ImageFormat i = new Image("abc.jpg");
    Images a = new ImageImplPPM(i);
    a.createSepia();
    a.getImage().getConverter().exportImage("k", FileFormat.JPEG);


  }

}

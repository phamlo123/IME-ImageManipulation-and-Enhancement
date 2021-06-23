package model.imagerepresentation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import model.ImagesOperations;
import model.enums.Coloring;
import model.imaging.ColoringOperation;
import model.imaging.FilteringOperation;
import model.imaging.ImagingOperation;
import model.enums.Matrices;

/**
 * This class extends the ImageImpl abstract class that is parameterized by type PPM. This class
 * will implements the Images interface and will therefore implements all the methods in the
 * interface onto a PPM object.
 */
public class ImagesOperationsImpl implements ImagesOperations {

  protected ImageFormat image;

  /**
   * Create an instance of this class to perform filtering on this image.
   *
   * @param imageFormat is the image to be performed filtering on.
   */
  public ImagesOperationsImpl(ImageFormat imageFormat) {
    if (imageFormat == null) {
      throw new IllegalArgumentException("Provided image is null");
    }
    this.image = imageFormat;
  }

  @Override
  public void blurringImage() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_BLURRING.getMatrix();
    ImagingOperation<ImageFormat> imagingOperation = new FilteringOperation(this.image);
    this.image = new Image(imagingOperation.helperForColoringAndFiltering(matrix));
  }


  @Override
  public void sharpeningImage() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_SHARPENING.getMatrix();
    ImagingOperation<ImageFormat> imagingOperation = new FilteringOperation(this.image);
    this.image = new Image(imagingOperation.helperForColoringAndFiltering(matrix));
  }

  @Override
  public void createMonochrome() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix();
    ImagingOperation<ImageFormat> imagingOperation = new ColoringOperation(this.image);
    this.image = new Image(imagingOperation.helperForColoringAndFiltering(matrix));
  }

  @Override
  public void createSepia() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_SEPIA.getMatrix();
    ImagingOperation<ImageFormat> imagingOperation = new ColoringOperation(this.image);
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


}

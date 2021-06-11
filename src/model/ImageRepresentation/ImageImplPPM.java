package model.ImageRepresentation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import model.Coloring;
import model.ImageImpl;
import model.ImageRepresentation.ImagingOps.PPM.ColoringOperationPPM;
import model.ImageRepresentation.ImagingOps.PPM.FilteringOperationPPM;
import model.ImageRepresentation.ImagingOps.ImagingOperation;
import model.Matrices;

/**
 * This class extends the ImageImpl abstract class that is parameterized by type PPM. This class
 * will implements the Images interface and will therefore implements all the methods in the
 * interface onto a PPM object.
 */
public class ImageImplPPM extends ImageImpl<PPM> {

  public ImageImplPPM(PPM ppm) {
    super(ppm);

  }

  @Override
  public void blurringImage() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_BLURRING.getMatrix();
    ImagingOperation<PPM> imagingOperation = new FilteringOperationPPM(this.image);
    this.image = new PPM(imagingOperation.helperForColoringAndFiltering(matrix));
  }

  @Override
  public void sharpeningImage() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_SHARPENING.getMatrix();
    ImagingOperation<PPM> imagingOperation = new FilteringOperationPPM(this.image);
    this.image = new PPM(imagingOperation.helperForColoringAndFiltering(matrix));
  }

  @Override
  public void createMonochrome() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix();
    ImagingOperation<PPM> imagingOperation = new ColoringOperationPPM(this.image);
    this.image = new PPM(imagingOperation.helperForColoringAndFiltering(matrix));
  }

  @Override
  public void createSepia() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_SEPIA.getMatrix();
    ImagingOperation<PPM> imagingOperation = new ColoringOperationPPM(this.image);
    this.image = new PPM(imagingOperation.helperForColoringAndFiltering(matrix));
  }

  @Override
  public PPM getImage() {
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
    return new PPM(temp);
  }

}

package model.ImageRepresentation;

import java.util.List;
import model.ImageImpl;
import model.ImageOps;
import model.ImageRepresentation.ImagingOps.PPM.ImagingOPsPPM;
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
    ImagingOperation<PPM> imagingOperation = new ImagingOPsPPM(this.image, ImageOps.FILTERING);
    this.image = new PPM(imagingOperation.helperForColoringAndFiltering(matrix));
  }

  @Override
  public void sharpeningImage() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_SHARPENING.getMatrix();
    ImagingOperation<PPM> imagingOperation = new ImagingOPsPPM(this.image, ImageOps.FILTERING);
    this.image = new PPM(imagingOperation.helperForColoringAndFiltering(matrix));
  }

  @Override
  public void createMonochrome() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix();
    ImagingOperation<PPM> imagingOperation = new ImagingOPsPPM(this.image, ImageOps.COLORING);
    this.image = new PPM(imagingOperation.helperForColoringAndFiltering(matrix));
  }

  @Override
  public void createSepia() {
    List<List<Double>> matrix = Matrices.MATRIX_FOR_SEPIA.getMatrix();
    ImagingOperation<PPM> imagingOperation = new ImagingOPsPPM(this.image, ImageOps.COLORING);
    this.image = new PPM(imagingOperation.helperForColoringAndFiltering(matrix));
  }


}

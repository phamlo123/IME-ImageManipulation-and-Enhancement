package model.imaging;

import java.awt.Color;
import java.util.List;
import model.imagerepresentation.ImageFormat;

/**
 * This interface provides methods to help perform downsizing and creating mosaic on an ImageFormat.
 */
public interface ImagingOperationExtra extends ImagingOperation<ImageFormat> {


  /**
   * Perform downsizing of an image in
   * @param ratioWidth
   * @param ratioHeight
   * @return
   */
  List<List<Color>> downSize(double ratioWidth, double ratioHeight);

  /**
   *
   * @param numSeed
   * @return
   */
  List<List<Color>> createMosaic(int numSeed);
}

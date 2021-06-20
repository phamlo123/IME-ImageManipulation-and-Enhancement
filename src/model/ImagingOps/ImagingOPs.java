package model.ImagingOps;

import java.util.List;
import model.enumTypes.Coloring;
import model.enumTypes.ImageOps;
import model.ImageRepresentation.ImageFormat;

/**
 * Abstract class to provide a unified way to construct a PPM imaging operator by dissecting the PPM
 * object parameter into red, blue and green color channels.
 */
public abstract class ImagingOPs implements ImagingOperation<ImageFormat> {

  protected ImageFormat imageObject;
  protected ImageOps imageOps;
  protected List<List<Integer>> redChannel;
  protected List<List<Integer>> blueChannel;
  protected List<List<Integer>> greenChannel;

  /**
   * Construct an abstract object of ImagingOPsPPM
   *
   * @param imageObject is the PPM object to be dissected
   * @param imageOps    is the kind of operation
   */
  public ImagingOPs(ImageFormat imageObject, ImageOps imageOps) {
    if (imageObject == null) {
      throw new IllegalArgumentException("image Object is null");
    }
    this.imageObject = imageObject;
    this.imageOps = imageOps;
    this.redChannel = imageObject.getColorChannel(Coloring.RED);
    this.blueChannel = imageObject.getColorChannel(Coloring.BLUE);
    this.greenChannel = imageObject.getColorChannel(Coloring.GREEN);
  }

}
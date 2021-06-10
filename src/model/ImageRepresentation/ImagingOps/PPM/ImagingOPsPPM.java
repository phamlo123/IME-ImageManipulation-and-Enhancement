package model.ImageRepresentation.ImagingOps.PPM;

import java.util.List;
import model.Coloring;
import model.ImageOps;
import model.ImageRepresentation.ImagingOps.ImagingOperation;
import model.ImageRepresentation.PPM;

/**
 *
 */
public abstract class ImagingOPsPPM implements ImagingOperation<PPM> {

  protected PPM imageObject;
  protected ImageOps imageOps;
  protected List<List<Integer>> redChannel;
  protected List<List<Integer>> blueChannel;
  protected List<List<Integer>> greenChannel;

  /**
   * @param imageObject
   * @param imageOps
   */
  public ImagingOPsPPM(PPM imageObject, ImageOps imageOps) {
    if(imageObject == null) {
      throw new IllegalArgumentException("image Object is null");
    }
    this.imageObject = imageObject;
    this.imageOps = imageOps;
    this.redChannel = imageObject.getColorChannel(Coloring.RED);
    this.blueChannel = imageObject.getColorChannel(Coloring.BLUE);
    this.greenChannel = imageObject.getColorChannel(Coloring.GREEN);
  }


}

package model.imagerepresentation;

import model.ImagesOperationsExtra;
import model.enums.FileFormat;
import model.imaging.ImagingOperationExtra;
import model.imaging.ImagingOperationExtraImpl;

public class ImagesOperationsExtraImpl extends ImagesOperationsImpl
    implements ImagesOperationsExtra {

  /**
   * Create an instance of this class to perform downSizing and Mosaic on this image.
   *
   * @param imageFormat is the image to be performed filtering on.
   */
  public ImagesOperationsExtraImpl(ImageFormat imageFormat) {
    super(imageFormat);
  }

  @Override
  public void downSize(double ratioWidth, double ratioHeight) throws IllegalArgumentException {
    if (ratioHeight < 0 || ratioHeight > 1 || ratioWidth < 0 || ratioWidth > 1) {
      throw new IllegalArgumentException("Invalid ratios");
    }
    ImagingOperationExtra imagingOperationExtra = new ImagingOperationExtraImpl(this.image);
    this.image = new Image(imagingOperationExtra.downSize(ratioWidth, ratioHeight));
  }

  @Override
  public void createMosaic(int numSeeds) throws IllegalArgumentException {
    if (numSeeds <= 0) {
      throw new IllegalArgumentException("Number of seeds is less than or equal to 0");
    }
    ImagingOperationExtra imagingOperationExtra = new ImagingOperationExtraImpl(this.image);
    this.image = new Image(imagingOperationExtra.createMosaic(numSeeds));
  }

  public static void main(String[] args) {
    Image i = new Image("Jellyfish.jpg");
    ImagesOperationsExtra im = new ImagesOperationsExtraImpl(i);
    im.createMosaic(100000);
    im.getImage().getConverter().exportImage("sth", FileFormat.JPEG);

  }
}

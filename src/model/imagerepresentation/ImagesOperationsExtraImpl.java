package model.imagerepresentation;

import model.ImagesOperations;
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
  public void downSize(double ratioWidth, double ratioHeight) {
    ImagingOperationExtra imagingOperationExtra = new ImagingOperationExtraImpl(this.image);
    this.image = new Image(imagingOperationExtra.downSize(ratioWidth, ratioHeight));
  }

  @Override
  public void createMosaic(int numSeeds) {
    ImagingOperationExtra imagingOperationExtra = new ImagingOperationExtraImpl(this.image);
    this.image = new Image(imagingOperationExtra.createMosaic(numSeeds));
  }


  public static void main(String[] args) {
    Image image1 = new Image("cdf.jpg");
    ImagesOperationsExtra i1 = new ImagesOperationsExtraImpl(image1);
    i1.createMosaic(8000);
    i1.getImage().getConverter().exportImage("cdfTest1", FileFormat.JPEG);
  }
}

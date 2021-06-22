package model.imaging;

import java.awt.Color;
import java.util.List;
import model.imagerepresentation.ImageFormat;

public interface ImagingOperationExtra extends ImagingOperation<ImageFormat> {


  List<List<Color>> downSize(double ratioWidth, double ratioHeight);

  List<List<Color>> createMosaic(int numSeed);
}

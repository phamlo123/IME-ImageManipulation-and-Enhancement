package model.ImageRepresentation.ImagingOps.PPM;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import model.Coloring;
import model.ImageOps;
import model.ImageRepresentation.ImagingOps.ImagingOperation;
import model.ImageRepresentation.ImagingOps.PPM.ColoringOperationPPM;
import model.ImageRepresentation.ImagingOps.PPM.FilteringOperationPPM;
import model.ImageRepresentation.PPM;

/**
 *
 */
public class ImagingOPsPPM implements ImagingOperation<PPM> {

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

  @Override
  public List<List<Color>> helperForColoringAndFiltering(List<List<Double>> matrix) {
    switch (imageOps) {
      case COLORING:
        ImagingOperation<PPM> imagingOperationColoring = new ColoringOperationPPM(imageObject);
        return imagingOperationColoring.helperForColoringAndFiltering(matrix);
      case FILTERING:
        ImagingOperation<PPM> imagingOperationFiltering = new FilteringOperationPPM(imageObject);
        return imagingOperationFiltering.helperForColoringAndFiltering(matrix);
      default:
        throw new IllegalArgumentException("Unsupported Image Operation!");
    }
  }

  @Override
  public List<List<Color>> getLists(List<List<Integer>> red, List<List<Integer>> green,
      List<List<Integer>> blue) {
    List<List<Color>> temp = new ArrayList<>();

    int height = red.size();
    int width = red.get(0).size();

    for (int row = 0; row < height; row++) {
      temp.add(new ArrayList<>());
      for (int column = 0; column < width; column++) {
        temp.get(row).add(new Color(red.get(row).get(column), green.get(row).get(column),
            blue.get(row).get(column)));
      }
    }
    return temp;
  }
}

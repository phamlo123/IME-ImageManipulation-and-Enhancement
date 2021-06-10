import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import model.Coloring;
import model.ImageRepresentation.ImagingOps.ImagingOperation;
import model.ImageRepresentation.ImagingOps.PPM.ColoringOperationPPM;
import model.ImageRepresentation.ImagingOps.PPM.FilteringOperationPPM;
import model.ImageRepresentation.PPM;
import model.ImageRepresentation.util.Arithmetic;
import model.Matrices;
import org.junit.Test;

public class FilteringOperationPPMTest {

  ImagingOperation a = new FilteringOperationPPM(new PPM(10, 10));

  @Test(expected = IllegalArgumentException.class)
  public void testNullConstructor() {
    ImagingOperation a = new ColoringOperationPPM(null);
  }

  @Test
  public void testHelperFiltering() {

    List<List<Color>> b = a.helperForColoringAndFiltering(Matrices.MATRIX_FOR_BLURRING
        .getMatrix());
    PPM ppm = new PPM(10, 10);
    List<List<Integer>> red = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> green = ppm.getColorChannel(Coloring.GREEN);
    List<List<Integer>> blue = ppm.getColorChannel(Coloring.BLUE);

    List<List<Integer>> colorRed = Arithmetic.helperForMultiplying(red, Matrices.MATRIX_FOR_BLURRING
        .getMatrix());
    List<List<Integer>> colorBlue = Arithmetic.helperForMultiplying(blue,
        Matrices.MATRIX_FOR_BLURRING.getMatrix());
    List<List<Integer>> colorGreen = Arithmetic.helperForMultiplying(green,
        Matrices.MATRIX_FOR_BLURRING.getMatrix());

    int height = colorBlue.size();
    int width = colorBlue.get(0).size();
    List<List<Color>> temp = new ArrayList<>();
    for (int row = 0; row < height; row++) {
      temp.add(new ArrayList<>());
      for (int column = 0; column < width; column++) {
        temp.get(row).add(new Color(colorRed.get(row).get(column), colorGreen.get(row).get(column),
            colorBlue.get(row).get(column)));
      }
    }
    assertEquals(height, b.size());
    assertEquals(width, b.get(0).size());
    assertEquals(b, temp);
    assertEquals(new PPM(b), new PPM(temp));
  }


  @Test
  public void testHelperFiltering2() {

    List<List<Color>> b = a.helperForColoringAndFiltering(Matrices.MATRIX_FOR_SHARPENING
        .getMatrix());
    PPM ppm = new PPM(10, 10);
    List<List<Integer>> red = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> green = ppm.getColorChannel(Coloring.GREEN);
    List<List<Integer>> blue = ppm.getColorChannel(Coloring.BLUE);

    List<List<Integer>> colorRed = Arithmetic.helperForMultiplying(red,
        Matrices.MATRIX_FOR_SHARPENING.getMatrix());
    List<List<Integer>> colorBlue = Arithmetic.helperForMultiplying(blue,
        Matrices.MATRIX_FOR_SHARPENING.getMatrix());
    List<List<Integer>> colorGreen = Arithmetic.helperForMultiplying(green,
        Matrices.MATRIX_FOR_SHARPENING.getMatrix());

    int height = colorBlue.size();
    int width = colorBlue.get(0).size();
    List<List<Color>> temp = new ArrayList<>();
    for (int row = 0; row < height; row++) {
      temp.add(new ArrayList<>());
      for (int column = 0; column < width; column++) {
        temp.get(row).add(new Color(colorRed.get(row).get(column), colorGreen.get(row).get(column),
            colorBlue.get(row).get(column)));
      }
    }
    assertEquals(height, b.size());
    assertEquals(width, b.get(0).size());
    assertEquals(b, temp);
    assertEquals(new PPM(b), new PPM(temp));
  }
}
import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import model.Coloring;
import model.ImagingOps.ImagingOperation;
import model.ImagingOps.PPM.ColoringOperationPPM;
import model.ImageRepresentation.PPM.PPM;
import model.ImageRepresentation.util.Arithmetic;
import model.Matrices;
import org.junit.Test;

/**
 *  * Class containing tests for ColoringOperation members.
 */
public class ColoringOperationPPMTest {

  ImagingOperation a = new ColoringOperationPPM(new PPM(10, 10));

  @Test(expected = IllegalArgumentException.class)
  public void testNullConstructor() {
    ImagingOperation a = new ColoringOperationPPM(null);
  }

  @Test
  public void testHelperColoring() {

    List<List<Color>> b = a.helperForColoringAndFiltering(Matrices.MATRIX_FOR_GRAY_SCALING
        .getMatrix());
    PPM ppm = new PPM(10, 10);
    List<List<Integer>> red = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> green = ppm.getColorChannel(Coloring.GREEN);
    List<List<Integer>> blue = ppm.getColorChannel(Coloring.BLUE);

    List<List<Integer>> colorRed = Arithmetic.helperForMultiplyingEigen(Coloring.RED, red, green,
        blue, Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix());
    List<List<Integer>> colorBlue = Arithmetic.helperForMultiplyingEigen(Coloring.GREEN, red, green,
        blue, Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix());
    List<List<Integer>> colorGreen = Arithmetic.helperForMultiplyingEigen(Coloring.BLUE, red, green,
        blue, Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix());

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
  public void testHelperColoring2() {

    List<List<Color>> b = a.helperForColoringAndFiltering(Matrices.MATRIX_FOR_SEPIA
        .getMatrix());
    PPM ppm = new PPM(10, 10);
    List<List<Integer>> red = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> green = ppm.getColorChannel(Coloring.GREEN);
    List<List<Integer>> blue = ppm.getColorChannel(Coloring.BLUE);

    List<List<Integer>> colorRed = Arithmetic.helperForMultiplyingEigen(Coloring.RED, red, green,
        blue, Matrices.MATRIX_FOR_SEPIA.getMatrix());
    List<List<Integer>> colorBlue = Arithmetic.helperForMultiplyingEigen(Coloring.BLUE, red, green,
        blue, Matrices.MATRIX_FOR_SEPIA.getMatrix());
    List<List<Integer>> colorGreen = Arithmetic
        .helperForMultiplyingEigen(Coloring.GREEN, red, green,
            blue, Matrices.MATRIX_FOR_SEPIA.getMatrix());

    int height = colorRed.size();
    int width = colorRed.get(0).size();
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
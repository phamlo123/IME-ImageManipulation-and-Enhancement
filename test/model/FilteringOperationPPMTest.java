package model;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import model.enumTypes.Coloring;
import model.ImageRepresentation.Image;
import model.ImageRepresentation.ImageFormat;
import model.ImagingOps.ImagingOperation;
import model.ImagingOps.ColoringOperation;
import model.ImagingOps.FilteringOperation;
import model.enumTypes.Matrices;
import model.util.Arithmetic;
import org.junit.Test;

/***
 * Class containing tests for FilteringOperation members.
 */
public class FilteringOperationPPMTest {

  ImagingOperation a = new FilteringOperation(new Image(10, 10));

  @Test(expected = IllegalArgumentException.class)
  public void testNullConstructor() {
    ImagingOperation a = new ColoringOperation(null);
  }

  @Test
  public void testHelperFiltering() {

    List<List<Color>> b = a.helperForColoringAndFiltering(Matrices.MATRIX_FOR_BLURRING
        .getMatrix());
    ImageFormat ppm = new Image(10, 10);
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
    assertEquals(new Image(b), new Image(temp));
  }


  @Test
  public void testHelperFiltering2() {

    List<List<Color>> b = a.helperForColoringAndFiltering(Matrices.MATRIX_FOR_SHARPENING
        .getMatrix());
    ImageFormat ppm = new Image(10, 10);
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
    assertEquals(new Image(b), new Image(temp));
  }
}
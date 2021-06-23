package model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.List;
import model.enums.Coloring;
import model.imagerepresentation.Image;
import model.imagerepresentation.ImageFormat;
import model.util.ImageUtil;
import org.junit.Test;

/**
 * Class that contains tests for ImageUtil static methods.
 */
public class ImageUtilTest {

  @Test
  public void testGetList() {
    ImageFormat ppm = new Image("sample.ppm");
    List<List<Integer>> red = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> green = ppm.getColorChannel(Coloring.GREEN);
    List<List<Integer>> blue = ppm.getColorChannel(Coloring.BLUE);
    List<List<Color>> a = ImageUtil.getLists(red, green, blue);
    assertEquals(new Image(a), ppm);
  }

  @Test
  public void testGetList3() {
    ImageFormat ppm = new Image();
    List<List<Integer>> red = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> green = ppm.getColorChannel(Coloring.GREEN);
    List<List<Integer>> blue = ppm.getColorChannel(Coloring.BLUE);
    List<List<Color>> a = ImageUtil.getLists(red, green, blue);
    assertEquals(new Image(a), ppm);
  }


}
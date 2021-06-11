import static org.junit.Assert.*;

import java.awt.Color;
import java.util.List;
import model.Coloring;
import model.ImageRepresentation.PPM;
import model.ImageRepresentation.util.ImageUtil;
import org.junit.Test;

public class ImageUtilTest {

  @Test
  public void testGetList() {
    PPM ppm = ImageUtil.importImageFile("Koala.ppm");
    List<List<Integer>> red = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> green = ppm.getColorChannel(Coloring.GREEN);
    List<List<Integer>> blue = ppm.getColorChannel(Coloring.BLUE);
    List<List<Color>> a = ImageUtil.getLists(red, green, blue);
    assertEquals(new PPM(a), ppm);
  }

  @Test
  public void testGetList3() {
    PPM ppm = new PPM();
    List<List<Integer>> red = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> green = ppm.getColorChannel(Coloring.GREEN);
    List<List<Integer>> blue = ppm.getColorChannel(Coloring.BLUE);
    List<List<Color>> a = ImageUtil.getLists(red, green, blue);
    assertEquals(new PPM(a), ppm);
  }

}
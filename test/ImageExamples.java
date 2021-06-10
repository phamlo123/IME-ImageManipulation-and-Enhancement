import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.ImageImpl;
import model.ImageRepresentation.ImageImplPPM;
import model.ImageRepresentation.PPM;
import model.Images;

/**
 * Class to hold the examples used by multiple test classes.
 */
public class ImageExamples {
  PPM ppmExample1 = PPM.importImageFile("Koala.ppm");
  PPM ppmExample2 = PPM.importImageFile("Northeastern.ppm");
  PPM ppmExample3 = PPM.importImageFile("Koala.ppm");
  PPM ppmExample4 = new PPM(10, 10);

  public static List<List<Color>> checkerboard() {
    List<List<Color>> checkerboard = new ArrayList<>();
    boolean isWhite = true;
    for (int i = 0; i < 10; i++) {
      checkerboard.add(new ArrayList<>());
      for (int j = 0; j < 10; j++) {
        if (isWhite) {
          checkerboard.get(i).add(Color.WHITE);
          isWhite = false;
        } else {
          checkerboard.get(i).add(Color.BLACK);
          isWhite = true;
        }
      }
      isWhite = !isWhite;
    }
    return checkerboard;
  }

  public static List<List<Integer>> checkerboardRGB() {
    List<List<Integer>> checkerboardRGB = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      checkerboardRGB.add(Arrays.asList(255, 0, 255, 0, 255, 0, 255, 0, 255, 0));
    }
    return checkerboardRGB;
  }


  Images<PPM> ppmImage1 = new ImageImplPPM(ppmExample4);
}

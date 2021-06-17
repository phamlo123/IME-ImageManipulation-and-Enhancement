import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.ImageRepresentation.ImageImplPPM;
import model.ImageRepresentation.PPM;
import model.ImageRepresentation.ImageFormat;
import model.ImageRepresentation.util.ImageUtil;
import model.Images;

/**
 * Class to hold the examples used by multiple test classes.
 */
public class ImageExamples {

  ImageFormat ppmExample1 = PPM.importImageFile("sample.ppm");
  ImageFormat ppmExample2 = PPM.importImageFile("Northeastern.ppm");
  ImageFormat ppmExample3 = PPM.importImageFile("sample.ppm");
  ImageFormat ppmExample4 = new PPM(10, 10);

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
    for (int i = 0; i < 5; i++) {
      checkerboardRGB.add(Arrays.asList(255, 0, 255, 0, 255, 0, 255, 0, 255, 0));
      checkerboardRGB.add(Arrays.asList(0, 255, 0, 255, 0, 255, 0, 255, 0, 255));
    }
    return checkerboardRGB;
  }


  Images<ImageFormat> ppmImage1 = new ImageImplPPM(ppmExample4);
}

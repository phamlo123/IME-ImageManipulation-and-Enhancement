import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import model.Coloring;
import model.ImageRepresentation.ImageImplPPM;
import model.ImageRepresentation.PPM;
import model.ImageRepresentation.ImageFormat;
import model.ImageRepresentation.util.Arithmetic;
import model.ImageRepresentation.util.ImageUtil;
import model.Images;
import model.Matrices;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to test ImageImplPPM class methods
 */
public class ImageImplPPMTest {

  ImageExamples examples;

  @Before
  public void setup() {
    examples = new ImageExamples();
  }

  // createMonochrome tests

  // tests the normal functionality of the createMonochrome method with a black and white
  // checkerboard
  @Test
  public void testCreateMonochromeCheckerboard() {
    List<List<Integer>> red = examples.ppmExample4.getColorChannel(Coloring.RED);
    List<List<Integer>> blue = examples.ppmExample4.getColorChannel(Coloring.BLUE);
    List<List<Integer>> green = examples.ppmExample4.getColorChannel(Coloring.GREEN);

    assertEquals(red.size(), blue.size());
    assertEquals(blue.size(), green.size());
    List<List<Integer>> monoRed =
        Arithmetic.helperForMultiplyingEigen(Coloring.RED, red, green, blue,
            Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix());
    List<List<Integer>> monoBlue =
        Arithmetic.helperForMultiplyingEigen(Coloring.BLUE, red, green, blue,
            Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix());
    List<List<Integer>> monoGreen =
        Arithmetic.helperForMultiplyingEigen(Coloring.GREEN, red, green, blue,
            Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix());
    List<List<Color>> color = new ArrayList<>();

    int size = red.size();
    for (int i = 0; i < size; i++) {
      color.add(new ArrayList<>());
      for (int j = 0; j < red.get(i).size(); j++) {
        color.get(i).add(new Color(monoRed.get(i).get(j), monoGreen.get(i).get(j),
            monoBlue.get(i).get(j)));
      }
    }

    Images<ImageFormat> images = new ImageImplPPM(examples.ppmExample4);
    images.createMonochrome();

    assertEquals(new PPM(color), images.getImage());
  }

  // tests the normal functionality of the createMonochrome method with an imported image
  @Test
  public void testCreateMonochromeImported() {
    PPM ppm = PPM.importImageFile("sample.ppm");
    List<List<Integer>> red = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> blue = ppm.getColorChannel(Coloring.BLUE);
    List<List<Integer>> green = ppm.getColorChannel(Coloring.GREEN);

    assertEquals(red.size(), blue.size());
    assertEquals(blue.size(), green.size());

    List<List<Integer>> monoRed =
        Arithmetic.helperForMultiplyingEigen(Coloring.RED, red, green, blue,
            Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix());
    List<List<Integer>> monoBlue =
        Arithmetic.helperForMultiplyingEigen(Coloring.BLUE, red, green, blue,
            Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix());
    List<List<Integer>> monoGreen =
        Arithmetic.helperForMultiplyingEigen(Coloring.GREEN, red, green, blue,
            Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix());
    List<List<Color>> color = new ArrayList<>();

    int size = red.size();
    for (int i = 0; i < size; i++) {
      color.add(new ArrayList<>());
      for (int j = 0; j < red.get(i).size(); j++) {
        color.get(i).add(new Color(monoRed.get(i).get(j), monoGreen.get(i).get(j),
            monoBlue.get(i).get(j)));
      }
    }

    Images<ImageFormat> images = new ImageImplPPM(PPM.importImageFile("sample.ppm"));
    images.createMonochrome();
    assertFalse(images.getImage().equals(PPM.importImageFile("sample.ppm")));
    assertEquals(new PPM(color), images.getImage());
  }

  // createSepia tests

  // tests the normal functionality of the createSepia method with a black and white checkerboard
  @Test
  public void testCreateSepiaCheckerboard() {
    List<List<Integer>> red = examples.ppmExample4.getColorChannel(Coloring.RED);
    List<List<Integer>> blue = examples.ppmExample4.getColorChannel(Coloring.BLUE);
    List<List<Integer>> green = examples.ppmExample4.getColorChannel(Coloring.GREEN);

    assertEquals(red.size(), blue.size());
    assertEquals(blue.size(), green.size());
    List<List<Integer>> sepiaRed =
        Arithmetic.helperForMultiplyingEigen(Coloring.RED, red, green, blue,
            Matrices.MATRIX_FOR_SEPIA.getMatrix());
    List<List<Integer>> sepiaBlue =
        Arithmetic.helperForMultiplyingEigen(Coloring.BLUE, red, green, blue,
            Matrices.MATRIX_FOR_SEPIA.getMatrix());
    List<List<Integer>> sepiaGreen =
        Arithmetic.helperForMultiplyingEigen(Coloring.GREEN, red, green, blue,
            Matrices.MATRIX_FOR_SEPIA.getMatrix());
    List<List<Color>> color = new ArrayList<>();

    int size = red.size();
    for (int i = 0; i < size; i++) {
      color.add(new ArrayList<>());
      for (int j = 0; j < red.get(i).size(); j++) {
        color.get(i).add(new Color(sepiaRed.get(i).get(j), sepiaGreen.get(i).get(j),
            sepiaBlue.get(i).get(j)));
      }
    }

    Images<ImageFormat> images = new ImageImplPPM(examples.ppmExample4);
    images.createSepia();

    assertEquals(new PPM(color), images.getImage());
  }

  // tests the normal functionality of the createSepia method with an imported image
  @Test
  public void testCreateSepiaImported() {
    PPM ppm = PPM.importImageFile("Northeastern.ppm");
    List<List<Integer>> red = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> blue = ppm.getColorChannel(Coloring.BLUE);
    List<List<Integer>> green = ppm.getColorChannel(Coloring.GREEN);

    assertEquals(red.size(), blue.size());
    assertEquals(blue.size(), green.size());
    List<List<Integer>> sepiaRed =
        Arithmetic.helperForMultiplyingEigen(Coloring.RED, red, green, blue,
            Matrices.MATRIX_FOR_SEPIA.getMatrix());
    List<List<Integer>> sepiaBlue =
        Arithmetic.helperForMultiplyingEigen(Coloring.BLUE, red, green, blue,
            Matrices.MATRIX_FOR_SEPIA.getMatrix());
    List<List<Integer>> sepiaGreen =
        Arithmetic.helperForMultiplyingEigen(Coloring.GREEN, red, green, blue,
            Matrices.MATRIX_FOR_SEPIA.getMatrix());
    List<List<Color>> color = new ArrayList<>();

    int size = red.size();
    for (int i = 0; i < size; i++) {
      color.add(new ArrayList<>());
      for (int j = 0; j < red.get(i).size(); j++) {
        color.get(i).add(new Color(sepiaRed.get(i).get(j), sepiaGreen.get(i).get(j),
            sepiaBlue.get(i).get(j)));
      }
    }

    Images<ImageFormat> images = new ImageImplPPM(PPM.importImageFile("Northeastern.ppm"));
    images.createSepia();
    assertFalse(images.getImage().equals(PPM.importImageFile("Northeastern.ppm")));

    assertEquals(new PPM(color), images.getImage());
  }

  // blurringImage tests

  // tests the normal functionality of the blurringImage method with a black and white checkerboard
  @Test
  public void testBlurringCheckerBoard() {
    List<List<Integer>> red = examples.ppmExample4.getColorChannel(Coloring.RED);
    List<List<Integer>> blue = examples.ppmExample4.getColorChannel(Coloring.BLUE);
    List<List<Integer>> green = examples.ppmExample4.getColorChannel(Coloring.GREEN);

    assertEquals(red.size(), blue.size());
    assertEquals(blue.size(), green.size());
    List<List<Integer>> blurredRed =
        Arithmetic.helperForMultiplying(red, Matrices.MATRIX_FOR_BLURRING.getMatrix());
    List<List<Integer>> blurredBlue =
        Arithmetic.helperForMultiplying(blue, Matrices.MATRIX_FOR_BLURRING.getMatrix());
    List<List<Integer>> blurredGreen =
        Arithmetic.helperForMultiplying(green, Matrices.MATRIX_FOR_BLURRING.getMatrix());
    List<List<Color>> color = new ArrayList<>();

    int size = red.size();
    for (int i = 0; i < size; i++) {
      color.add(new ArrayList<>());
      for (int j = 0; j < red.get(i).size(); j++) {
        color.get(i).add(new Color(blurredRed.get(i).get(j), blurredGreen.get(i).get(j),
            blurredBlue.get(i).get(j)));
      }
    }

    Images<ImageFormat> images = new ImageImplPPM(examples.ppmExample4);
    images.blurringImage();

    assertEquals(new PPM(color), images.getImage());
  }

  // tests the normal functionality of the blurringImage method with an imported image
  @Test
  public void testBlurringImported() {
    PPM ppm = PPM.importImageFile("sample.ppm");
    List<List<Integer>> red = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> blue = ppm.getColorChannel(Coloring.BLUE);
    List<List<Integer>> green = ppm.getColorChannel(Coloring.GREEN);

    assertEquals(red.size(), blue.size());
    assertEquals(blue.size(), green.size());

    List<List<Integer>> blurredRed =
        Arithmetic.helperForMultiplying(red, Matrices.MATRIX_FOR_BLURRING.getMatrix());
    List<List<Integer>> blurredBlue =
        Arithmetic.helperForMultiplying(blue, Matrices.MATRIX_FOR_BLURRING.getMatrix());
    List<List<Integer>> blurredGreen =
        Arithmetic.helperForMultiplying(green, Matrices.MATRIX_FOR_BLURRING.getMatrix());
    List<List<Color>> color = new ArrayList<>();

    int size = red.size();
    for (int i = 0; i < size; i++) {
      color.add(new ArrayList<>());
      for (int j = 0; j < red.get(i).size(); j++) {
        color.get(i).add(new Color(blurredRed.get(i).get(j), blurredGreen.get(i).get(j),
            blurredBlue.get(i).get(j)));
      }
    }

    Images<ImageFormat> images = new ImageImplPPM(PPM.importImageFile("sample.ppm"));
    images.blurringImage();
    assertFalse(images.getImage().equals(PPM.importImageFile("sample.ppm")));
    assertEquals(new PPM(color), images.getImage());
  }

  // sharpeningImage tests

  // tests the normal functionality of the sharpeningImage method with a black and white
  // checkerboard
  @Test
  public void testSharpeningCheckerBoard() {
    List<List<Integer>> red = examples.ppmExample4.getColorChannel(Coloring.RED);
    List<List<Integer>> blue = examples.ppmExample4.getColorChannel(Coloring.BLUE);
    List<List<Integer>> green = examples.ppmExample4.getColorChannel(Coloring.GREEN);

    assertEquals(red.size(), blue.size());
    assertEquals(blue.size(), green.size());
    List<List<Integer>> sharpedRed =
        Arithmetic.helperForMultiplying(red, Matrices.MATRIX_FOR_SHARPENING.getMatrix());
    List<List<Integer>> sharpedBlue =
        Arithmetic.helperForMultiplying(blue, Matrices.MATRIX_FOR_SHARPENING.getMatrix());
    List<List<Integer>> sharpedGreen =
        Arithmetic.helperForMultiplying(green, Matrices.MATRIX_FOR_SHARPENING.getMatrix());
    List<List<Color>> color = new ArrayList<>();

    int size = red.size();
    for (int i = 0; i < size; i++) {
      color.add(new ArrayList<>());
      for (int j = 0; j < red.get(i).size(); j++) {
        color.get(i).add(new Color(sharpedRed.get(i).get(j), sharpedGreen.get(i).get(j),
            sharpedBlue.get(i).get(j)));
      }
    }

    Images<ImageFormat> images = new ImageImplPPM(examples.ppmExample4);

    assertFalse(images.getImage().equals(new PPM(color)));
    images.sharpeningImage();
    assertEquals(new PPM(color), images.getImage());
  }

  // tests the normal functionality of the sharpeningImage method with an imported image
  @Test
  public void testSharpendingImported() {
    PPM ppm = PPM.importImageFile("Northeastern.ppm");
    List<List<Integer>> red = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> blue = ppm.getColorChannel(Coloring.BLUE);
    List<List<Integer>> green = ppm.getColorChannel(Coloring.GREEN);

    assertEquals(red.size(), blue.size());
    assertEquals(blue.size(), green.size());
    List<List<Integer>> sharpedRed =
        Arithmetic.helperForMultiplying(red, Matrices.MATRIX_FOR_SHARPENING.getMatrix());
    List<List<Integer>> sharpedBlue =
        Arithmetic.helperForMultiplying(blue, Matrices.MATRIX_FOR_SHARPENING.getMatrix());
    List<List<Integer>> sharpedGreen =
        Arithmetic.helperForMultiplying(green, Matrices.MATRIX_FOR_SHARPENING.getMatrix());
    List<List<Color>> color = new ArrayList<>();

    int size = red.size();
    for (int i = 0; i < size; i++) {
      color.add(new ArrayList<>());
      for (int j = 0; j < red.get(i).size(); j++) {
        color.get(i).add(new Color(sharpedRed.get(i).get(j), sharpedGreen.get(i).get(j),
            sharpedBlue.get(i).get(j)));
      }
    }

    Images<ImageFormat> images = new ImageImplPPM(PPM.importImageFile("Northeastern.ppm"));
    images.sharpeningImage();
    assertFalse(images.getImage().equals(PPM.importImageFile("Northeastern.ppm")));

    assertEquals(new PPM(color), images.getImage());
  }


  // tests the normal functionality of the sharpeningImage method with an imported image
  @Test
  public void testSharpendingImportedMultiple() {
    PPM ppm = PPM.importImageFile("Northeastern.ppm");
    List<List<Integer>> red = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> blue = ppm.getColorChannel(Coloring.BLUE);
    List<List<Integer>> green = ppm.getColorChannel(Coloring.GREEN);

    assertEquals(red.size(), blue.size());
    assertEquals(blue.size(), green.size());
    List<List<Integer>> sharpedRed =
        Arithmetic.helperForMultiplying(red, Matrices.MATRIX_FOR_SHARPENING.getMatrix());
    List<List<Integer>> sharpedBlue =
        Arithmetic.helperForMultiplying(blue, Matrices.MATRIX_FOR_SHARPENING.getMatrix());
    List<List<Integer>> sharpedGreen =
        Arithmetic.helperForMultiplying(green, Matrices.MATRIX_FOR_SHARPENING.getMatrix());

    List<List<Integer>> sharpedRed1 =
        Arithmetic.helperForMultiplying(sharpedRed, Matrices.MATRIX_FOR_SHARPENING.getMatrix());
    List<List<Integer>> sharpedBlue1 =
        Arithmetic.helperForMultiplying(sharpedBlue, Matrices.MATRIX_FOR_SHARPENING.getMatrix());
    List<List<Integer>> sharpedGreen1 =
        Arithmetic.helperForMultiplying(sharpedGreen, Matrices.MATRIX_FOR_SHARPENING.getMatrix());
    List<List<Color>> color = new ArrayList<>();

    int size = red.size();
    for (int i = 0; i < size; i++) {
      color.add(new ArrayList<>());
      for (int j = 0; j < red.get(i).size(); j++) {
        color.get(i).add(new Color(sharpedRed1.get(i).get(j), sharpedGreen1.get(i).get(j),
            sharpedBlue1.get(i).get(j)));
      }
    }

    Images<ImageFormat> images = new ImageImplPPM(PPM.importImageFile("Northeastern.ppm"));
    images.sharpeningImage();
    assertFalse(images.getImage().equals(new PPM(color)));
    images.sharpeningImage();
    assertFalse(images.getImage().equals(PPM.importImageFile("Northeastern.ppm")));

    assertEquals(new PPM(color), images.getImage());
  }

  // tests the normal functionality of the blurringImage method with an imported image
  @Test
  public void testBlurringImportedMultiple() {
    PPM ppm = PPM.importImageFile("sample.ppm");
    List<List<Integer>> red = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> blue = ppm.getColorChannel(Coloring.BLUE);
    List<List<Integer>> green = ppm.getColorChannel(Coloring.GREEN);

    assertEquals(red.size(), blue.size());
    assertEquals(blue.size(), green.size());

    List<List<Integer>> blurredRed =
        Arithmetic.helperForMultiplying(red, Matrices.MATRIX_FOR_BLURRING.getMatrix());
    List<List<Integer>> blurredBlue =
        Arithmetic.helperForMultiplying(blue, Matrices.MATRIX_FOR_BLURRING.getMatrix());
    List<List<Integer>> blurredGreen =
        Arithmetic.helperForMultiplying(green, Matrices.MATRIX_FOR_BLURRING.getMatrix());

    List<List<Integer>> blurredRed1 =
        Arithmetic.helperForMultiplying(blurredRed, Matrices.MATRIX_FOR_BLURRING.getMatrix());
    List<List<Integer>> blurredBlue1 =
        Arithmetic.helperForMultiplying(blurredBlue, Matrices.MATRIX_FOR_BLURRING.getMatrix());
    List<List<Integer>> blurredGreen1 =
        Arithmetic.helperForMultiplying(blurredGreen, Matrices.MATRIX_FOR_BLURRING.getMatrix());
    List<List<Color>> color = new ArrayList<>();

    int size = red.size();
    for (int i = 0; i < size; i++) {
      color.add(new ArrayList<>());
      for (int j = 0; j < red.get(i).size(); j++) {
        color.get(i).add(new Color(blurredRed1.get(i).get(j), blurredGreen1.get(i).get(j),
            blurredBlue1.get(i).get(j)));
      }
    }

    Images<ImageFormat> images = new ImageImplPPM(PPM.importImageFile("sample.ppm"));
    images.blurringImage();
    assertFalse(images.getImage().equals(new PPM(color)));
    images.blurringImage();
    assertFalse(images.getImage().equals(PPM.importImageFile("sample.ppm")));
    assertEquals(new PPM(color), images.getImage());
  }

  @Test
  public void testCreateSepiaImportedMultiple() {
    PPM ppm = PPM.importImageFile("Northeastern.ppm");
    List<List<Integer>> red = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> blue = ppm.getColorChannel(Coloring.BLUE);
    List<List<Integer>> green = ppm.getColorChannel(Coloring.GREEN);

    assertEquals(red.size(), blue.size());
    assertEquals(blue.size(), green.size());
    List<List<Integer>> sepiaRed =
        Arithmetic.helperForMultiplyingEigen(Coloring.RED, red, green, blue,
            Matrices.MATRIX_FOR_SEPIA.getMatrix());
    List<List<Integer>> sepiaBlue =
        Arithmetic.helperForMultiplyingEigen(Coloring.BLUE, red, green, blue,
            Matrices.MATRIX_FOR_SEPIA.getMatrix());
    List<List<Integer>> sepiaGreen =
        Arithmetic.helperForMultiplyingEigen(Coloring.GREEN, red, green, blue,
            Matrices.MATRIX_FOR_SEPIA.getMatrix());

    List<List<Integer>> sepiaRed1 =
        Arithmetic.helperForMultiplyingEigen(Coloring.RED, sepiaRed, sepiaGreen, sepiaBlue,
            Matrices.MATRIX_FOR_SEPIA.getMatrix());
    List<List<Integer>> sepiaBlue1 =
        Arithmetic.helperForMultiplyingEigen(Coloring.BLUE, sepiaRed, sepiaGreen, sepiaBlue,
            Matrices.MATRIX_FOR_SEPIA.getMatrix());
    List<List<Integer>> sepiaGreen1 =
        Arithmetic.helperForMultiplyingEigen(Coloring.GREEN, sepiaRed, sepiaGreen, sepiaBlue,
            Matrices.MATRIX_FOR_SEPIA.getMatrix());
    List<List<Color>> color = new ArrayList<>();

    int size = red.size();
    for (int i = 0; i < size; i++) {
      color.add(new ArrayList<>());
      for (int j = 0; j < red.get(i).size(); j++) {
        color.get(i).add(new Color(sepiaRed1.get(i).get(j), sepiaGreen1.get(i).get(j),
            sepiaBlue1.get(i).get(j)));
      }
    }

    Images<ImageFormat> images = new ImageImplPPM(PPM.importImageFile("Northeastern.ppm"));
    images.createSepia();
    assertFalse(images.getImage().equals(new PPM(color)));
    images.createSepia();
    assertFalse(images.getImage().equals(PPM.importImageFile("Northeastern.ppm")));

    assertEquals(new PPM(color), images.getImage());
  }

  // tests the normal functionality of the createMonochrome method with an imported image
  @Test
  public void testCreateMonochromeImportedMultiple() {
    PPM ppm = PPM.importImageFile("sample.ppm");
    List<List<Integer>> red = ppm.getColorChannel(Coloring.RED);
    List<List<Integer>> blue = ppm.getColorChannel(Coloring.BLUE);
    List<List<Integer>> green = ppm.getColorChannel(Coloring.GREEN);

    assertEquals(red.size(), blue.size());
    assertEquals(blue.size(), green.size());

    List<List<Integer>> monoRed =
        Arithmetic.helperForMultiplyingEigen(Coloring.RED, red, green, blue,
            Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix());
    List<List<Integer>> monoBlue =
        Arithmetic.helperForMultiplyingEigen(Coloring.BLUE, red, green, blue,
            Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix());
    List<List<Integer>> monoGreen =
        Arithmetic.helperForMultiplyingEigen(Coloring.GREEN, red, green, blue,
            Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix());

    List<List<Integer>> monoRed1 =
        Arithmetic.helperForMultiplyingEigen(Coloring.RED, monoRed, monoGreen, monoBlue,
            Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix());
    List<List<Integer>> monoBlue1 =
        Arithmetic.helperForMultiplyingEigen(Coloring.BLUE, monoRed, monoGreen, monoBlue,
            Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix());
    List<List<Integer>> monoGreen1 =
        Arithmetic.helperForMultiplyingEigen(Coloring.GREEN, monoRed, monoGreen, monoBlue,
            Matrices.MATRIX_FOR_GRAY_SCALING.getMatrix());
    List<List<Color>> color = new ArrayList<>();

    int size = red.size();
    for (int i = 0; i < size; i++) {
      color.add(new ArrayList<>());
      for (int j = 0; j < red.get(i).size(); j++) {
        color.get(i).add(new Color(monoRed1.get(i).get(j), monoGreen1.get(i).get(j),
            monoBlue1.get(i).get(j)));
      }
    }

    Images<ImageFormat> images = new ImageImplPPM(PPM.importImageFile("sample.ppm"));
    images.createMonochrome();
    assertFalse(images.getImage().equals(new PPM(color)));
    images.createMonochrome();
    assertFalse(images.getImage().equals(PPM.importImageFile("sample.ppm")));
    assertEquals(new PPM(color), images.getImage());
  }


}
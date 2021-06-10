import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import model.Coloring;
import model.ImageRepresentation.ImageImplPPM;
import model.ImageRepresentation.PPM;
import model.ImageRepresentation.util.Arithmetic;
import model.Images;
import model.Matrices;
import org.junit.Before;
import org.junit.Test;

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
    assertEquals(ImageExamples.checkerboard(), examples.ppmExample4.getImage());
    examples.ppmImage1.createMonochrome();
    assertEquals(ImageExamples.checkerboard(), examples.ppmExample4.getImage());
  }

  // createSepia tests

  // tests the normal functionality of the createSepia method with a black and white checkerboard
  @Test
  public void testCreateSepiaCheckerboard() {
    assertEquals(ImageExamples.checkerboard(), examples.ppmExample4.getImage());
    examples.ppmImage1.createSepia();
    assertEquals(ImageExamples.checkerboard(), examples.ppmExample4.getImage());
  }


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

    Images<PPM> images = new ImageImplPPM(examples.ppmExample4);
    images.blurringImage();

    assertEquals(new PPM(color), images.getImage());
  }


  @Test
  public void testBlurringImported() {
    PPM ppm = PPM.importImageFile("Koala.ppm");
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

    Images<PPM> images = new ImageImplPPM(PPM.importImageFile("Koala.ppm"));
    images.blurringImage();
    assertFalse(images.getImage().equals(PPM.importImageFile("Koala.ppm")));
    assertEquals(new PPM(color), images.getImage());
  }



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

    Images<PPM> images = new ImageImplPPM(examples.ppmExample4);

    assertFalse(images.getImage().equals(new PPM(color)));
    images.sharpeningImage();
    assertEquals(new PPM(color), images.getImage());
  }

  @Test
  public void testSharpeningImported() {
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

    Images<PPM> images = new ImageImplPPM(PPM.importImageFile("Northeastern.ppm"));
    images.sharpeningImage();
    assertFalse(images.getImage().equals(PPM.importImageFile("Northeastern.ppm")));

    assertEquals(new PPM(color), images.getImage());
  }



}
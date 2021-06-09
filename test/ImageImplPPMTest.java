import static org.junit.Assert.*;

import java.awt.Color;
import java.util.List;
import model.ImageRepresentation.PPM;
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
    assertEquals(ImageExamples.checkerboard(),examples.ppmExample4.getImage());
    examples.ppmImage1.createMonochrome();
    assertEquals(ImageExamples.checkerboard(), examples.ppmExample4.getImage());
  }

  // createSepia tests

  // tests the normal functionality of the createSepia method with a black and white checkerboard
  @Test
  public void testCreateSepiaCheckerboard() {
    assertEquals(ImageExamples.checkerboard(),examples.ppmExample4.getImage());
    examples.ppmImage1.createSepia();
    assertEquals(ImageExamples.checkerboard(), examples.ppmExample4.getImage());
  }
}
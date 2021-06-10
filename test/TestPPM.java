import static org.junit.Assert.*;

import model.Coloring;
import model.ImageRepresentation.PPM;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the PPM class and all of its methods function correctly
 */
public class TestPPM {

  ImageExamples examples;

  // initialize examples
  @Before
  public void setup() {
    examples = new ImageExamples();
  }

  // constructor tests

  // throws an exception because null is passed into the constructor
  @Test(expected = IllegalArgumentException.class)
  public void testPPMConstructorNull() {
    new PPM(null);
  }

  // export tests

  // tests the normal functionality of the export method on a checkerboard by importing it again
  // and checking the image properties
  @Test
  public void testExportNormal() {
    examples.ppmExample4.exportPPM("test.ppm");
    assertEquals(new PPM(ImageExamples.checkerboard()),PPM.importImageFile("test.ppm"));
  }

  // getImage tests

  // tests the normal functionality of the getImage method
  @Test
  public void testGetImageNormal() {
    assertEquals(ImageExamples.checkerboard(), examples.ppmExample4.getImage());
  }

  // equals tests

  // tests that the overridden equals method returns false when an object that's not a PPM object
  // is passed
  @Test
  public void testEqualsDifferentObjects() {
    assertFalse(examples.ppmExample1.equals("hello world"));
  }

  // tests that the overridden equals method returns false when a PPM file with different fields
  // is passed
  @Test
  public void testEqualsDifferentFields() {
    assertFalse(examples.ppmExample1.equals(examples.ppmExample2));
  }

  // tests that the overridden equals method returns false when a null object is passed
  @Test
  public void testEqualsNull() {
    assertFalse(examples.ppmExample1.equals(null));
  }

  // tests that the overridden equals method returns true when a copy PPM object is passed
  @Test
  public void testEqualsCopyPPM() {
    assertTrue(examples.ppmExample1.equals(examples.ppmExample3));
  }

  // tests that the overridden equals method returns true when the same PPM object is passed
  @Test
  public void testEqualsSamePPM() {
    assertTrue(examples.ppmExample1.equals(examples.ppmExample1));
  }


  // getColorChannel tests

  // tests the normal functionality of the getColorChannel method when red is passed
  @Test
  public void testGetColorChannelRed() {
    assertEquals(ImageExamples.checkerboardRGB(),
        examples.ppmExample4.getColorChannel(Coloring.RED));
  }

  // tests the normal functionality of the getColorChannel method when green is passed
  @Test
  public void testGetColorChannelGreen() {
    assertEquals(ImageExamples.checkerboardRGB(),
        examples.ppmExample4.getColorChannel(Coloring.GREEN));
  }

  // tests the normal functionality of the getColorChannel method when blue is passed
  @Test
  public void testGetColorChannelBlue() {
    assertEquals(ImageExamples.checkerboardRGB(),
        examples.ppmExample4.getColorChannel(Coloring.BLUE));
  }

  // throws an exception because the file name does not exist
  @Test(expected = IllegalArgumentException.class)
  public void testImportIllegal() {
    PPM.importImageFile("notExist.ppm");
  }

  // tests the normal functionality of the importImageFile method
  @Test
  public void testImport() {
    PPM ppm = new PPM();
    ppm.exportPPM("importTest.ppm");
    PPM ppmClone = PPM.importImageFile("importTest.ppm");
    assertEquals(ppm, ppmClone);
  }
  
}

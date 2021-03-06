package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.converter.Converter;
import model.enums.Coloring;
import model.imagerepresentation.Image;
import model.imagerepresentation.ImageFormat;
import model.enums.FileFormat;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to ensure that the PPM class and all of its methods function correctly.
 */
public class TestImage {

  ImageExamples examples;

  // initialize examples
  @Before
  public void setup() {
    examples = new ImageExamples();
  }

  // constructor tests

  // export tests

  // tests the normal functionality of the export method on a checkerboard by importing it again
  // and checking the image properties
  @Test
  public void testExportNormal() {
    examples.ppmExample4.getConverter().exportImage("test.ppm", FileFormat.PPM);
    assertEquals(new Image(ImageExamples.checkerboard()), new Image("test.ppm"));
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
    assertFalse(examples.ppmExample1 == null);
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
    new Image("notExist.ppm");
  }

  // tests the normal functionality of the importImageFile method
  @Test
  public void testImport() {
    ImageFormat ppm = new Image();
    ppm.getConverter().exportImage("importTest.ppm", FileFormat.PPM);
    ImageFormat ppmClone = new Image("importTest.ppm");
    assertEquals(ppm, ppmClone);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeight() {
    ImageFormat ppm = new Image(-1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroHeight() {
    ImageFormat ppm = new Image(0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidth() {
    ImageFormat ppm = new Image(1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test0Width() {
    ImageFormat ppm = new Image(1, 0);
  }


  @Test
  public void testImportFromConstructor() {
    Image image = new Image("abc.jpg");
    assertFalse(new Image().equals(image));
  }

  @Test
  public void testGetConverter() {
    Image image = new Image("Koala.ppm");
    Converter converter = image.getConverter();
    converter.exportImage("KoalaTest.ppm", FileFormat.PPM);
    assertEquals(image, new Image("KoalaTest.ppm"));
  }


}

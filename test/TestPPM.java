import static org.junit.Assert.*;

import model.ImageRepresentation.PPM;
import org.junit.Before;
import org.junit.Test;

public class TestPPM {

  PPM ppmExample1;
  PPM ppmExample2;
  PPM ppmExample3;

  @Before
  public void setup() {
    ppmExample1 = PPM.importImageFile("Koala.ppm");
    ppmExample2 = PPM.importImageFile("Northeastern.ppm");
    ppmExample3 = PPM.importImageFile("Koala.ppm");
  }

  // export tests

  // importImageFile tests

  // getImage tests

  // equals tests

  // tests that the overridden equals method returns false when an object that's not a PPM object
  // is passed
  @Test
  public void testEqualsDifferentObjects() {
    assertFalse(ppmExample1.equals("hello world"));
  }

  // tests that the overridden equals method returns false when a PPM file with different fields
  // is passed
  @Test
  public void testEqualsDifferentFields() {
    assertFalse(ppmExample1.equals(ppmExample2));
  }

  // tests that the overridden equals method returns false when a null object is passed
  @Test
  public void testEqualsNull() {
    assertFalse(ppmExample1.equals(null));
  }

  // tests that the overridden equals method returns true when a copy PPM object is passed
  @Test
  public void testEqualsCopyPPM() {
    assertTrue(ppmExample1.equals(ppmExample3));
  }

  // tests that the overridden equals method returns true when the same PPM object is passed
  @Test
  public void testEqualsSamePPM() {
    assertTrue(ppmExample1.equals(ppmExample1));
  }

  // hashcode tests

  // tests the normal functionality of the hashcode method with a PPM object
  @Test
  public void testHashcodeExample1() {
    assertEquals(1862422245,ppmExample1.hashCode());
  }

  // tests the normal functionality of the hashcode method with a copy of the first PPM object
  @Test
  public void testHashcodeCopyOfExample1() {
    assertEquals(1862422245,ppmExample3.hashCode());
  }

  // tests the normal functionality of the hashcode method with a second example
  @Test
  public void testHashcodeExample2() {
    assertEquals(1650984420,ppmExample2.hashCode());
  }

  // getColorChannel tests

}

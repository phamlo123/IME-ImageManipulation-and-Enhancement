package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.imagerepresentation.Image;
import model.imagerepresentation.ImageFormat;
import model.imagerepresentation.ImagesOperationsExtraImpl;
import org.junit.Test;

/**
 * This class provides tests for ImagesOperationsExtraImpl public methods.
 */
public class ImagesOperationsExtraImplTest {

  ImageFormat abc = new Image("abc.jpg");
  ImagesOperationsExtra a = new ImagesOperationsExtraImpl(abc);

  @Test
  public void downSize() {
    a.downSize(.5, .5);
    int size = abc.getImage().size();
    assertEquals(size / 2, a.getImage().getImage().size());
  }


  @Test(expected = IllegalArgumentException.class)
  public void downSizeIllegal() {
    a.downSize(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void downSizeIllegal2() {
    a.downSize(-.4, -.4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void downSizeIllegal3() {
    a.downSize(1.1, 2);
  }

  @Test
  public void createMosaic() {
    assertTrue(a.getImage().equals(abc));
    a.createMosaic(100);
    assertFalse(a.getImage().equals(abc));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMosaicIllegal() {
    a.createMosaic(-1);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testMosaicIllega2l() {
    a.createMosaic(-0);
  }
}
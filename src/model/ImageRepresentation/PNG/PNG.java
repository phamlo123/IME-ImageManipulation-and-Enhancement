package model.ImageRepresentation.PNG;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import model.Coloring;
import model.ImageRepresentation.ImageFormat;

public class PNG implements ImageFormat {

  private BufferedImage image;

  /**
   * @param fileName
   */
  @Override
  public void exportPPM(String fileName) {
    try {
      ImageIO.write(image, "png",new File(fileName));
    } catch (IOException e) {
      System.out.println("Error writing image to " + fileName);
    }
  }

  /**
   * Creates a copy of this PPM's image.
   *
   * @return the copy of this PPM's image as a list of list of colors
   */
  @Override
  public List<List<Color>> getImage() {
    return null;
  }

  /**
   * Returns a copy of this PPM's specified color channel.
   *
   * @param coloring the Coloring type desired
   * @return the color channel of this PPM as a list of list of Integer
   * @throws IllegalArgumentException if an invalid Coloring is passed
   */
  @Override
  public List<List<Integer>> getColorChannel(Coloring coloring) throws IllegalArgumentException {
    return null;
  }
}

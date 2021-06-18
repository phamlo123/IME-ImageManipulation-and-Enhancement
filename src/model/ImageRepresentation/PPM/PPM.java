package model.ImageRepresentation.PPM;

import java.awt.Color;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import model.Coloring;
import model.ImageRepresentation.Image;
import model.ImageRepresentation.ImageFormat;
import model.ImageRepresentation.util.ImageUtil;

/**
 *
 */
public class PPM extends Image implements ImageFormat {

  /**
   * Constructs a PPM from an image.
   *
   * @param image the list of list of color to create the PPM from
   */
  public PPM(List<List<Color>> image) {
    super(image);
  }

  /**
   * Creates a PPM with a checkerboard as its image.
   */
  public PPM() {
    super(1024, 768);
  }

  /**
   * Creates a PPM with a checkerboard with the specified height and width given as the image.
   *
   * @param height the height of the checkerboard image
   * @param width  the width of the checkerboard image
   */
  public PPM(int height, int width) {
    super(height, width);
  }


  /**
   * Exports the image stored in this PPM to the file name given.
   *
   * @param fileName the name of the file to write to
   */
  public void exportPPM(String fileName) {

    int height = getImage().size();
    int width = getImage().get(0).size();

    StringBuilder stringBuilder1 = new StringBuilder();

    stringBuilder1.append("P3\n");
    stringBuilder1.append(width).append(" ").append(height).append("\n");
    stringBuilder1.append("255\n");

    for (List<Color> colors : getImage()) {
      for (int j = 0; j < width; j++) {
        stringBuilder1.append(colors.get(j).getRed());
        stringBuilder1.append("\n");
        stringBuilder1.append(colors.get(j).getGreen());
        stringBuilder1.append("\n");
        stringBuilder1.append(colors.get(j).getBlue());
        stringBuilder1.append("\n");
      }
    }
    try {
      FileWriter fileWriter = new FileWriter(fileName);
      fileWriter.write(stringBuilder1.toString());
      fileWriter.close();
    } catch (IOException e) {
      System.out.print(e.getMessage());
    }
  }

  /**
   * Creates a new PPM object with the image at the desired file name.
   *
   * @param fileName the name of the file to import from
   * @return the created PPM object
   */
  public static PPM importImageFile(String fileName) {
    return new PPM(ImageUtil.readPPM(fileName));
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PPM ppm = (PPM) o;
    return Objects.equals(getImage(), ppm.getImage()) && Objects
        .equals(getColorChannel(Coloring.RED), ppm.getColorChannel(Coloring.RED)) && Objects
        .equals(getColorChannel(Coloring.GREEN), ppm.getColorChannel(Coloring.GREEN)) && Objects
        .equals(getColorChannel(Coloring.BLUE), ppm.getColorChannel(Coloring.BLUE));
  }

  @Override
  public int hashCode() {
    return Objects.hash(getImage(), getColorChannel(Coloring.RED), getColorChannel(Coloring.GREEN),
        getColorChannel(Coloring.BLUE));
  }


  @Override
  public void importImage(String fileName) {
  }

}

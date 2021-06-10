package model.ImageRepresentation;

import java.awt.Color;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Coloring;
import model.ImageRepresentation.util.ImageUtil;
import model.Images;

/**
 * this class contains information about image that is a PPM.
 */
public class PPM {

  private final List<List<Color>> image;
  private final List<List<Integer>> redChannel;
  private final List<List<Integer>> greenChannel;
  private final List<List<Integer>> blueChannel;

  public PPM(List<List<Color>> image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    this.image = image;
    this.greenChannel = setColoring(image, Coloring.GREEN);
    this.redChannel = setColoring(image, Coloring.RED);
    this.blueChannel = setColoring(image, Coloring.BLUE);

  }

  public PPM() {
    this.image = createListOfColor(1024, 768);
    this.greenChannel = setColoring(image, Coloring.GREEN);
    this.redChannel = setColoring(image, Coloring.RED);
    this.blueChannel = setColoring(image, Coloring.BLUE);
  }

  public PPM(int height, int width) {
    this.image = createListOfColor(height, width);
    this.greenChannel = setColoring(image, Coloring.GREEN);
    this.redChannel = setColoring(image, Coloring.RED);
    this.blueChannel = setColoring(image, Coloring.BLUE);
  }

  /**
   *
   * @param height
   * @param width
   * @return
   */
  private static List<List<Color>> createListOfColor(int height, int width) {
    boolean isWhite = true;
    List<List<Color>> temp = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      temp.add(new ArrayList<>());
      for (int j = 0; j < width; j++) {
        if (isWhite) {
          temp.get(i).add(Color.WHITE);
          isWhite = false;
        } else {
          temp.get(i).add(Color.BLACK);
          isWhite = true;
        }
      }
    }
    return temp;
  }

  /**
   * @return
   * @throws IOException
   */
  public String exportPPM() {
    StringBuilder stringBuilder = new StringBuilder();

    int height = this.image.size();
    int width = this.image.get(0).size();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        stringBuilder.append(String.format("(%d, %d) ", i, j));
        stringBuilder.append("(");
        stringBuilder.append(this.image.get(i).get(j).getRed());
        stringBuilder.append(", ");
        stringBuilder.append(this.image.get(i).get(j).getBlue());
        stringBuilder.append(", ");
        stringBuilder.append(this.image.get(i).get(j).getGreen());
        stringBuilder.append(")");
        stringBuilder.append("\n");
      }
    }

    StringBuilder stringBuilder1 = new StringBuilder();

    stringBuilder1.append("P3\n");
    stringBuilder1.append(width).append(" ").append(height).append("\n");
    stringBuilder1.append("255\n");

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        stringBuilder1.append(this.image.get(i).get(j).getRed());
        stringBuilder1.append("\n");
        stringBuilder1.append(this.image.get(i).get(j).getGreen());
        stringBuilder1.append("\n");
        stringBuilder1.append(this.image.get(i).get(j).getBlue());
        stringBuilder1.append("\n");
      }
    }
    try {
      FileWriter fileWriter = new FileWriter("s.ppm");
      fileWriter.write(stringBuilder1.toString());
      fileWriter.close();
    } catch (IOException e) {
      System.out.print(e.getMessage());
    }
    return stringBuilder.toString();
  }

  /**
   *
   * @param fileName
   * @return
   */
  public static PPM importImageFile(String fileName) {
    return new PPM(ImageUtil.readPPM(fileName));
  }

  public List<List<Color>> getImage() {
    List<List<Color>> temp = new ArrayList<>();
    for (int i = 0; i < image.size(); i++) {
      temp.add(new ArrayList<>());
      for (int j = 0; j < image.get(i).size(); j++) {
        temp.get(i).add(image.get(i).get(j));
      }
    }
    return temp;
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
    return Objects.equals(image, ppm.image) && Objects
        .equals(redChannel, ppm.redChannel) && Objects
        .equals(greenChannel, ppm.greenChannel) && Objects
        .equals(blueChannel, ppm.blueChannel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(image, redChannel, greenChannel, blueChannel);
  }

  /**
   *
   * @param image
   * @param color
   * @return
   */
  private List<List<Integer>> setColoring(List<List<Color>> image, Coloring color) {
    List<List<Integer>> temp = new ArrayList<>();
    int rowSize = image.size();
    for (int row = 0; row < rowSize; row++) {
      temp.add(new ArrayList<>());
      for (int column = 0; column < image.get(row).size(); column++) {
        switch (color) {
          case RED:
            temp.get(row).add(image.get(row).get(column).getRed());
            break;
          case BLUE:
            temp.get(row).add(image.get(row).get(column).getBlue());
            break;
          case GREEN:
            temp.get(row).add(image.get(row).get(column).getGreen());
            break;
        }
      }
    }
    return temp;
  }

  /**
   * @param coloring
   * @return
   * @throws IllegalStateException
   */
  public List<List<Integer>> getColorChannel(Coloring coloring) throws IllegalStateException {
    switch (coloring) {
      case BLUE:
        return this.blueChannel;
      case RED:
        return this.redChannel;
      case GREEN:
        return this.greenChannel;
      default:
        throw new IllegalArgumentException("Illegal Coloring");
    }
  }


  public static void main(String[] arg) throws IOException {
    PPM ppm3 = importImageFile("Koala.ppm");
    ppm3.exportPPM();
    PPM ppm4 = importImageFile("s.ppm");
    Images<PPM> t = new ImageImplPPM(ppm4);
    t.blurringImage();

    System.out.println(ppm3.equals(t.getImage()));
  }
}

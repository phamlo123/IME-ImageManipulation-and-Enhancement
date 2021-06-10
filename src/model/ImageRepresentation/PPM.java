package model.ImageRepresentation;

import java.awt.Color;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Coloring;
import model.ImageRepresentation.util.ImageUtil;

/**
 *
 */
public class PPM {

  private final List<List<Color>> image;
  private final List<List<Integer>> redChannel;
  private final List<List<Integer>> greenChannel;
  private final List<List<Integer>> blueChannel;

  /**
   * Constructs a PPM from an image.
   * @param image the list of list of color to create the PPM from
   */
  public PPM(List<List<Color>> image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    this.image = image;
    this.greenChannel = setColoring(image, Coloring.GREEN);
    this.redChannel = setColoring(image, Coloring.RED);
    this.blueChannel = setColoring(image, Coloring.BLUE);

  }

  /**
   * Creates a PPM with a checkerboard as its image.
   */
  public PPM() {
    this.image = createListOfColor(1024, 768);
    this.greenChannel = setColoring(image, Coloring.GREEN);
    this.redChannel = setColoring(image, Coloring.RED);
    this.blueChannel = setColoring(image, Coloring.BLUE);
  }

  /**
   * Creates a PPM with a checkerboard with the specified height and width given as the image.
   * @param height the height of the checkerboard image
   * @param width  the width of the checkerboard image
   */
  public PPM(int height, int width) {
    this.image = createListOfColor(height, width);
    this.greenChannel = setColoring(image, Coloring.GREEN);
    this.redChannel = setColoring(image, Coloring.RED);
    this.blueChannel = setColoring(image, Coloring.BLUE);
  }

  /**
   * Creates a list of list of colors that represents a checkerboard image.
   * @param height  the height of the checkerboard
   * @param width   the width of the checkerboard
   * @return the created checkerboard as a list of list of colors
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
      isWhite = !isWhite;
    }
    return temp;
  }

  /**
   * Exports the image stored in this PPM to the file name given.
   * @param fileName the name of the file to write to
   * @return a String containing all the pixels of this PPM's image
   */
  public String exportPPM(String fileName) {
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

    for (List<Color> colors : this.image) {
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
    return stringBuilder.toString();
  }

  /**
   * Creates a new PPM object with the image at the desired file name.
   * @param fileName the name of the file to import from
   * @return the created PPM object
   */
  public static PPM importImageFile(String fileName) {
    return new PPM(ImageUtil.readPPM(fileName));
  }

  /**
   * Creates a copy of this PPM's image.
   * @return the copy of this PPM's image as a list of list of colors
   */
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
   * Returns a list of list representing the values of each pixel for the specified color.
   * @param image the image to retrieve the color values of
   * @param color the color values to retrieve for each pixel
   * @return the list of list of the specified color values for each pixel
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
   * Returns a copy of this PPM's specified color channel.
   * @param coloring the Coloring type desired
   * @return the color channel of this PPM as a list of list of Integer
   * @throws IllegalArgumentException if an invalid Coloring is passed
   */
  public List<List<Integer>> getColorChannel(Coloring coloring) throws IllegalArgumentException {
    switch (coloring) {
      case BLUE:
        List<List<Integer>> tempBlue = new ArrayList<>();
        int size = this.blueChannel.size();
        for (int i = 0; i < size; i++) {
          tempBlue.add(new ArrayList<>());
          for (int j = 0; j < this.blueChannel.get(i).size(); j++) {
            tempBlue.get(i).add(this.blueChannel.get(i).get(j));
          }
        }
        return tempBlue;
      case RED:
        List<List<Integer>> tempRed = new ArrayList<>();
        int sizeRed = this.redChannel.size();
        for (int i = 0; i < sizeRed; i++) {
          tempRed.add(new ArrayList<>());
          for (int j = 0; j < this.redChannel.get(i).size(); j++) {
            tempRed.get(i).add(this.redChannel.get(i).get(j));
          }
        }
        return tempRed;
      case GREEN:
        List<List<Integer>> tempGreen = new ArrayList<>();
        int sizeGreen = this.greenChannel.size();
        for (int i = 0; i < sizeGreen; i++) {
          tempGreen.add(new ArrayList<>());
          for (int j = 0; j < this.greenChannel.get(i).size(); j++) {
            tempGreen.get(i).add(this.greenChannel.get(i).get(j));
          }
        }
        return tempGreen;
      default:
        throw new IllegalArgumentException("Illegal Coloring");
    }
  }

}

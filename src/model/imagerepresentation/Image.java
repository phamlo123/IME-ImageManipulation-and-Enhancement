package model.imagerepresentation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.enums.Coloring;
import model.converter.Converter;
import model.converter.SimpleConverter;
import model.util.ImageUtil;

/**
 * This class represents the program internal representation of an image.
 */
public class Image implements ImageFormat {

  private final Converter converter;
  private final List<List<Color>> image;
  private List<List<Integer>> redChannel;
  private List<List<Integer>> greenChannel;
  private List<List<Integer>> blueChannel;

  /**
   * Create an image with the given list of lists of color, which represents a RGB color value for
   * all pixels in the image.
   *
   * @param listOfColor is the list being analyzed
   */
  public Image(List<List<Color>> listOfColor) {
    if (listOfColor == null) {
      throw new IllegalArgumentException("list of color is null");
    }
    this.image = listOfColor;
    this.converter = getConverter();
    setColoring(image);
  }

  /**
   * Create image with height 1024 and width 768, which is a checkerboard of alternating color
   * value.
   */
  public Image() {
    this.image = createListOfColor(1024, 768);
    setColoring(image);
    this.converter = getConverter();
  }

  /**
   * Create an image with a given height and width, which is a checkerboard of alternating color
   * values.
   *
   * @param height of the image in pixel
   * @param width  of the image in pixel
   * @throws IllegalArgumentException if height or width is less than or equal to 0.
   */
  public Image(int height, int width) {
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("Illegal height or width length");
    } else {
      this.image = createListOfColor(height, width);
      setColoring(getImage());
      this.converter = getConverter();
    }
  }

  /**
   * Create an image from an imported image file with the given file name.
   *
   * @param fileName is the name of the image file
   */
  public Image(String fileName) throws IllegalArgumentException {
    this.converter = getImport(fileName);
    this.image = converter.getListOfColor();
    setColoring(getImage());
  }

  /**
   * Get the converter from the imported image file with the given file name.
   *
   * @param fileName is the name of the file
   * @return a Converted of the with the given imported File
   * @throws IllegalArgumentException if the file cannot be imported
   */
  private Converter getImport(String fileName) throws IllegalArgumentException {
    try {
      return new SimpleConverter(fileName);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Illegal Imported File");
    }
  }

  /**
   * Creates a list of list of colors that represents a checkerboard image.
   *
   * @param height the height of the checkerboard
   * @param width  the width of the checkerboard
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

  @Override
  public List<List<Color>> getImage() {
    List<List<Color>> temp = new ArrayList<>();
    int size = image.size();
    for (int i = 0; i < size; i++) {
      temp.add(new ArrayList<>());
      for (int j = 0; j < image.get(i).size(); j++) {
        temp.get(i).add(image.get(i).get(j));
      }
    }
    return temp;
  }

  /**
   * Returns a copy of this PPM's specified color channel.
   *
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


  /**
   * Returns a list of list representing the values of each pixel for the specified color.
   *
   * @param image the image to retrieve the color values of
   */
  private void setColoring(List<List<Color>> image) {
    List<List<Integer>> red = new ArrayList<>();
    List<List<Integer>> green = new ArrayList<>();
    List<List<Integer>> blue = new ArrayList<>();

    int rowSize = image.size();
    for (int row = 0; row < rowSize; row++) {
      red.add(new ArrayList<>());
      green.add(new ArrayList<>());
      blue.add(new ArrayList<>());

      for (int column = 0; column < image.get(row).size(); column++) {

        red.get(row).add(image.get(row).get(column).getRed());

        blue.get(row).add(image.get(row).get(column).getBlue());

        green.get(row).add(image.get(row).get(column).getGreen());
      }
    }

    this.greenChannel = green;
    this.redChannel = red;
    this.blueChannel = blue;
  }

  @Override
  public Converter getConverter() {
    return new SimpleConverter(ImageUtil.createBufferedImage(this.image));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Image image = (Image) o;
    List<List<Color>> colors = getImage();
    return colors.equals(image.getImage());
  }

  @Override
  public int hashCode() {
    System.out.println("hello");
    return Objects.hash(getImage());
  }

}

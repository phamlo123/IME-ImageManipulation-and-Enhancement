package model.ImageRepresentation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Coloring;
import model.converter.Converter;
import model.converter.SimpleConverter;
import model.util.ImageUtil;

public class Image implements ImageFormat {

  private Converter converter;
  private List<List<Color>> image;
  private List<List<Integer>> redChannel;
  private List<List<Integer>> greenChannel;
  private List<List<Integer>> blueChannel;

  public Image(List<List<Color>> listOfColor) {
    if (listOfColor == null) {
      throw new IllegalArgumentException();
    }
    this.image = listOfColor;
    setColoring(image);
  }

  public Image() {
    this.image = createListOfColor(1024, 768);
    setColoring(image);
    this.converter = getConverter();
  }


  public Image(int height, int width) {
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("Illegal height or width length");
    } else {
      this.image = createListOfColor(height, width);
      setColoring(getImage());
      this.converter = getConverter();
    }
  }

  public Image(String fileName) {
    if (!verifyImport(fileName)) {
      throw new IllegalArgumentException("Illegal Imported File");
    }
    this.converter = new SimpleConverter(fileName);
    this.image = converter.getListOfColor();
    setColoring(getImage());
  }


  private boolean verifyImport(String fileName) {
    try {
      Converter converter = new SimpleConverter(fileName);
    } catch (IllegalArgumentException e) {
      return false;
    }
    return true;
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
   * @return the list of list of the specified color values for each pixel
   */
  protected void setColoring(List<List<Color>> image) {
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
    return Objects.equals(getImage(), image.getImage()) && Objects
        .equals(getColorChannel(Coloring.RED), image.getColorChannel(Coloring.RED)) && Objects
        .equals(getColorChannel(Coloring.GREEN), image.getColorChannel(Coloring.GREEN)) && Objects
        .equals(getColorChannel(Coloring.BLUE), image.getColorChannel(Coloring.BLUE));
  }

  @Override
  public int hashCode() {
    return Objects.hash(getImage(), getColorChannel(Coloring.RED), getColorChannel(Coloring.GREEN),
        getColorChannel(Coloring.BLUE));
  }



}

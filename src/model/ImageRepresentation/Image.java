package model.ImageRepresentation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import model.Coloring;
import model.FileFormat;
import model.ImageRepresentation.JPEG.JPEG;
import model.ImageRepresentation.PNG.PNG;
import model.ImageRepresentation.PPM.PPM;

public abstract class Image implements ImageFormat {

  private BufferedImage bufferedImage;
  private List<List<Color>> image;
  private List<List<Integer>> redChannel;
  private List<List<Integer>> greenChannel;
  private List<List<Integer>> blueChannel;


  public Image(List<List<Color>> listOfColor) {
    if(listOfColor == null) {
      throw new IllegalArgumentException();
    }
    this.image = listOfColor;
    setColoring(image);
    this.bufferedImage = createBufferedImage(listOfColor);
  }

  public Image() {
    this.image = new ArrayList<>();
    setColoring(image);
    this.bufferedImage = createBufferedImage(image);
  }

  public Image(String fileName) {
    importImage(fileName);
    this.image = getListOfColor(bufferedImage);
    setColoring(image);
  }

  public Image(int height, int width) {
    if(height < 0 || width < 0) {
      throw new IllegalArgumentException();
    }
    this.image = createListOfColor(height, width);
    this.bufferedImage = createBufferedImage(image);
    setColoring(image);
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


  /**
   * Creates a copy of this PPM's image.
   *
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


  public ImageFormat convert(FileFormat fileFormat) throws IllegalArgumentException {
    ImageFormat imageFormat;
    switch (fileFormat) {
      case PPM:
        imageFormat = new PPM(this.getImage());
        break;
      case JPEG:
        imageFormat = new JPEG(this.getImage());
        break;
      case PNG:
        imageFormat = new PNG(this.getImage());
        break;
      default:
        throw new IllegalArgumentException();
    }
    return imageFormat;
  }


  @Override
  public void importImage(String fileName) {
    try {
      this.bufferedImage = ImageIO.read(new File(fileName));
    } catch (IOException e) {
      System.out.println("File " + fileName + " not found!");
    }
  }


  protected BufferedImage createBufferedImage(List<List<Color>> listOfColor) {
    int height = listOfColor.size();
    int width = listOfColor.get(0).size();
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        bufferedImage.setRGB(column, row, listOfColor.get(row).get(column).getRGB());
      }
    }
    return bufferedImage;
  }


  protected static List<List<Color>> getListOfColor(BufferedImage bufferedImage) {
    int height = bufferedImage.getHeight();
    int width = bufferedImage.getWidth();
    List<List<Color>> listOfColor = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      listOfColor.add(new ArrayList<>());
      for (int j = 0; j < width; j++) {
        listOfColor.get(i).add(new Color(bufferedImage.getRGB(i, j)));
      }
    }
    return listOfColor;
  }

  @Override
  public BufferedImage getBufferedImage() {
    return bufferedImage;
  }
}

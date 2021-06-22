package model.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is a function object class that contains methods to help process a ppm file.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format create a list of list of Colors from that PPM file.
   *
   * @param filename the path of the file.
   * @return a list of list of colors based on the information in the PPM file.
   */
  public static List<List<Color>> readPPM(String filename) throws IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("file new found");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("PPM file should start with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    List<List<Color>> image = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      image.add(new ArrayList<>());
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        image.get(i).add(new Color(r, g, b));
      }
    }
    return image;
  }


  /**
   * this method creates a List of Lists of Colors based on the given lists of red, green, and blue
   * value.
   *
   * @param red   is the list of list of red values.
   * @param green is the list of lists of green values.
   * @param blue  is the list of list of blue values.
   * @return a list of list of colors that is a combination of the three red, green, and blue
   *         parameters.
   */
  public static List<List<Color>> getLists(List<List<Integer>> red, List<List<Integer>> green,
      List<List<Integer>> blue) {
    List<List<Color>> temp = new ArrayList<>();

    int height = red.size();
    int width = red.get(0).size();

    for (int row = 0; row < height; row++) {
      temp.add(new ArrayList<>());
      for (int column = 0; column < width; column++) {
        temp.get(row).add(new Color(red.get(row).get(column), green.get(row).get(column),
            blue.get(row).get(column)));
      }
    }
    return temp;
  }

  /**
   * Create a buffered image from the given list of lists of colors.
   * @param listOfColor source list.
   * @return a list of list of colors.
   */
  public static BufferedImage createBufferedImage(List<List<Color>> listOfColor) {
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


  /**
   * Exports the image stored in this List of List of Colors to the file name given.
   *
   * @param fileName the name of the file to write to
   * @param image    is the list that contains color values for all pixels in the exported image
   */
  public static void exportPPM(String fileName, List<List<Color>> image) {

    int height = image.size();
    int width = image.get(0).size();

    StringBuilder stringBuilder1 = new StringBuilder();

    stringBuilder1.append("P3\n");
    stringBuilder1.append(width).append(" ").append(height).append("\n");
    stringBuilder1.append("255\n");

    for (List<Color> colors : image) {
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
   * Checks if the given object is null. If it is, throws an exception.
   *
   * @param object the object to be checked
   * @throws IllegalArgumentException if the given object is null
   */
  public static void checkNull(Object object) throws IllegalArgumentException {
    if (object == null) {
      throw new IllegalArgumentException("Cannot pass null parameters");
    }
  }
}
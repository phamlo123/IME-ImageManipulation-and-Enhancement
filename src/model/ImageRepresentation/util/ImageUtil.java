package model.ImageRepresentation.util;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.ImageRepresentation.PPM;

/**
 * This class is a function object class that contains methods to help process a ppm file
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format create a list of list of Colors from that PPM file.
   *
   * @param filename the path of the file.
   * @return a list of list of colors based on the information in the PPM file.
   */
  private static List<List<Color>> readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
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
   * Creates a new PPM object with the image at the desired file name.
   *
   * @param fileName the name of the file to import from
   * @return the created PPM object
   */
  public static PPM importImageFile(String fileName) {
    return new PPM(ImageUtil.readPPM(fileName));
  }


  /**
   * this method creates a List of Lists of Colors based on the given lists of red, green, and blue
   * value.
   *
   * @param red   is the list of list of red values.
   * @param green is the list of lists of green values.
   * @param blue  is the list of list of blue values.
   * @return a list of list of colors that is a combination of the three red, green, and blue
   * parameters.
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
}
package model.ImageRepresentation;

import java.awt.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Coloring;

/**
 * this class contains information about image that is a PPM.
 */
public class PPM {

  private List<List<Color>> image;
  private List<List<Integer>> redChannel;
  private List<List<Integer>> greenChannel;
  private List<List<Integer>> blueChannel;

  public static class ImageUtil {

    /**
     * Read an image file in the PPM format and print the colors.
     *
     * @param filename the path of the file.
     */
    public static List<List<Color>> readPPM(String filename) {
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
      System.out.println("Width of image: " + width);
      int height = sc.nextInt();
      System.out.println("Height of image: " + height);
      int maxValue = sc.nextInt();
      System.out.println("Maximum value of a color in this file (usually 256): " + maxValue);

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
  }



    public PPM(List<List<Color>> image) {
    this.image = image;
    this.greenChannel = setColoring(image, Coloring.GREEEN);
    this.redChannel = setColoring(image, Coloring.RED);
    this.blueChannel = setColoring(image, Coloring.BLUE);

  }

  public PPM() {
    this.image = createListOfColor();
    this.greenChannel = setColoring(image, Coloring.GREEEN);
    this.redChannel = setColoring(image, Coloring.RED);
    this.blueChannel = setColoring(image, Coloring.BLUE);
  }

  public static List<List<Color>> createListOfColor() {
    boolean isWhite = true;
    List<List<Color>> temp = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      temp.add(new ArrayList<>());
      for (int j = 0; j < 10; j++) {
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


  public String exportPPM() throws IOException {
    StringBuilder stringBuilder = new StringBuilder();
    StringBuilder stringBuilder1 = new StringBuilder();



    int height = this.image.size();
    int width = this.image.get(0).size();

    System.out.println(width);


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


    stringBuilder1.append("P3\n");
    stringBuilder1.append(width + " " + height + "\n");
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
    FileWriter obj = new FileWriter("s.ppm");
    obj.write(stringBuilder1.toString());
    obj.close();
    return stringBuilder.toString();
  }


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
    if (o == this) {
      return true;
    }
    if (!(o instanceof PPM)) {
      return false;
    } else {
      PPM temp = (PPM) o;
      return temp.getImage().equals(this.getImage());
    }
  }

  @Override
  public int hashCode() {
    return 0;
  }

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
          case GREEEN:
            temp.get(row).add(image.get(row).get(column).getGreen());
            break;
        }
      }
    }
    return temp;
  }


  public List<List<Integer>> getColorChannel(Coloring coloring) throws IllegalStateException {
    switch (coloring) {
      case BLUE:
        return this.blueChannel;
      case RED:
        return this.redChannel;
      case GREEEN:
        return this.greenChannel;
      default:
        throw new IllegalArgumentException("Illegal Coloring");
    }
  }


  public static void main(String[] arg) throws IOException {
    PPM ppm3 = importImageFile("Koala.ppm");
    ppm3.exportPPM();
  }


}

package model.ImageRepresentation;

import java.awt.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * this class contains information about image that is a PPM.
 */
public class PPM {

  private List<List<Color>> image;


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
      int height = sc.nextInt();
      int maxValue = sc.nextInt();

      List<List<Color>> image = new ArrayList<>();
      for (int i = 0; i < height; i++) {
        image.add(new ArrayList<>());
        for (int j = 0; j < width; j++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          image.get(i).add(new Color(r, b, g));
        }
      }
      return image;
    }

  }


  public PPM(List<List<Color>> image) {
    this.image = image;
  }

  public PPM() {
    this.image = new ArrayList<>();
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



  public String exportPPM() {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < this.image.size(); i++) {
      for (int j = 0; j < this.image.get(i).size(); j++) {
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
    return stringBuilder.toString();
  }

  public void importImageFile (String fileName) {
    this.image = PPM.ImageUtil.readPPM(fileName);
  }

  public List<List<Color>> getImage() {
    List<List<Color>> temp = new ArrayList<>();
    for(int i = 0; i<image.size(); i++) {
      temp.add(new ArrayList<>());
      for (int j = 0; j< image.get(i).size(); j++) {
        temp.get(i).add(image.get(i).get(j));
      }
    }
    return temp;
  }


  public static void main(String[] arg) {
    PPM ppm = new PPM(PPM.createListOfColor());
    String a = ppm.exportPPM();

    PPM ppm2 = new PPM();
    ppm2.importImageFile("koala.ppm");
    System.out.println(ppm2.getImage().toString());
  }


}

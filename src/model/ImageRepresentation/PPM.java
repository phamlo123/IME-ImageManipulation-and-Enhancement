package model.ImageRepresentation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * this class contains information about image that is a PPM.
 */
public class PPM {

  private List<List<Color>> image;



  public PPM(List<List<Color>> image) {
    this.image = image;
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

  public void importFile (String fileName) {
  }
  public static void main(String[] arg) {
    PPM ppm = new PPM(PPM.createListOfColor());
    String a = ppm.exportPPM();

    System.out.println(a);
  }
}

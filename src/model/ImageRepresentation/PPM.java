package model.ImageRepresentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * this class contains information about image that is a PPM.
 */
public class PPM {

  private File imageFile;
  private Scanner scanner;

  public PPM(String filename) {
    this.imageFile = new File(filename);
    try {
      this.scanner = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      return;
    }
  }



  public List<List<Double>> getPixelColor() {

    return null;
  }

  public void exportPPM() {
  }

  public void importPPM() {
  }


}

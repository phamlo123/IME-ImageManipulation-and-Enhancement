package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.ImageRepresentation.Image;
import model.ImageRepresentation.ImagesOperationsImpl;
import model.ImageRepresentation.ImageFormat;

/**
 * Class to hold the examples used by multiple test classes.
 */
public class ImageExamples {

  ImageFormat ppmExample1 = new Image("sample.ppm");
  ImageFormat ppmExample2 = new Image("Northeastern.ppm");
  ImageFormat ppmExample3 = new Image("sample.ppm");
  ImageFormat ppmExample4 = new Image(10, 10);

  public static List<List<Color>> checkerboard() {
    List<List<Color>> checkerboard = new ArrayList<>();
    boolean isWhite = true;
    for (int i = 0; i < 10; i++) {
      checkerboard.add(new ArrayList<>());
      for (int j = 0; j < 10; j++) {
        if (isWhite) {
          checkerboard.get(i).add(Color.WHITE);
          isWhite = false;
        } else {
          checkerboard.get(i).add(Color.BLACK);
          isWhite = true;
        }
      }
      isWhite = !isWhite;
    }
    return checkerboard;
  }

  public static List<List<Integer>> checkerboardRGB() {
    List<List<Integer>> checkerboardRGB = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      checkerboardRGB.add(Arrays.asList(255, 0, 255, 0, 255, 0, 255, 0, 255, 0));
      checkerboardRGB.add(Arrays.asList(0, 255, 0, 255, 0, 255, 0, 255, 0, 255));
    }
    return checkerboardRGB;
  }


  ImagesOperations ppmImage1 = new ImagesOperationsImpl(ppmExample4);
}

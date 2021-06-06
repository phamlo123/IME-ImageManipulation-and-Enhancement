package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Enum that contains all predefined matrices for image modification in the form of List of Lists.
 */
public enum Matrices {
  MATRIX_FOR_BLURRING(sth.LIST_A),
  MATRIX_FOR_SHARPENING(sth.LIST_B),
  MATRIX_FOR_GRAY_SCALING(sth.LIST_C),
  MATRIX_FOR_SEPIA(sth.LIST_D);

  private List<List<Double>> matrix;

  Matrices(List<List<Double>> matrix) {
    this.matrix = matrix;
  }

  static class sth {

    private static List<List<Double>> LIST_A = initialize1((double) 1 / 16);
    private static List<List<Double>> LIST_B = initialize2((double) -1 / 8);
    private static List<List<Double>> LIST_C = initialize3();
    private static List<List<Double>> LIST_D = initialize4();


    static private List<List<Double>> initialize1(Double d) {
      List<List<Double>> temp = new ArrayList<>();
      for (int i = 0; i < 3; i++) {
        temp.add(new ArrayList<>());
        for (int j = 0; j < 3; j++) {
          if (i == 1) {
            if (j == 1) {
              temp.get(i).add(j, d * 4);
            } else {
              temp.get(i).add(j, d * 2);
            }
          } else {
            temp.get(i).add(j, d);
          }
        }
      }
      temp.get(0).set(1, d*2);
      temp.get(2).set(1, d*2);
      return temp;
    }


    static private List<List<Double>> initialize2(Double d) {
      List<List<Double>> temp = new ArrayList<>();
      for (int i = 0; i < 5; i++) {
        temp.add(new ArrayList<>());
        for (int j = 0; j < 5; j++) {
          if (j == 2 && i == 2) {
            temp.get(i).add(j, d * (-8));
          } else if (i != 0 && i != 4 && j != 0 && j != 4) {
            temp.get(i).add(j, d * (-2));
          } else {
            temp.get(i).add(j, d);
          }
        }
      }
      return temp;
    }

    static private List<List<Double>> initialize3() {
      List<List<Double>> temp = new ArrayList<>();
      for (int i = 0; i < 3; i++) {
        temp.add(new ArrayList<>());
      }
      for (int j = 0; j < 3; j++) {
        temp.get(j).add(.2126);
        temp.get(j).add(.7152);
        temp.get(j).add(.0722);
      }
      return temp;
    }

    static private List<List<Double>> initialize4() {
      List<List<Double>> temp = new ArrayList<>();
      for (int i = 0; i < 3; i++) {
        temp.add(new ArrayList<>());
      }
      temp.get(0).add(0.393);
      temp.get(0).add(0.769);
      temp.get(0).add(0.189);
      temp.get(1).add(0.349);
      temp.get(1).add(0.686);
      temp.get(1).add(0.168);
      temp.get(2).add(0.272);
      temp.get(2).add(0.534);
      temp.get(2).add(0.131);
      return temp;
    }
  }

  public List<List<Double>> getMatrix() {
    return this.matrix;
  }

  public static void main(String[] arg) {

    System.out.println(MATRIX_FOR_GRAY_SCALING.getMatrix());
  }
}

package model;

import java.util.ArrayList;
import java.util.List;

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

    private static List<List<Double>> LIST_A;
    private static List<List<Double>> LIST_B;
    private static List<List<Double>> LIST_C;
    private static List<List<Double>> LIST_D;

    sth() {
      this.LIST_A = initialize((double) 1/16);
      this.LIST_B = initialize2();
      this.LIST_C = initialize3();
      this.LIST_D = initialize4();
    }

    static private List<List<Double>> initialize(Double d) {
      List<List<Double>> temp = new ArrayList<>();
      for (int i = 0; i < 3; i = i + 2) {
        temp.add(new ArrayList<>());
        for (int j = 0; j < 3; j = j + 2) {
          if(i == 1) {
            if (j == 1) {
              temp.get(i).add(d * 4);
            } else {
              temp.get(i).add( d * 2);
            }
          } else {
            temp.get(i).add(d);
          }
        }
      }
      return temp;
    }


    static private List<List<Double>> initialize2() {
      return null;
    }

    static private List<List<Double>> initialize3() {
      return null;
    }

    static private List<List<Double>> initialize4() {
      return null;
    }
  }
}

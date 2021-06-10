package model.ImageRepresentation.util;

import java.util.ArrayList;
import java.util.List;
import model.Coloring;

public class Arithmetic {


  /**
   * @param channel
   * @param matrix
   * @return
   */

  public static List<List<Integer>> helperForMultiplying(List<List<Integer>> channel,
      List<List<Double>> matrix) {
    int height = channel.size();
    int offset = (matrix.size() - 1) / 2;
    List<List<Integer>> temp = new ArrayList<>();
    for (int row = 0; row < height; row++) {
      temp.add(new ArrayList<>());
      for (int column = 0; column < channel.get(row).size(); column++) {
        double sum = 0;
        for (int i = 0; i < matrix.size(); i++) {
          for (int j = 0; j < matrix.get(i).size(); j++) {
            try {
              sum = sum + channel.get(row + i - offset).get(column + j - offset)
                  * matrix.get(i).get(j);
            } catch (IndexOutOfBoundsException e) {
            }
          }
        }
        int a = verifySum(sum, 255, 0);
        temp.get(row).add(a);
      }
    }
    System.out.println(offset);
    return temp;
  }


  /**
   * @param coloring
   * @param redChannel
   * @param greenChannel
   * @param blueChannel
   * @param matrix
   * @return
   * @throws IllegalArgumentException
   */
  public static List<List<Integer>> helperForMultiplyingEigen(Coloring coloring,
      List<List<Integer>> redChannel,
      List<List<Integer>> greenChannel, List<List<Integer>> blueChannel,
      List<List<Double>> matrix) throws IllegalArgumentException {
    List<List<Integer>> temp = new ArrayList<>();
    switch (coloring) {
      case RED:
        for (int i = 0; i < redChannel.size(); i++) {
          temp.add(new ArrayList<>());
          for (int j = 0; j < redChannel.get(i).size(); j++) {
            double sum = matrix.get(0).get(0) * redChannel.get(i).get(j)
                + matrix.get(0).get(1) * greenChannel.get(i).get(j)
                + matrix.get(0).get(2) * blueChannel.get(i).get(j);
            int a = Arithmetic.verifySum(sum, 255, 0);
            temp.get(i).add(a);
          }
        }
        return temp;
      case GREEN:
        for (int i = 0; i < greenChannel.size(); i++) {
          temp.add(new ArrayList<>());
          for (int j = 0; j < greenChannel.get(i).size(); j++) {
            double sum = matrix.get(1).get(0) * redChannel.get(i).get(j)
                + matrix.get(1).get(1) * greenChannel.get(i).get(j)
                + matrix.get(1).get(2) * blueChannel.get(i).get(j);
            int a = Arithmetic.verifySum(sum, 255, 0);
            temp.get(i).add(a);
          }
        }
        return temp;
      case BLUE:
        for (int i = 0; i < greenChannel.size(); i++) {
          temp.add(new ArrayList<>());
          for (int j = 0; j < greenChannel.get(i).size(); j++) {
            double sum = matrix.get(2).get(0) * redChannel.get(i).get(j)
                + matrix.get(2).get(1) * greenChannel.get(i).get(j)
                + matrix.get(2).get(2) * blueChannel.get(i).get(j);
            int a = Arithmetic.verifySum(sum, 255, 0);
            temp.get(i).add(a);
          }
        }
        return temp;
      default:
        throw new IllegalArgumentException();
    }
  }

  /**
   * @param sum
   * @return
   */
  public static int verifySum(Double sum, int upperLimit, int lowerLimit) {
    int a;
    if (sum > lowerLimit) {
      a = (int) Math.min(sum, upperLimit);
    } else {
      a = (int) Math.max(sum, lowerLimit);
    }
    return a;
  }
}

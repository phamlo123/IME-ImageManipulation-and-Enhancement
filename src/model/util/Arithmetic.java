package model.util;

import java.util.ArrayList;
import java.util.List;
import model.enums.Coloring;

/**
 * This class is a function object class that contain methods to process two or more matrices in the
 * form of list of list of objects.
 */
public class Arithmetic {


  /**
   * This method helps multiply two list of lists of Numbers that align the center of the kernel
   * with each of all elements of the main list of lists {see @link}.
   *
   * @param channel is the main list of lists of numbers that the kernel will be applied to
   * @param matrix  is the kernel matrix in the form of a list of list of numbers
   * @return a result List of list of Integers after kernel is applied for all the numbers in the
   * channel list.
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
              continue;
            }
          }
        }
        int a = verifySum(sum, 255, 0);
        temp.get(row).add(a);
      }
    }
    return temp;
  }


  /**
   * Method to multiply two matrices in the form of list of list of Integers and combine the results
   * for all of the integers in a new list of list of integers.
   *
   * @param coloring     the type of color that determines what color channel is being performed
   *                     on.
   * @param redChannel   is a list of list of numbers that represent the red value of all the pixels
   *                     in an image
   * @param greenChannel is a list of list of numbers that represent the green value of all the
   *                     pixels in an image
   * @param blueChannel  is a list of list of numbers that represent the green value of all the
   *                     pixels in an image
   * @param matrix       is the 3-by-3 matrix that will be used to apply to all the integers in the
   *                     given lists.
   * @return a list of list of integers after multiplication.
   * @throws IllegalArgumentException if the coloring type is not supported or matrix is not
   *                                  compatible (only 3 by 3 matrices are compatible).
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
            double sum;
            try {
              sum = matrix.get(0).get(0) * redChannel.get(i).get(j)
                  + matrix.get(0).get(1) * greenChannel.get(i).get(j)
                  + matrix.get(0).get(2) * blueChannel.get(i).get(j);
            } catch (IndexOutOfBoundsException e) {
              throw new IllegalArgumentException("Provided matrix is not compatible");

            }
            int a = Arithmetic.verifySum(sum, 255, 0);
            temp.get(i).add(a);
          }
        }
        return temp;
      case GREEN:
        for (int i = 0; i < greenChannel.size(); i++) {
          temp.add(new ArrayList<>());
          for (int j = 0; j < greenChannel.get(i).size(); j++) {
            double sum;
            try {
              sum = matrix.get(1).get(0) * redChannel.get(i).get(j)
                  + matrix.get(1).get(1) * greenChannel.get(i).get(j)
                  + matrix.get(1).get(2) * blueChannel.get(i).get(j);
            } catch (IndexOutOfBoundsException e) {
              throw new IllegalArgumentException("Provided matrix is not compatible");
            }
            int a = Arithmetic.verifySum(sum, 255, 0);
            temp.get(i).add(a);
          }
        }
        return temp;
      case BLUE:
        for (int i = 0; i < greenChannel.size(); i++) {
          temp.add(new ArrayList<>());
          for (int j = 0; j < greenChannel.get(i).size(); j++) {
            double sum;
            try {
              sum = matrix.get(2).get(0) * redChannel.get(i).get(j)
                  + matrix.get(2).get(1) * greenChannel.get(i).get(j)
                  + matrix.get(2).get(2) * blueChannel.get(i).get(j);
            } catch (IndexOutOfBoundsException e) {
              throw new IllegalArgumentException("Provided matrix is not compatible");
            }
            int a = Arithmetic.verifySum(sum, 255, 0);
            temp.get(i).add(a);
          }
        }
        return temp;
      default:
        throw new IllegalArgumentException("Color not supported");
    }
  }

  /**
   * This method sets assign upper and lower bound to a number and also turn that number into the
   * nearest lower integer.
   *
   * @param sum        is the number to be applied the bounds to
   * @param upperLimit is the upper bound
   * @param lowerLimit is the lower bound
   * @return an integer that is rounded down of a double number and is within the specified range.
   * @throws IllegalArgumentException if lowerbound is greater than upperbound
   */
  public static int verifySum(double sum, int upperLimit, int lowerLimit)
      throws IllegalArgumentException {
    if (lowerLimit > upperLimit) {
      throw new IllegalArgumentException("Lower limit is greater than upper limit");
    }
    int a;
    if (sum > lowerLimit) {
      a = (int) Math.min(sum, upperLimit);
    } else {
      a = (int) Math.max(sum, lowerLimit);
    }
    return a;
  }


  public static List<Integer> averageValue(List<Integer> integers) {
    int total = 0;
    int size = integers.size();
    for (int i = 0; i < size; i++) {
      total = total + integers.get(i);
    }
    int result = total / size;

    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      list.add(result);
    }

    return list;
  }

}

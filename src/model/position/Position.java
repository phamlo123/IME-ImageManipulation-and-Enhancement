package model.position;

import java.util.List;

/**
 * This interface provides methods for relative positions.
 */
public interface Position {

  /**
   * Get distance from this position to the given position.
   * @param position2D is the position to be compared with this
   * @return the absolute distance from this position to the given position.
   */
  double getDistanceFrom(Position position2D);

  /**
   * Get the nearest position to this position in the given list of positions.
   * @param position2DList is the list of positions
   * @return the position in the list that is closest of this position.
   */
  Position getNearestPosition(List<Position> position2DList);

  /**
   * Get the Color Integer Value of the Pixel that has relative position in the list of lists that
   * is the same as this position.
   * @param listOfColor is the list of lists of Integers that represent the pixels and their
   *                    respective color values.
   * @return the integer that represents the color of the pixel that is in this position.
   */
  Integer getColorPos(List<List<Integer>> listOfColor);

  /**
   * Get the x-value of this position.
   * @return the x-value of this position.
   */
  int getX();

  /**
   * Get the y-value of this position.
   * @return the y-value of this position.
   */
  int getY();
}

package model.position;

import java.util.List;

/**
 *
 */
public interface Position {

  /**
   *
   * @param position2D
   * @return
   */
  double getDistanceFrom(Position2D position2D);

  /**
   *
   * @param position2DList
   * @return
   */
  Position2D getNearestPosition(List<Position2D> position2DList);

  /**
   *
   * @param listOfColor
   * @return
   */
  Integer getColorPos(List<List<Integer>> listOfColor);

  /**
   *
   * @return
   */
  int getX();

  /**
   *
   * @return
   */
  int getY();
}

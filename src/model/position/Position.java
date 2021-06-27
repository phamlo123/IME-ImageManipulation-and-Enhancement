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
  double getDistanceFrom(Position position2D);

  /**
   *
   * @param position2DList
   * @return
   */
  Position getNearestPosition(List<Position> position2DList);

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

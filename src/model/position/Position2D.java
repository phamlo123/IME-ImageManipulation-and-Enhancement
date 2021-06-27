package model.position;

import java.util.List;
import java.util.Objects;

/**
 * This class provides implementation for Position interface and is a way to represent 2-D position
 * of a Pixel. It also implements the Comparable interface that can be used to rank position from
 * low to high.
 */
public class Position2D implements Comparable<Position>, Position {

  private int x;
  private int y;

  /**
   * Construct a 2D position with the given x and y coordinates.
   *
   * @param x is the value of x coordinates
   * @param y is the value of y coordinates
   */
  public Position2D(int x, int y) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException();
    }
    this.x = x;
    this.y = y;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public double getDistanceFrom(Position position2D) {
    double distanceX = Math.pow(x - position2D.getX(), 2);
    double distanceY = Math.pow(y - position2D.getY(), 2);
    double distance = Math.sqrt(distanceX + distanceY);
    return distance;
  }

  @Override
  public Position getNearestPosition(List<Position> position2DList) {
    Position nearest = position2DList.get(0);
    for (Position pos : position2DList) {
      if (pos.getDistanceFrom(this) < nearest.getDistanceFrom(this)) {
        nearest = pos;
      }
    }
    return nearest;
  }

  @Override
  public Integer getColorPos(List<List<Integer>> listOfColor) {
    try {
      return listOfColor.get(y).get(x);
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException();
    }
  }


  @Override
  public boolean equals(Object position2D) {
    if (position2D == null) {
      return false;
    }
    if (!(position2D instanceof Position2D)) {
      return false;
    }
    Position2D pos = (Position2D) position2D;
    return this.x == pos.getX() && this.y == pos.getY();
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }


  @Override
  public int compareTo(Position o) {
    if (o.equals(this)) {
      return 0;
    }
    double distanceFrom0 = o.getDistanceFrom(new Position2D(0, 0));
    double distanceFrom1 = this.getDistanceFrom(new Position2D(0, 0));
    int result = (int) Math.round(distanceFrom1 - distanceFrom0);
    return result;
  }


}

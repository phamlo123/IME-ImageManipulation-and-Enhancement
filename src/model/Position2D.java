package model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Position2D implements Comparable<Position2D> {

  private int x;
  private int y;

  public Position2D(int x, int y) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException();
    }
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public double getDistanceFrom(Position2D position2D) {
    double distanceX = Math.pow(x - position2D.getX(), 2);
    double distanceY = Math.pow(y - position2D.getY(), 2);
    double distance = Math.sqrt(distanceX + distanceY);
    return distance;
  }

  public Position2D getNearestPosition(List<Position2D> position2DList) {
    Position2D nearest = position2DList.get(0);
    for (Position2D pos : position2DList) {
      if (pos.getDistanceFrom(this) < nearest.getDistanceFrom(this)) {
        nearest = pos;
      }
    }
    return nearest;
  }


  public Position2D getClosestNum(List<Position2D> list) {
    Arrays.sort(list.toArray());
    int low = 0;
    int high = list.size() - 1;

    if (high < 0) {
      throw new IllegalArgumentException("The array cannot be empty");
    }

    while (low < high) {
      int mid = (low + high) / 2;
      assert (mid < high);
      int d1 = Math.abs(this.compareTo(list.get(mid)));
      int d2 = Math.abs(this.compareTo(list.get(mid + 1)));
      if (d2 <= d1) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }
    return list.get(high);
  }

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
  public int compareTo(Position2D o) {
    if (o.equals(this)) {
      return 0;
    }
    double distanceFrom0 = o.getDistanceFrom(new Position2D(0, 0));
    double distanceFrom1 = this.getDistanceFrom(new Position2D(0, 0));
    int result = (int) Math.round(distanceFrom1 - distanceFrom0);
    return result;
  }

  public static Comparator<Position2D> position2DComparator = new Comparator<Position2D>() {
    @Override
    public int compare(Position2D o1, Position2D o2) {
      return o1.compareTo(o2);
    }
  };


}

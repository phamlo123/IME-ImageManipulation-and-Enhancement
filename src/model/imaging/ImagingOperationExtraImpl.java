package model.imaging;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import model.position.Position;
import model.position.Position2D;
import model.enums.Coloring;
import model.imagerepresentation.ImageFormat;
import model.util.Arithmetic;
import model.util.ImageUtil;

/**
 * This class implements the ImagingOperationExtra and extends the ColoringOperation class in order
 * to reuse some of the methods already defined in that class.
 */
public class ImagingOperationExtraImpl extends ColoringOperation implements ImagingOperationExtra {

  public ImagingOperationExtraImpl(ImageFormat imageFormat) {
    super(imageFormat);
  }


  @Override
  public List<List<Color>> downSize(double ratioWidth, double ratioHeight) {
    List<List<Color>> image = this.imageObject.getImage();
    int newHeight = (int) Math.round(image.size() * ratioHeight);
    int newWidth = (int) Math.round(image.get(0).size() * ratioWidth);

    List<List<Integer>> red = helpMulti(newHeight, newWidth, Coloring.RED, ratioHeight, ratioWidth);
    List<List<Integer>> green = helpMulti(newHeight, newWidth, Coloring.GREEN, ratioHeight,
        ratioWidth);
    List<List<Integer>> blue = helpMulti(newHeight, newWidth, Coloring.BLUE, ratioHeight,
        ratioWidth);
    return ImageUtil.getLists(red, green, blue);
  }


  private List<List<Integer>> helpMulti(int newHeight, int newWidth, Coloring coloring,
      double ratioHeight, double ratioWidth) throws IllegalArgumentException {
    List<List<Integer>> list;
    switch (coloring) {
      case RED:
        list = new ArrayList<>(redChannel);
        break;
      case GREEN:
        list = new ArrayList<>(greenChannel);
        break;
      case BLUE:
        list = new ArrayList<>(blueChannel);
        break;
      default:
        throw new IllegalArgumentException();
    }
    List<List<Integer>> temp = new ArrayList<>();
    for (int i = 0; i < newHeight; i++) {
      temp.add(new ArrayList<>());
      for (int j = 0; j < newWidth; j++) {
        double y = (1 / ratioHeight) * i;
        double x = (1 / ratioWidth) * j;
        int downY = (int) Math.round(y);
        int downX = (int) Math.round(x);
        int upY = downY + 1;
        int upX = downX + 1;
        int a = list.get(downY).get(downX);
        int b = list.get(downY).get(upX);
        int c = list.get(upY).get(downX);
        int d = list.get(upY).get(upX);

        double m = (b * (x - downX)) + (a * (upX - x));
        double n = (d * (x - downX)) + (c * (upX - x));

        int color = (int) (n * (y - downY) + m * (upY - y));
        temp.get(i).add(color);
      }
    }
    return temp;
  }


  @Override
  public List<List<Color>> createMosaic(int numSeed) {
    List<List<Color>> image = this.imageObject.getImage();
    int height = image.size();
    int width = image.get(0).size();

    List<Integer> randomListX = getRandom(numSeed, 0, width - 1);
    List<Integer> randomListY = getRandom(numSeed, 0, height - 1);
    List<Position> position2DListSeed = new ArrayList<>();

    for (int i = 0; i < randomListX.size(); i++) {
      position2DListSeed.add(new Position2D(randomListX.get(i), randomListY.get(i)));
    }

    List<Position> position2DList = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        position2DList.add(new Position2D(j, i));
      }
    }

    List<Position> newPos = new ArrayList<>();
    for (Position position : position2DList) {
      newPos.add(position.getNearestPosition(position2DListSeed));
    }

    Map<Position, List<Position>> map = new HashMap<>();
    for (int i = 0; i < position2DList.size(); i++) {
      if (!map.containsKey(newPos.get(i))) {
        List<Position> list = new ArrayList<>();
        list.add(position2DList.get(i));
        map.put(newPos.get(i), list);
      } else {
        List<Position> list = map.get(newPos.get(i));
        list.add(position2DList.get(i));
        map.put(newPos.get(i), list);
      }
    }

    updateNewColor(map, redChannel);
    updateNewColor(map, greenChannel);
    updateNewColor(map, blueChannel);

    return ImageUtil.getLists(redChannel, greenChannel, blueChannel);
  }


  private void updateNewColor(Map<Position, List<Position>> map,
      List<List<Integer>> listOfColor) {
    for (Position key : map.keySet()) {
      List<Position> listPos = map.get(key);
      List<Integer> l = new ArrayList<>();
      for (Position pos : listPos) {
        l.add(pos.getColorPos(listOfColor));
      }

      List<Integer> list = Arithmetic.averageValue(l);

      for (int i = 0; i < listPos.size(); i++) {
        listOfColor.get(listPos.get(i).getY()).set(listPos.get(i).getX(), list.get(i));
      }
    }

  }

  private List<Integer> getRandom(int numSeed, int min, int max) {
    List<Integer> temp = new ArrayList<>(numSeed);
    Random random = new Random();
    for (int i = 0; i < numSeed; i++) {
      int randomNum = random.nextInt((max - min) + 1) + min;
      temp.add(randomNum);
    }
    return temp;
  }


}

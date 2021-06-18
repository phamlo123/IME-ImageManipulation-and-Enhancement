package model.converter;

import static model.util.ImageUtil.readPPM;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import model.FileFormat;

import model.util.ImageUtil;

public class SimpleConverter implements Converter {

  private BufferedImage bufferedImage;

  public SimpleConverter(String fileName) {
    this();
    this.bufferedImage = importImage(fileName);
  }

  public SimpleConverter() {
    this.bufferedImage = null;
  }

  public SimpleConverter(BufferedImage bufferedImage) {
    this.bufferedImage = bufferedImage;
  }


  @Override
  public BufferedImage importImage(String fileName) throws IllegalArgumentException {
    if (fileName.endsWith("ppm")) {
      return ImageUtil.createBufferedImage(readPPM(fileName));
    }
    try {
      File f = new File(fileName);
      BufferedImage image = ImageIO.read(f);
      return image;
    } catch (IOException e) {
      throw new IllegalArgumentException("File not found or extension not supported");
    }
  }

  @Override
  public void exportImage(String fileName, FileFormat fileFormat) throws IllegalArgumentException {
    String file;
    switch (fileFormat) {
      case PNG:
        file = "png";
        break;
      case JPEG:
        file = "jpg";
        break;
      case PPM:
        ImageUtil.exportPPM(fileName, getListOfColor());
        return;
      default:
        throw new IllegalArgumentException("file extension not supported");
    }
    try {
      ImageIO.write(this.bufferedImage, file, new File(fileName));
    } catch (IOException e) {
      System.out.println("Error writing image to " + fileName);
    }
  }

  @Override
  public List<List<Color>> getListOfColor() {
    int height = bufferedImage.getHeight();
    int width = bufferedImage.getWidth();
    List<List<Color>> listOfColor = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      listOfColor.add(new ArrayList<>());
      for (int j = 0; j < width; j++) {
        listOfColor.get(i).add(new Color(bufferedImage.getRGB(j, i)));
      }
    }
    return listOfColor;
  }

}

package model.ImageRepresentation.JPEG;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import model.ImageRepresentation.Image;
import model.ImageRepresentation.ImageFormat;

public class JPEG extends Image implements ImageFormat {

  public JPEG(String fileName) {
    super(fileName);
  }

  public JPEG() {
    super();
  }

  public JPEG(List<List<Color>> listOfColor) {
    super(listOfColor);
  }


  @Override
  public void exportPPM(String fileName) {
    try {
      ImageIO.write(getBufferedImage(), "jpg", new File(fileName));
    } catch (IOException e) {
      System.out.println("Error writing image to " + fileName);
    }
  }


}

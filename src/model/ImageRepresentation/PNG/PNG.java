package model.ImageRepresentation.PNG;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import model.ImageRepresentation.Image;
import model.ImageRepresentation.ImageFormat;

public class PNG extends Image implements ImageFormat {


  public PNG(String fileName) {
    super(fileName);
  }

  public PNG() {
    super();
  }

  public PNG(List<List<Color>> listOfColor) {
    super(listOfColor);
  }


  @Override
  public void exportPPM(String fileName) {
    try {
      ImageIO.write(getBufferedImage(), "png", new File(fileName));
    } catch (IOException e) {
      System.out.println("Error writing image to " + fileName);
    }
  }
}

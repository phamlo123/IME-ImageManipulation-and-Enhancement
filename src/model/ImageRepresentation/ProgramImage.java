package model.ImageRepresentation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.GregorianCalendar;

/**
 * this class contains information about an image that is programmable.
 */
public class ProgramImage {
  Image image;

  public static class ImageCreator {
    ProgramImage programImage;
    ImageCreator() {
      this.programImage = create();
    }

    public static ProgramImage create() {
      return new ProgramImage(new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB));
    }
  }

  private ProgramImage(Image image) {
    this.image = image;
  }

  public ProgramImage makeAnImage() {
    return ImageCreator.create();
  }




  public Image getImage() {
    return this.image;
  }

}

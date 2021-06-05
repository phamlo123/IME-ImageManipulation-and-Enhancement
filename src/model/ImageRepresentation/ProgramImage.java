package model.ImageRepresentation;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import model.ImageImpl;

/**
 * this class contains information about an image that is programmable.
 */
public class ProgramImage {
  Image image;

  public static class imageCreator {
    ProgramImage programImage;
    imageCreator() {
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
    return imageCreator.create();
  }
}

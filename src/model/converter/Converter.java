package model.converter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;
import model.enumTypes.FileFormat;


/**
 * This interface provides mechanisms for converting image files of different formats into the
 * program internal representation of an image.
 */
public interface Converter {

  /**
   * Import an image file into a buffereed Image
   * @param fileName is the file name of the imported image
   * @return the Buffered image extracted from the image file
   * @throws IllegalArgumentException if the image file could not be found with the given name
   */
  BufferedImage importImage(String fileName) throws IllegalArgumentException;

  /**
   * Export an image into a specified image format with a given file name
   * @param fileNam is the file name of the newly created file
   * @param fileFormat is the format supported by our program
   * @throws IllegalArgumentException if the file format is not supported
   */
  void exportImage(String fileNam, FileFormat fileFormat) throws IllegalArgumentException;

  /**
   * Get the list of list of colors of all the pixels from the buffered Image of this Converter
   * object
   * @return a list of list of color of this object
   */
  List<List<Color>> getListOfColor();

  /**
   * Export the image object into a file with JPEG extension and with the given file name. Create
   * a string that is the path to that newly created file
   * @param fileName is the name of the new image file
   * @param fileFormat is the desired format for the exported image file
   * @return the path of the file
   */
  String exportWithPath(String fileName, FileFormat fileFormat);

}

package controller;

import java.util.Objects;
import model.FileFormat;
import model.ImageRepresentation.multiLayers.MultiLayers;
import model.util.ImageUtil;

/**
 * Command class used for saving images from the model to a file.
 */
public class SaveCommand implements ImageCommand {

  private final String fileName;
  private final FileFormat fileFormat;

  /**
   * Creates a SaveCommand object with the given file destination name and file format as
   * private fields.
   * @param fileName    the name of the file to write to.
   * @param fileFormat  the type of file to create (png, jpg, or ppm)
   */
  public SaveCommand(String fileName, FileFormat fileFormat) {
    ImageUtil.checkNull(fileName);
    ImageUtil.checkNull(fileFormat);
    this.fileName = fileName;
    this.fileFormat = fileFormat;
  }

  @Override
  public void go(MultiLayers model) {
    ImageUtil.checkNull(model);
    model.saveImages(fileName, fileFormat);
  }
}

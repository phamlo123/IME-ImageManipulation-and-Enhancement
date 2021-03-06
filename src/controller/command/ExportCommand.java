package controller.command;

import model.enums.FileFormat;
import model.imagerepresentation.multilayers.MultiLayers;
import model.util.ImageUtil;

/**
 * Command class used for exporting the top image from the model to a file.
 */
public class ExportCommand implements ImageCommand {

  private final String fileName;
  private final FileFormat fileFormat;

  /**
   * Creates a SaveCommand object with the given file destination name and file format as private
   * fields.
   *
   * @param fileName   the name of the file to write to.
   * @param fileFormat the type of file to create (png, jpg, or ppm)
   */
  public ExportCommand(String fileName, FileFormat fileFormat) {
    ImageUtil.checkNull(fileName);
    ImageUtil.checkNull(fileFormat);
    this.fileName = fileName;
    this.fileFormat = fileFormat;
  }

  @Override
  public void execute(MultiLayers model) {
    ImageUtil.checkNull(model);
    model.saveImages(fileName, fileFormat);
  }
}

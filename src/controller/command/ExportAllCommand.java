package controller.command;

import model.ImageRepresentation.multiLayers.MultiLayers;
import model.enumTypes.FileFormat;
import model.util.ImageUtil;

/**
 * Command class used for exporting all the images in the model.
 */
public class ExportAllCommand implements ImageCommand {

  private final String baseName;
  private final FileFormat fileFormat;
  /**
   * Constructs a ExportAllCommand object with the base name for the file being written to as a
   * private field.
   * @param baseName the name of the file to write the multiple images to
   */
  public ExportAllCommand(String baseName, FileFormat fileFormat) {
    ImageUtil.checkNull(baseName);
    this.baseName = baseName;
    this.fileFormat = fileFormat;
  }

  @Override
  public void go(MultiLayers model) {
    ImageUtil.checkNull(model);
    model.exportAll(baseName, fileFormat);
  }
}

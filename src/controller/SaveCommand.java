package controller;

import model.FileFormat;
import model.ImageRepresentation.multiLayers.MultiLayers;

public class SaveCommand implements ImageCommand {

  private final String fileName;
  private final FileFormat fileFormat;

  public SaveCommand(String fileName, FileFormat fileFormat) {
    this.fileName = fileName;
    this.fileFormat = fileFormat;
  }

  @Override
  public void go(MultiLayers model) {
    model.saveImages(fileName, fileFormat);
  }
}

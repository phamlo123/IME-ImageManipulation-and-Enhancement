package controller.command;

import model.ImageRepresentation.multiLayers.MultiLayers;

/**
 * Command class used for importing all the images in a file to the model.
 */
public class ImportAllCommand implements ImageCommand {

  private final String fileName;

  /**
   * Constructs an ImportAllCommand object with the filename to take the images from as a private
   * field
   * @param fileName the name of the file to import the images from
   */
  public ImportAllCommand(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public void go(MultiLayers model) {

  }
}

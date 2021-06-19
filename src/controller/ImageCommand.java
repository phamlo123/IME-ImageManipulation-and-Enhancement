package controller;

import model.ImageRepresentation.multiLayers.MultiLayers;

/**
 * Represents a command that can be executed on MultiLayeredImages. Follows the command design
 * pattern.
 */
public interface ImageCommand {

  /**
   * Executes the specific method determined by each command class.
   * @param model the model to execute the specific command from
   */
  void go(MultiLayers model);

}

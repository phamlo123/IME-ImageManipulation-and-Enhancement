package controller;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Represents a controller for image processing, allowing the user to input certain commands and the
 * controller has the model execute those commands.
 */
public interface ImageController {

  /**
   * Processes the commands desired by the user input from the console interactively.
   *
   * @throws IllegalStateException if there is nothing left from the user to read
   */
  void processInteractive()
      throws IllegalStateException, IllegalArgumentException, FileNotFoundException;

  /**
   * Processes the commands desired by the user input from a file.
   *
   * @param fileName the name of the file to read from
   * @throws IllegalStateException if there is nothing left from the user to read
   */
  void processFile(File fileName)
      throws FileNotFoundException, IllegalStateException, IllegalArgumentException;

}

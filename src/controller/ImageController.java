package controller;

import java.io.File;
import java.io.FileNotFoundException;

public interface ImageController {

  /**
   * Processes the commands desired by the user input from the console interactively
   * @throws IllegalStateException
   */
  void processInteractive();

  /**
   * Processes the commands desired by the user input from a file
   * @param fileName
   * @throws IllegalStateException
   */
  void processFile(File fileName) throws FileNotFoundException;

}

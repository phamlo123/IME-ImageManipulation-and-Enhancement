package controller;

import java.awt.event.ActionEvent;

/**
 * Represents a controller for image processing, taking user input from a graphical interface view
 * and then having the model execute those commands.
 */
public interface NewControllerInterface {

  /**
   * Method that delegates to the model the desired functionality based on the press of a button
   * from the view.
   *
   * @param arg0 the action from the view (button pressed)
   */
  void actionPerformed(ActionEvent arg0);

  /**
   * Helper for actionPerformed that takes the String version of the ActionEvent given to
   * actionPerformed. This method is public for easier testing.
   *
   * @param command the command to be executed.
   */
  void actionHelper(String command);
}

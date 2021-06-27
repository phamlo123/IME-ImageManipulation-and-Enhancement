package gui;

import controller.NewController;
import java.awt.image.BufferedImage;

/**
 * Interface representing the view of the image processing as a graphical user interface.
 */
public interface ImageView {

  /**
   * Assigns the given controller as the listener for all the interactive options in the view.
   * @param controller the controller to handle the buttons in the user interface
   */
  void setListener(NewController controller);

  /**
   * Displays a text window that takes in and returns user input as a String.
   * @param string the message to display to the user
   * @return the input typed in by the user as a String
   */
  String getText(String string);

  /**
   * Displays the given String as a message on a new window.
   * @param string the message to be displayed
   */
  void setText(String string);

  /**
   * Sets the given image as the image previewed on the main image panel.
   * @param image the image to be displayed on the main image panel
   */
  void setImage(BufferedImage image);

  /**
   * Displays the current number of layers in the image, and the current layer the user is
   * interacting with.
   * @param currentDisplay the index of the layer the user is currently working with
   * @param numLayer       the total number of layers in the image
   */
  void setCurrentDisplay(String currentDisplay, String numLayer);

}

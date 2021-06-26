
package controller;

import gui.SwingFeaturesFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import model.enums.FileFormat;
import model.imagerepresentation.Image;
import model.imagerepresentation.multilayers.MultiLayers;

public class NewController implements ActionListener {

  private final SwingFeaturesFrame swingFeaturesFrame;
  private final MultiLayers model;

  public NewController(MultiLayers model, SwingFeaturesFrame swingFeaturesFrame)
      throws IllegalArgumentException {
    if (model == null || swingFeaturesFrame == null) {
      throw new IllegalArgumentException();
    }
    this.swingFeaturesFrame = swingFeaturesFrame;
    this.model = model;
    swingFeaturesFrame.setListener(this);
    swingFeaturesFrame.setImage(model.getTopmost());
  }


  public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    switch (arg0.getActionCommand()) {
      case "Blur":
        swingFeaturesFrame.setText("Blur was selected");
        model.blurringImage();
        swingFeaturesFrame.setImage(model.getTopmost());
        break;

      case "Sharpen":
        swingFeaturesFrame.setText("Sharpen was selected");
        model.sharpeningImage();
        swingFeaturesFrame.setImage(model.getTopmost());
        break;

      case "Gray":
        swingFeaturesFrame.setText("Gray was selected");
        model.createMonochrome();
        swingFeaturesFrame.setImage(model.getTopmost());
        break;

      case "Sepia":
        swingFeaturesFrame.setText("Sepia was selected");
        model.createSepia();
        swingFeaturesFrame.setImage(model.getTopmost());
        break;
      case "Import":
        String load = swingFeaturesFrame.getText("Please enter image file name and layer index");
        Scanner loadScanner = new Scanner(load);
        try {
          model.loadImages(new Image(loadScanner.next()), Integer.parseInt(loadScanner.next()));
        } catch (IllegalArgumentException e) {

        }
        break;
      case "Import All":
        String importAll = swingFeaturesFrame.getText("Please enter text file name");
        try {
          model.importAll(importAll);
        } catch (IllegalArgumentException e) {

        }
        break;
      case "Export":
        String export = swingFeaturesFrame
            .getText("Please enter name for this image and file format");
        Scanner exportScanner = new Scanner(export);
        try {
          model.saveImages(exportScanner.next(), this.toFormat(exportScanner.next()));
        } catch (IllegalArgumentException e) {

        }
        break;
      case "Export All":
        String exportAll = swingFeaturesFrame
            .getText("Please enter name for this image and file format");
        Scanner exportAllScanner = new Scanner(exportAll);
        try {
          model.exportAll(exportAllScanner.next(), this.toFormat(exportAllScanner.next()));
        } catch (IllegalArgumentException e) {

        }
        break;
      case "Mosaic":
        swingFeaturesFrame
            .getText("Please enter the number of seeds");
        // mosaic
        break;
      case "Downsize":
        swingFeaturesFrame.getText("Please enter desired width and height");
        // downsize
        break;
      case "Option": {
        String[] options = {"Uno", "Dos", "Tres", "Cuatro", "Cinco", "seis", "siete", "ocho",
            "nueve", "dies"};
      }
      break;
    }
  }

  /**
   * Converts the next piece of user input from a String into a valid file format type. If the input
   * is not a valid file format type, the method asks the user to input a valid file format type.
   *
   * @param toFormat String to be converted to file type
   * @return the correct FileFormat that corresponds to the user input
   * @throws IllegalStateException if the appendable has an error appending
   */
  private FileFormat toFormat(String toFormat) throws IllegalStateException {
    switch (toFormat) {
      case "png":
        return FileFormat.PNG;
      case "jpg":
      case "jpeg":
        return FileFormat.JPEG;
      case "ppm":
        return FileFormat.PPM;
      default:
        throw new IllegalArgumentException();
    }
  }
}





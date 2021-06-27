
package controller;

import gui.SwingFeaturesFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
        model.blurringImage();
        swingFeaturesFrame.setImage(model.getTopmost());
        swingFeaturesFrame.setText("Blur was selected");
        break;
      case "Sharpen":
        model.sharpeningImage();
        swingFeaturesFrame.setImage(model.getTopmost());
        swingFeaturesFrame.setText("Sharpen was selected");
        break;
      case "Gray":
        model.createMonochrome();
        swingFeaturesFrame.setImage(model.getTopmost());
        swingFeaturesFrame.setText("Gray was selected");
        break;
      case "Sepia":
        model.createSepia();
        swingFeaturesFrame.setImage(model.getTopmost());
        swingFeaturesFrame.setText("Sepia was selected");
        break;
      case "Import":
        String load = swingFeaturesFrame.getText("Please enter image file name and layer index");
        Scanner loadScanner = new Scanner(load);
        try {
          model.loadImages(new Image(loadScanner.next()), Integer.parseInt(loadScanner.next()));
          swingFeaturesFrame.setText("Import was selected");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText("Invalid file name or layer index. Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Import All":
        String importAll = swingFeaturesFrame.getText("Please enter text file name");
        try {
          model.importAll(importAll);
          swingFeaturesFrame.setText("Import All was selected");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText("Invalid file name. Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Export":
        String export = swingFeaturesFrame
            .getText("Please enter name for this image and file format");
        Scanner exportScanner = new Scanner(export);
        try {
          model.saveImages(exportScanner.next(), this.toFormat(exportScanner.next()));
          swingFeaturesFrame.setText("Export was selected");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText("Invalid name or file format. Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Export All":
        String exportAll = swingFeaturesFrame
            .getText("Please enter name for this image and file format");
        Scanner exportAllScanner = new Scanner(exportAll);
        try {
          model.exportAll(exportAllScanner.next(), this.toFormat(exportAllScanner.next()));
          swingFeaturesFrame.setText("Export All was selected");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText("Invalid name or file format. Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Add Layer":
        model.addLayer();
        swingFeaturesFrame.setText("Add layer was selected");
        break;
      case "Remove Layer":
        String remove = swingFeaturesFrame.getText("Please enter a layer index");
        try {
          model.removeLayer(Integer.parseInt(remove));
          swingFeaturesFrame.setText("Remove layer was selected");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText("Invalid layer index. Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Visible":
        String visible = swingFeaturesFrame.getText("Please enter a layer index");
        try {
          model.setInvisibility(Integer.parseInt(visible), true);
          swingFeaturesFrame.setImage(model.getTopmost());
          swingFeaturesFrame.setText("Visible was selected");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText("Invalid layer index. Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Invisible":
        String invisible = swingFeaturesFrame.getText("Please enter a layer index");
        try {
          model.setInvisibility(Integer.parseInt(invisible), false);
          swingFeaturesFrame.setImage(model.getTopmost());
          swingFeaturesFrame.setText("Invisible was selected");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText("Invalid layer index. Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Set Current":
        String current = swingFeaturesFrame.getText("Please enter a layer index");
        try {
          model.setCurrent(Integer.parseInt(current));
          swingFeaturesFrame.setText("Set current was selected");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText("Invalid layer index. Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Mosaic":
        String mosaic = swingFeaturesFrame
            .getText("Please enter the number of seeds");
        try {
          model.createMosaic(Integer.parseInt(mosaic));
          swingFeaturesFrame.setImage(model.getTopmost());
          swingFeaturesFrame.setText("Mosaic was selected");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText("Invalid number. Please enter again.");
          this.actionPerformed(arg0);
        }
        break;
      case "Downsize":
        String downsize = swingFeaturesFrame
            .getText("Please enter desired width and height as doubles");
        Scanner downsizeScanner = new Scanner(downsize);
        try {
          model.downSize(Double.parseDouble(downsizeScanner.next()),
              Double.parseDouble(downsizeScanner.next()));
          swingFeaturesFrame.setImage(model.getTopmost());
          swingFeaturesFrame.setText("Downsize was selected");
        } catch (IllegalArgumentException e) {
          swingFeaturesFrame.setText("Invalid width or height. Please enter again.");
          this.actionPerformed(arg0);
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





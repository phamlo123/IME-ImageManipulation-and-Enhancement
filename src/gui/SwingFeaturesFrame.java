package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.crypto.dsig.XMLObject;

/**
 * This class opens the main window, that has different elements illustrated in it. It also doubles
 * up as all the listeners for simplicity. Such a design is not recommended in general.
 */

public class SwingFeaturesFrame extends JFrame implements ActionListener, ItemListener,
    ListSelectionListener {

  private JPasswordField pfield;
  private JButton pButton;
  private JLabel pDisplay;
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;

  private JLabel checkboxDisplay;
  private JLabel radioDisplay;
  private JLabel comboboxDisplay;
  private JLabel colorChooserDisplay;
  private JLabel fileOpenDisplay;
  private JLabel fileSaveDisplay;
  private JLabel inputDisplay;
  private JLabel optionDisplay;

  private JList<String> listOfStrings;
  private JList<Integer> listOfIntegers;

  public SwingFeaturesFrame() {
    super();
    setTitle("Image Processor");
    setSize(1000, 1000);

    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BorderLayout());
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    //natural height, maximum width

    //show an image with a scrollbar
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    TitledBorder title = BorderFactory.createTitledBorder("Image");
    title.setTitleJustification(TitledBorder.CENTER);
    imagePanel.setBorder(title);
    imagePanel.add(new JScrollPane());
    imagePanel.setLayout(new BorderLayout());
    imagePanel.setSize(new Dimension(500, 500));
    mainPanel.add(imagePanel, BorderLayout.CENTER);
    //imagePanel.setMaximumSize(null);

    //Filtering Boxes
    JPanel filteringBoxesPanel = new JPanel();

    TitledBorder title2 = BorderFactory.createTitledBorder("Filtering Options");
    title2.setTitleJustification(TitledBorder.CENTER);
    filteringBoxesPanel.setBorder(title2);
    filteringBoxesPanel.setPreferredSize(new Dimension(150, 120));
    filteringBoxesPanel.setLayout(new GridLayout(18, 1, 0, 0));
    mainPanel.add(filteringBoxesPanel, BorderLayout.WEST);

    JButton blurButton = new JButton("Blur");
    blurButton.setSize(new Dimension(150, 20));
    blurButton.setMaximumSize(new Dimension(150, 20));
    JButton sharpenButton = new JButton("Sharpen");
    sharpenButton.setSize(new Dimension(150, 20));
    sharpenButton.setMaximumSize(new Dimension(150, 20));

    JButton createGrayScaleButton = new JButton("Create Grayscale");
    createGrayScaleButton.setSize(new Dimension(150, 20));
    createGrayScaleButton.setMaximumSize(new Dimension(150, 20));

    JButton createSepiaButton = new JButton("Create Sepia");
    createSepiaButton.setSize(new Dimension(150, 20));
    createSepiaButton.setMaximumSize(new Dimension(150, 20));

    JButton downSizePanelButton = new JButton("Downsize");
    downSizePanelButton.setSize(new Dimension(150, 20));
    downSizePanelButton.setActionCommand("Downsize");
    downSizePanelButton.addActionListener(this);
    downSizePanelButton.setMaximumSize(new Dimension(150, 20));

    JButton mosaicPanelButton = new JButton("Create Mosaic");
    mosaicPanelButton.setSize(new Dimension(150, 20));
    mosaicPanelButton.setActionCommand("Mosaic");
    mosaicPanelButton.addActionListener(this);
    mosaicPanelButton.setMaximumSize(new Dimension(150, 20));

    filteringBoxesPanel.add(blurButton);
    filteringBoxesPanel.add(sharpenButton);
    filteringBoxesPanel.add(createGrayScaleButton);

    filteringBoxesPanel.add(createSepiaButton);
    filteringBoxesPanel.add(downSizePanelButton);
    filteringBoxesPanel.add(mosaicPanelButton);

    JPanel otherOptions = new JPanel();

    TitledBorder title4 = BorderFactory.createTitledBorder("Other Options");
    title4.setTitleJustification(TitledBorder.CENTER);
    otherOptions.setBorder(title4);
    otherOptions.setLayout(new GridLayout(0, 1, 10, 0));

    otherOptions.setPreferredSize(new Dimension(250, 100));
    mainPanel.add(otherOptions, BorderLayout.EAST);

    JPanel checkerBoard = new JPanel();
    checkerBoard.setLayout(new BorderLayout());
    checkerBoard.setPreferredSize(new Dimension(200, 200));

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    TitledBorder title3 = BorderFactory.createTitledBorder("File");
    title3.setTitleJustification(TitledBorder.CENTER);
    dialogBoxesPanel.setBorder(title3);
    dialogBoxesPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    dialogBoxesPanel.setLayout(new FlowLayout());

    mainPanel.add(dialogBoxesPanel, BorderLayout.PAGE_END);

    //color chooser
    JPanel colorChooserPanel = new JPanel();
    colorChooserPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(colorChooserPanel);
    JButton colorChooserButton = new JButton("Choose a color");
    colorChooserButton.setActionCommand("Color chooser");
    colorChooserButton.addActionListener(this);
    colorChooserPanel.add(colorChooserButton);
    colorChooserDisplay = new JLabel("      ");
    colorChooserDisplay.setOpaque(true); //so that background color shows up
    colorChooserDisplay.setBackground(Color.BLACK);

    colorChooserPanel.add(colorChooserDisplay);

    //load an Image
    JPanel loadImagePanel = new JPanel();
    loadImagePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(loadImagePanel);
    JButton loadImagePanelButton = new JButton("Load an Image");
    loadImagePanelButton.setActionCommand("Input1");
    loadImagePanelButton.addActionListener(this);
    loadImagePanel.add(loadImagePanelButton);

    //Load All Images
    JPanel loadAllImages = new JPanel();
    loadAllImages.setLayout(new FlowLayout());
    dialogBoxesPanel.add(loadAllImages);
    JButton loadAllImagesButton = new JButton("Load All Images");
    loadAllImagesButton.setActionCommand("Input2");
    loadAllImagesButton.addActionListener(this);
    loadAllImages.add(loadAllImagesButton);

    //Save an Image
    JPanel saveAnImage = new JPanel();
    saveAnImage.setLayout(new FlowLayout());
    dialogBoxesPanel.add(saveAnImage);
    JButton saveAnImageButton = new JButton("Save");
    saveAnImageButton.addActionListener(this);
    saveAnImageButton.setActionCommand("Input3");
    saveAnImage.add(saveAnImageButton);

    //Save all Images
    JPanel saveAllImage = new JPanel();
    saveAllImage.setLayout(new FlowLayout());
    dialogBoxesPanel.add(saveAllImage);
    JButton saveAllImagesButton = new JButton("Save All");
    saveAllImagesButton.setActionCommand("Input4");
    saveAllImagesButton.addActionListener(this);
    saveAllImage.add(saveAllImagesButton);

    //JOptionsPane message dialog
    JPanel messageDialogPanel = new JPanel();
    messageDialogPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(messageDialogPanel);

    //JOptionsPane input dialog
    JPanel inputDialogPanel = new JPanel();
    inputDialogPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(inputDialogPanel);

    //JOptionsPane options dialog
    JPanel optionsDialogPanel = new JPanel();
    optionsDialogPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(optionsDialogPanel);


  }


  @Override
  public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    switch (arg0.getActionCommand()) {
      case "password button":
        pDisplay.setText(new String(pfield.getPassword()));
        pfield.setText("");
        break;
      case "RB1":
        radioDisplay.setText("Radio button 1 was selected");
        break;

      case "RB2":
        radioDisplay.setText("Radio button 2 was selected");
        break;

      case "RB3":
        radioDisplay.setText("Radio button 3 was selected");
        break;

      case "RB4":
        radioDisplay.setText("Radio button 4 was selected");
        break;

      case "Size options":
        if (arg0.getSource() instanceof JComboBox) {
          JComboBox<String> box = (JComboBox<String>) arg0.getSource();
          comboboxDisplay.setText("You selected: " + (String) box.getSelectedItem());


        }
        break;
      case "Color chooser":
        Color col = JColorChooser.showDialog(SwingFeaturesFrame.this, "Choose a color",
            colorChooserDisplay.getBackground());
        colorChooserDisplay.setBackground(col);
        break;
      case "Open file": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & GIF Images", "jpg", "gif");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(SwingFeaturesFrame.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileOpenDisplay.setText(f.getAbsolutePath());
        }
      }
      break;
      case "Save file": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(SwingFeaturesFrame.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileSaveDisplay.setText(f.getAbsolutePath());
        }
      }
      break;
      case "Message":
        JOptionPane.showMessageDialog(SwingFeaturesFrame.this, "This is a demo message", "Message",
            JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(SwingFeaturesFrame.this, "You are about to be deleted.",
            "Last Chance", JOptionPane.WARNING_MESSAGE);
        JOptionPane.showMessageDialog(SwingFeaturesFrame.this, "You have been deleted.", "Too late",
            JOptionPane.ERROR_MESSAGE);
        JOptionPane
            .showMessageDialog(SwingFeaturesFrame.this, "Please start again.", "What to do next",
                JOptionPane.INFORMATION_MESSAGE);
        break;
      case "Input":
        inputDisplay.setText(JOptionPane.showInputDialog("Please enter your username"));
        break;
      case "Input1":
        inputDisplay.setText(JOptionPane.showInputDialog("Please enter image file name"));
        break;
      case "Input2":
        inputDisplay.setText(JOptionPane.showInputDialog("Please enter text file name"));
        break;
      case "Input3":
        inputDisplay.setText(JOptionPane.showInputDialog("Please enter name for this image"));
        break;
      case "Input4":
        inputDisplay
            .setText(JOptionPane.showInputDialog("Please enter base name for these images"));
        break;
      case "Mosaic":
        inputDisplay
            .setText(JOptionPane.showInputDialog("Please enter the number of seeds"));
        break;
      case "Option": {
        String[] options = {"Uno", "Dos", "Tres", "Cuatro", "Cinco", "seis", "siete", "ocho",
            "nueve", "dies"};
        int retvalue = JOptionPane
            .showOptionDialog(SwingFeaturesFrame.this, "Please choose number", "Options",
                JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[4]);
        optionDisplay.setText(options[retvalue]);
      }
      break;
    }
  }

  @Override
  public void itemStateChanged(ItemEvent arg0) {
    // TODO Auto-generated method stub
    String who = ((JCheckBox) arg0.getItemSelectable()).getActionCommand();

    switch (who) {
      case "CB1":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 1 was selected");
        } else {
          checkboxDisplay.setText("Check box 1 was deselected");
        }
        break;
      case "CB2":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 2 was selected");
        } else {
          checkboxDisplay.setText("Check box 2 was deselected");
        }
        break;
      case "CB3":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 3 was selected");
        } else {
          checkboxDisplay.setText("Check box 3 was deselected");
        }
        break;
      case "CB4":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 4 was selected");
        } else {
          checkboxDisplay.setText("Check box 4 was deselected");
        }
        break;

      case "CB5":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 5 was selected");
        } else {
          checkboxDisplay.setText("Check box 5 was deselected");
        }
        break;

    }
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    // We don't know which list called this callback, because we're using it
    // for two lists.  In practice, you should use separate listeners
    JOptionPane.showMessageDialog(SwingFeaturesFrame.this,
        "The source object is " + e.getSource(), "Source", JOptionPane.PLAIN_MESSAGE);
    // Regardless, the event information tells us which index was selected
    JOptionPane.showMessageDialog(SwingFeaturesFrame.this,
        "The changing index is " + e.getFirstIndex(), "Index", JOptionPane.PLAIN_MESSAGE);
    // This gets us the string value that's currently selected
    JOptionPane.showMessageDialog(SwingFeaturesFrame.this,
        "The current string item is " + this.listOfStrings.getSelectedValue(), "Selected string",
        JOptionPane.PLAIN_MESSAGE);
    // This gets us the integer value that's currently selected
    JOptionPane.showMessageDialog(SwingFeaturesFrame.this,
        "The current number item is " + this.listOfIntegers.getSelectedValue(), "Selected integer",
        JOptionPane.PLAIN_MESSAGE);
  }
}

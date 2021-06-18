package model.converter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;
import model.FileFormat;

public interface Converter {

  BufferedImage importImage(String fileName) throws IllegalArgumentException;

  void exportImage(String fileNam, FileFormat fileFormat) throws IllegalArgumentException;

  List<List<Color>> getListOfColor();

}

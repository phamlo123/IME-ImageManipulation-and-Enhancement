package model.ImageRepresentation;

public interface MultiLayers {

  void saveImages();

  void loadImages();

  void addLayer(ImageFormat image) throws IllegalArgumentException;

  void removeLayer(ImageFormat image) throws IllegalArgumentException;
}

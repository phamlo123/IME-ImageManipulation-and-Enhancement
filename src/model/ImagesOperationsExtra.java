package model;

public interface ImagesOperationsExtra extends ImagesOperations {


  void downSize(double ratioWidth, double ratioHeight);

  void createMosaic(int numSeeds);
}

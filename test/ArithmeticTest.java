import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.ImageRepresentation.util.Arithmetic;
import model.Matrices;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArithmeticTest {

  @Test
  public void testHelperForMultiplyingWith3() {
    List<List<Integer>> listA = new ArrayList<>();
    List<List<Double>> listB = Matrices.MATRIX_FOR_BLURRING.getMatrix();
    for (int i = 0; i < 3; i++) {
      listA.add(Arrays.asList(150, 200, 170));
    }

    List<List<Integer>> listD =  Arithmetic.helperForMultiplying(listA, listB);

    List<List<Integer>> listC = new ArrayList<>();

    listC.add(Arrays.asList(93,135,101));
    listC.add(Arrays.asList(125, 180, 135));
    listC.add(Arrays.asList(93, 135, 101));
    assertEquals(listC.size(), listD.size());
    for(int i = 0; i<3;i++) {
      assertEquals(listD.get(i).size(), listC.get(i).size());
      for (int j = 0; j < 3; j++) {
        assertEquals(listC.get(i).get(j), listD.get(i).get(j));
      }
    }
  }

  @Test
  public void testHelperForMultiplyingWith5() {
    List<List<Integer>> listA = new ArrayList<>();
    List<List<Double>> listB = Matrices.MATRIX_FOR_BLURRING.getMatrix();
    for (int i = 0; i < 5; i++) {
      listA.add(Arrays.asList(100, 150, 200, 130, 4));
    }
    List<List<Integer>> listD = Arithmetic.helperForMultiplying(listA, listB);
    List<List<Integer>> listC = new ArrayList<>();
    listC.add(Arrays.asList(65, 112, 127, 87, 25));
    listC.add(Arrays.asList(87, 150, 170, 116, 34));
    listC.add(Arrays.asList(87, 150, 170, 116, 34));
    listC.add(Arrays.asList(87, 150, 170, 116, 34));
    listC.add(Arrays.asList(65, 112, 127, 87, 25));
    assertEquals(listC.size(), listD.size());
    for (int i = 0; i < 5; i++) {
      assertEquals(listD.get(i).size(), listC.get(i).size());
      for (int j = 0; j < 5; j++) {
        assertEquals(listC.get(i).get(j), listD.get(i).get(j));
      }
    }
  }

  @Test
  public void testHelperForMultiplyingWith5WithNegative() {
    List<List<Integer>> listA = new ArrayList<>();
    List<List<Double>> listB = Matrices.MATRIX_FOR_BLURRING.getMatrix();
    for (int i = 0; i < 5; i++) {
      listA.add(Arrays.asList(0, 150, 1, 0, -4));
    }
    List<List<Integer>> listD = Arithmetic.helperForMultiplying(listA, listB);
    List<List<Integer>> listC = new ArrayList<>();
    listC.add(Arrays.asList(28, 56, 28, 0, 0));
    listC.add(Arrays.asList(37, 75, 38, 0, 0));
    listC.add(Arrays.asList(37, 75, 38, 0, 0));
    listC.add(Arrays.asList(37, 75, 38, 0, 0));
    listC.add(Arrays.asList(28, 56, 28, 0, 0));

    assertEquals(listC.size(), listD.size());
    for (int i = 0; i < 5; i++) {
      assertEquals(listD.get(i).size(), listC.get(i).size());
      for (int j = 0; j < 5; j++) {
        assertEquals(listC.get(i).get(j), listD.get(i).get(j));
      }
    }
  }

  @Test
  public void testHelperForMultiplyingWithEven() {
    List<List<Integer>> listA = new ArrayList<>();
    List<List<Double>> listB = Matrices.MATRIX_FOR_BLURRING.getMatrix();
    for (int i = 0; i < 4; i++) {
      listA.add(Arrays.asList(10, 150, 1, 0, 100, 100, 100, 255));
    }
    List<List<Integer>> listD = Arithmetic.helperForMultiplying(listA, listB);
    List<List<Integer>> listC = new ArrayList<>();
    listC.add(Arrays.asList(31, 58, 28, 18, 56, 75, 104, 114));
    listC.add(Arrays.asList(42, 77, 38, 25, 75, 100, 138, 152));
    listC.add(Arrays.asList(42, 77, 38, 25, 75, 100, 138, 152));
    listC.add(Arrays.asList(31, 58, 28, 18, 56, 75, 104, 114));
    System.out.println(listD);

    assertEquals(listC.size(), listD.size());
    for (int i = 0; i < 4; i++) {
      assertEquals(listD.get(i).size(), listC.get(i).size());
      for (int j = 0; j < listD.get(i).size(); j++) {
        assertEquals(listC.get(i).get(j), listD.get(i).get(j));
      }
    }
  }

  @Test
  public void testHelperForMultiplyingWith5WithOver255() {
    List<List<Integer>> listA = new ArrayList<>();
    List<List<Double>> listB = Matrices.MATRIX_FOR_BLURRING.getMatrix();
    for (int i = 0; i < 5; i++) {
      listA.add(Arrays.asList(255, 150, 260, 300, 200));
    }
    List<List<Integer>> listD = Arithmetic.helperForMultiplying(listA, listB);
    List<List<Integer>> listC = new ArrayList<>();
    listC.add(Arrays.asList(123, 152, 181, 198, 131));
    listC.add(Arrays.asList(165, 203, 242, 255, 175));
    listC.add(Arrays.asList(165, 203, 242, 255, 175));
    listC.add(Arrays.asList(165, 203, 242, 255, 175));
    listC.add(Arrays.asList(123, 152, 181, 198, 131));

    System.out.println(listD);
    assertEquals(listC.size(), listD.size());
    for (int i = 0; i < 5; i++) {
      assertEquals(listD.get(i).size(), listC.get(i).size());
      for (int j = 0; j < 5; j++) {
        assertEquals(listC.get(i).get(j), listD.get(i).get(j));
      }
    }
  }

  @Test
  public void testLimit() {
    int a =Arithmetic.verifySum(300.0, 150, 129);
    assertEquals(150, a);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLimitIllegal() {
    int a =Arithmetic.verifySum(300.0, 120, 150);
    assertEquals(150, a);
  }

  @Test
  public void testLimit2() {
    int a =Arithmetic.verifySum(100.1, 255, 0);
    assertEquals(100, a);
  }

  @Test
  public void testLimit3() {
    int a =Arithmetic.verifySum(-100.0, 255, 10);
    assertEquals(10, a);
  }




}

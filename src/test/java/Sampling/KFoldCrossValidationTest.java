package Sampling;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class KFoldCrossValidationTest {
    ArrayList<String> smallSample;
    ArrayList<Integer> largeSample;

    @Before
    public void setUp() {
        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        smallSample = new ArrayList<>(Arrays.asList(data));
        largeSample = new ArrayList<>();
        for (int i = 0; i < 1000; i++){
            largeSample.add(i);
        }
    }

    @Test
    public void testSmallSample10Fold() {
        KFoldCrossValidation kFoldCrossValidation = new KFoldCrossValidation(smallSample, 10, 1);
        String[] expected1 = {"7"};
        assertArrayEquals(expected1, kFoldCrossValidation.getTestFold(0).toArray());
    }

    @Test
    public void testSmallSample5Fold() {
        KFoldCrossValidation kFoldCrossValidation = new KFoldCrossValidation(smallSample, 5, 1);
        String[] expected2 = {"7", "10"};
        assertArrayEquals(expected2, kFoldCrossValidation.getTestFold(0).toArray());
    }

    @Test
    public void testSmallSample2Fold() {
        KFoldCrossValidation kFoldCrossValidation = new KFoldCrossValidation(smallSample, 2, 1);
        String[] expected3 = {"7", "10", "8", "9", "5"};
        assertArrayEquals(expected3, kFoldCrossValidation.getTestFold(0).toArray());
    }

    @Test
    public void testLargeSample10Fold() {
        HashSet<Integer> items;
        KFoldCrossValidation kFoldCrossValidation = new KFoldCrossValidation(largeSample, 10, 1);
        for (int i = 0; i < 10; i++){
            items = new HashSet<>();
            items.addAll(kFoldCrossValidation.getTrainFold(i));
            items.addAll(kFoldCrossValidation.getTestFold(i));
            assertEquals(100, kFoldCrossValidation.getTestFold(i).size());
            assertEquals(900, kFoldCrossValidation.getTrainFold(i).size());
            assertEquals(1000, items.size());
        }
    }

    @Test
    public void testLargeSample5Fold() {
        HashSet<Integer> items;
        KFoldCrossValidation kFoldCrossValidation = new KFoldCrossValidation(largeSample, 5, 1);
        for (int i = 0; i < 5; i++){
            items = new HashSet<>();
            items.addAll(kFoldCrossValidation.getTrainFold(i));
            items.addAll(kFoldCrossValidation.getTestFold(i));
            assertEquals(200, kFoldCrossValidation.getTestFold(i).size());
            assertEquals(800, kFoldCrossValidation.getTrainFold(i).size());
            assertEquals(1000, items.size());
        }
    }

    @Test
    public void testLargeSample2Fold() {
        HashSet<Integer> items;
        KFoldCrossValidation kFoldCrossValidation = new KFoldCrossValidation(largeSample, 2, 1);
        for (int i = 0; i < 2; i++){
            items = new HashSet<>();
            items.addAll(kFoldCrossValidation.getTrainFold(i));
            items.addAll(kFoldCrossValidation.getTestFold(i));
            assertEquals(500, kFoldCrossValidation.getTestFold(i).size());
            assertEquals(500, kFoldCrossValidation.getTrainFold(i).size());
            assertEquals(1000, items.size());
        }
    }

}
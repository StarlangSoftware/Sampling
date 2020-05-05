package Sampling;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class StratifiedKFoldCrossValidationTest {
    ArrayList<String>[] smallSample;
    ArrayList<Integer>[] largeSample;

    @Before
    public void setUp() {
        String[] class1Data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ArrayList<String> inputClass1 = new ArrayList<>(Arrays.asList(class1Data));
        String[] class2Data = {"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
        ArrayList<String> inputClass2 = new ArrayList<>(Arrays.asList(class2Data));
        smallSample = new ArrayList[2];
        smallSample[0] = inputClass1;
        smallSample[1] = inputClass2;
        ArrayList<Integer> class1 = new ArrayList<>();
        for (int i = 0; i < 1000; i++){
            class1.add(i);
        }
        ArrayList<Integer> class2 = new ArrayList<>();
        for (int i = 0; i < 3000; i++){
            class2.add(1000 + i);
        }
        ArrayList<Integer> class3 = new ArrayList<>();
        for (int i = 0; i < 5000; i++){
            class3.add(4000 + i);
        }
        largeSample = new ArrayList[3];
        largeSample[0] = class1;
        largeSample[1] = class2;
        largeSample[2] = class3;
    }

    @Test
    public void testSmallSample10Fold() {
        StratifiedKFoldCrossValidation stratifiedKFoldCrossValidation = new StratifiedKFoldCrossValidation(smallSample, 10, 1);
        String[] expected1 = {"7", "13", "28"};
        assertArrayEquals(expected1, stratifiedKFoldCrossValidation.getTestFold(0).toArray());
    }

    @Test
    public void testSmallSample5Fold() {
        StratifiedKFoldCrossValidation stratifiedKFoldCrossValidation = new StratifiedKFoldCrossValidation(smallSample, 5, 1);
        String[] expected2 = {"7", "10", "13", "28", "17", "26"};
        assertArrayEquals(expected2, stratifiedKFoldCrossValidation.getTestFold(0).toArray());
    }

    @Test
    public void testSmallSample2Fold() {
        StratifiedKFoldCrossValidation stratifiedKFoldCrossValidation = new StratifiedKFoldCrossValidation(smallSample, 2, 1);
        String[] expected3 = {"7", "10", "8", "9", "5", "13", "28", "17", "26", "22", "24", "30", "19", "18", "23"};
        assertArrayEquals(expected3, stratifiedKFoldCrossValidation.getTestFold(0).toArray());
    }

    @Test
    public void testLargeSample10Fold() {
        HashSet<Integer> items;
        StratifiedKFoldCrossValidation stratifiedKFoldCrossValidation = new StratifiedKFoldCrossValidation(largeSample, 10, 1);
        for (int i = 0; i < 10; i++){
            items = new HashSet<>();
            ArrayList<Integer> trainFold = stratifiedKFoldCrossValidation.getTrainFold(i);
            ArrayList<Integer> testFold = stratifiedKFoldCrossValidation.getTestFold(i);
            items.addAll(trainFold);
            items.addAll(testFold);
            assertEquals(900, testFold.size());
            assertEquals(8100, trainFold.size());
            assertEquals(9000, items.size());
            int[] trainCounts = new int[3];
            for (Integer integer : trainFold) {
                if (integer < 1000) {
                    trainCounts[0]++;
                } else {
                    if (integer < 4000) {
                        trainCounts[1]++;
                    } else {
                        trainCounts[2]++;
                    }
                }
            }
            assertEquals(900, trainCounts[0]);
            assertEquals(2700, trainCounts[1]);
            assertEquals(4500, trainCounts[2]);
            int[] testCounts = new int[3];
            for (Integer integer : testFold) {
                if (integer < 1000) {
                    testCounts[0]++;
                } else {
                    if (integer < 4000) {
                        testCounts[1]++;
                    } else {
                        testCounts[2]++;
                    }
                }
            }
            assertEquals(100, testCounts[0]);
            assertEquals(300, testCounts[1]);
            assertEquals(500, testCounts[2]);
        }
    }

    @Test
    public void testLargeSample5Fold() {
        HashSet<Integer> items;
        StratifiedKFoldCrossValidation stratifiedKFoldCrossValidation = new StratifiedKFoldCrossValidation(largeSample, 5, 1);
        for (int i = 0; i < 5; i++){
            items = new HashSet<>();
            ArrayList<Integer> trainFold = stratifiedKFoldCrossValidation.getTrainFold(i);
            ArrayList<Integer> testFold = stratifiedKFoldCrossValidation.getTestFold(i);
            items.addAll(trainFold);
            items.addAll(testFold);
            assertEquals(1800, testFold.size());
            assertEquals(7200, trainFold.size());
            assertEquals(9000, items.size());
            int[] trainCounts = new int[3];
            for (Integer integer : trainFold) {
                if (integer < 1000) {
                    trainCounts[0]++;
                } else {
                    if (integer < 4000) {
                        trainCounts[1]++;
                    } else {
                        trainCounts[2]++;
                    }
                }
            }
            assertEquals(800, trainCounts[0]);
            assertEquals(2400, trainCounts[1]);
            assertEquals(4000, trainCounts[2]);
            int[] testCounts = new int[3];
            for (Integer integer : testFold) {
                if (integer < 1000) {
                    testCounts[0]++;
                } else {
                    if (integer < 4000) {
                        testCounts[1]++;
                    } else {
                        testCounts[2]++;
                    }
                }
            }
            assertEquals(200, testCounts[0]);
            assertEquals(600, testCounts[1]);
            assertEquals(1000, testCounts[2]);
        }
    }

    @Test
    public void testLargeSample2Fold() {
        HashSet<Integer> items;
        StratifiedKFoldCrossValidation stratifiedKFoldCrossValidation = new StratifiedKFoldCrossValidation(largeSample, 2, 1);
        for (int i = 0; i < 2; i++){
            items = new HashSet<>();
            ArrayList<Integer> trainFold = stratifiedKFoldCrossValidation.getTrainFold(i);
            ArrayList<Integer> testFold = stratifiedKFoldCrossValidation.getTestFold(i);
            items.addAll(trainFold);
            items.addAll(testFold);
            assertEquals(4500, testFold.size());
            assertEquals(4500, trainFold.size());
            assertEquals(9000, items.size());
            int[] trainCounts = new int[3];
            for (Integer integer : trainFold) {
                if (integer < 1000) {
                    trainCounts[0]++;
                } else {
                    if (integer < 4000) {
                        trainCounts[1]++;
                    } else {
                        trainCounts[2]++;
                    }
                }
            }
            assertEquals(500, trainCounts[0]);
            assertEquals(1500, trainCounts[1]);
            assertEquals(2500, trainCounts[2]);
            int[] testCounts = new int[3];
            for (Integer integer : testFold) {
                if (integer < 1000) {
                    testCounts[0]++;
                } else {
                    if (integer < 4000) {
                        testCounts[1]++;
                    } else {
                        testCounts[2]++;
                    }
                }
            }
            assertEquals(500, testCounts[0]);
            assertEquals(1500, testCounts[1]);
            assertEquals(2500, testCounts[2]);
        }
    }

}
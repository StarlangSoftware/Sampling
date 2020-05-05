package Sampling;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class BootstrapTest {
    ArrayList<String> smallSample;
    ArrayList<Integer> largeSample;

    @Before
    public void setUp() {
        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        smallSample = new ArrayList<>(Arrays.asList(data));
        largeSample = new ArrayList<>();
        for (int i = 0; i < 1000000; i++){
            largeSample.add(i);
        }
    }

    @Test
    public void testSmallSample() {
        Bootstrap<String> bootstrap = new Bootstrap<>(smallSample, 1);
        String[] sample = {"6", "9", "8", "4", "5", "5", "5", "7", "9", "9"};
        assertArrayEquals(sample, bootstrap.getSample().toArray());
    }

    @Test
    public void testLargeSample() {
        Bootstrap<Integer> bootstrap = new Bootstrap<Integer>(largeSample, 1);
        ArrayList<Integer> sample = bootstrap.getSample();
        HashSet<Integer> set = new HashSet<>();
        set.addAll(sample);
        assertEquals(set.size() / 1000000.0, 0.632, 0.001);
    }

}
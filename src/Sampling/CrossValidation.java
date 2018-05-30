package Sampling;

import java.util.ArrayList;

public abstract class CrossValidation<T> {
    protected int K;

    public abstract ArrayList<T> getTrainFold(int k);
    public abstract ArrayList<T> getTestFold(int k);

}

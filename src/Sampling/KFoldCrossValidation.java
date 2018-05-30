package Sampling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KFoldCrossValidation<T> extends CrossValidation<T>{
    private List<T> instanceList;
    private int N;

    public KFoldCrossValidation(List<T> instanceList, int K, int seed){
        this.instanceList = instanceList;
        Collections.shuffle(instanceList, new Random(seed));
        N = instanceList.size();
        this.K = K;
    }

    public ArrayList<T> getTrainFold(int k){
        ArrayList<T> trainFold = new ArrayList<T>();
        for (int i = 0; i < (k * N) / K; i++){
            trainFold.add(instanceList.get(i));
        }
        for (int i = ((k + 1) * N) / K; i < N; i++){
            trainFold.add(instanceList.get(i));
        }
        return trainFold;
    }

    public ArrayList<T> getTestFold(int k){
        ArrayList<T> testFold = new ArrayList<T>();
        for (int i = (k * N) / K; i < ((k + 1) * N) / K; i++){
            testFold.add(instanceList.get(i));
        }
        return testFold;
    }

}

package Sampling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class StratifiedKFoldCrossValidation<T> extends CrossValidation<T>{
    private ArrayList<T>[] instanceLists;
    private int[] N;

    public StratifiedKFoldCrossValidation(ArrayList<T>[] instanceLists, int K, int seed){
        this.instanceLists = instanceLists;
        N = new int[instanceLists.length];
        for (int i = 0; i < instanceLists.length; i++){
            Collections.shuffle(instanceLists[i], new Random(seed));
            N[i] = instanceLists[i].size();
        }
        this.K = K;
    }

    public ArrayList<T> getTrainFold(int k){
        ArrayList<T> trainFold = new ArrayList<T>();
        for (int i = 0; i < N.length; i++){
            for (int j = 0; j < (k * N[i]) / K; j++){
                trainFold.add(instanceLists[i].get(j));
            }
            for (int j = ((k + 1) * N[i]) / K; j < N[i]; j++){
                trainFold.add(instanceLists[i].get(j));
            }
        }
        return trainFold;
    }

    public ArrayList<T> getTestFold(int k){
        ArrayList<T> testFold = new ArrayList<T>();
        for (int i = 0; i < N.length; i++){
            for (int j = (k * N[i]) / K; j < ((k + 1) * N[i]) / K; j++){
                testFold.add(instanceLists[i].get(j));
            }
        }
        return testFold;
    }

}

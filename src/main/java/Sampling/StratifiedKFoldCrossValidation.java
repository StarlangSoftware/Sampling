package Sampling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class StratifiedKFoldCrossValidation<T> extends CrossValidation<T>{
    private ArrayList<T>[] instanceLists;
    private int[] N;

    /**
     * Constructor of StratifiedKFoldCrossValidation class which takes a list of instanceList, K and seed number as inputs.
     * It initializes instanceList, N as size of instanceList and K, also shuffles the given instanceList randomly.
     * @param instanceLists data given
     * @param K fold number
     * @param seed integer input for random generator
     */
    public StratifiedKFoldCrossValidation(ArrayList<T>[] instanceLists, int K, int seed){
        this.instanceLists = instanceLists;
        N = new int[instanceLists.length];
        for (int i = 0; i < instanceLists.length; i++){
            Collections.shuffle(instanceLists[i], new Random(seed));
            N[i] = instanceLists[i].size();
        }
        this.K = K;
    }

    /**
     * This method returns the train data of the given instanceList.
     * @param k number of groups that a given data is to be split into
     * @return arrayList<T> trainFold
     */
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

    /**
     * This method returns the test data of the given instanceList.
     * @param k number of groups that a given data is to be split into
     * @return arrayList<T> testFold
     */
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

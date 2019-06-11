package Sampling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KFoldCrossValidation<T> extends CrossValidation<T>{
    private List<T> instanceList;
    private int N;

    /**
     * Constructor of KFoldCrossValidation class which takes a list of instanceList, K and seed number as inputs.
     * It initializes instanceList, N as size of instanceList and K, also shuffles the given instanceList randomly.
     * @param instanceList data given
     * @param K fold number
     * @param seed integer input for random generator
     */
    public KFoldCrossValidation(List<T> instanceList, int K, int seed){
        this.instanceList = instanceList;
        Collections.shuffle(instanceList, new Random(seed));
        N = instanceList.size();
        this.K = K;
    }

    /**
     * This method returns the train data of the given instanceList.
     * @param k number of groups that a given data is to be split into
     * @return arrayList<T> trainFold
     */
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

    /**
     * This method returns the test data of the given instanceList.
     * @param k number of groups that a given data is to be split into
     * @return arrayList<T> testFold
     */
    public ArrayList<T> getTestFold(int k){
        ArrayList<T> testFold = new ArrayList<T>();
        for (int i = (k * N) / K; i < ((k + 1) * N) / K; i++){
            testFold.add(instanceList.get(i));
        }
        return testFold;
    }

}

package Sampling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class StratifiedKFoldCrossValidation<T> extends CrossValidation<T>{
    private ArrayList<T>[] instanceLists;
    private int[] N;

    /**
     * A constructor of {@link StratifiedKFoldCrossValidation} class which takes as set of class samples as an array of array of instances, a K (K in K-fold cross-validation) and a seed number,
     * then shuffles each class sample using the seed number.
     *
     * @param instanceLists Original class samples. Each element of the this array is a sample only from one class.
     * @param K K in K-fold cross-validation
     * @param seed Random number to create K-fold sample(s)
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
     * getTrainFold returns the k'th train fold in K-fold stratified cross-validation.
     *
     * @param k index for the k'th train fold of the K-fold stratified cross-validation
     * @return Produced training sample
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
     * getTestFold returns the k'th test fold in K-fold stratified cross-validation.
     *
     * @param k index for the k'th test fold of the K-fold stratified cross-validation
     * @return Produced testing sample
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

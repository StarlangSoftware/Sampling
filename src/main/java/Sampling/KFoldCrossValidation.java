package Sampling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KFoldCrossValidation<T> extends CrossValidation<T>{
    private List<T> instanceList;
    private int N;

    /**
     * A constructor of {@link KFoldCrossValidation} class which takes a sample as an array of instances, a K (K in K-fold cross-validation) and a seed number,
     * then shuffles the original sample using this seed as random number.
     *
     * @param instanceList Original sample
     * @param K K in K-fold cross-validation
     * @param seed Random number to create K-fold sample(s)
     */
    public KFoldCrossValidation(List<T> instanceList, int K, int seed){
        this.instanceList = instanceList;
        Collections.shuffle(instanceList, new Random(seed));
        N = instanceList.size();
        this.K = K;
    }

    /**
     * getTrainFold returns the k'th train fold in K-fold cross-validation.
     *
     * @param k index for the k'th train fold of the K-fold cross-validation
     * @return Produced training sample
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
     * getTestFold returns the k'th test fold in K-fold cross-validation.
     *
     * @param k index for the k'th test fold of the K-fold cross-validation
     * @return Produced testing sample
     */
    public ArrayList<T> getTestFold(int k){
        ArrayList<T> testFold = new ArrayList<T>();
        for (int i = (k * N) / K; i < ((k + 1) * N) / K; i++){
            testFold.add(instanceList.get(i));
        }
        return testFold;
    }

}

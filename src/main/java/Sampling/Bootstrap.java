package Sampling;

import java.util.ArrayList;
import java.util.Random;

public class Bootstrap<T> {
    private ArrayList<T> instanceList;

    /**
     * Constructor of Bootstrap class.
     * It loops through the size of given data size and generates a sample distribution of random samples from given sample.
     * @param instanceList data to resample
     * @param seed integer input for random generator
     */
    public Bootstrap(ArrayList<T> instanceList, int seed){
        int N;
        Random random = new Random(seed);
        N = instanceList.size();
        this.instanceList = new ArrayList<T>();
        for (int i = 0; i < N; i++){
            this.instanceList.add(instanceList.get(random.nextInt(N)));
        }
    }

    /**
     * accessor method to return instance list
     * @return arrayList<T> instanceList
     */
    public ArrayList<T> getSample(){
        return instanceList;
    }

}

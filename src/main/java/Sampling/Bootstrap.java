package Sampling;

import java.util.ArrayList;
import java.util.Random;

public class Bootstrap<T> {
    private final ArrayList<T> instanceList;

    /**
     * A constructor of {@link Bootstrap} class which takes a sample an array of instances and a seed number, then creates a bootstrap
     * sample using this seed as random number.
     *
     * @param instanceList  Original sample
     * @param seed Random number to create boostrap sample
     */
    public Bootstrap(ArrayList<T> instanceList, int seed){
        int N;
        Random random = new Random(seed);
        N = instanceList.size();
        this.instanceList = new ArrayList<>();
        for (int i = 0; i < N; i++){
            this.instanceList.add(instanceList.get(random.nextInt(N)));
        }
    }

    /**
     * getSample returns the produced bootstrap sample.
     *
     * @return Produced bootstrap sample
     */
    public ArrayList<T> getSample(){
        return instanceList;
    }

}

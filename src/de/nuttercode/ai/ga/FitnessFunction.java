package de.nuttercode.ai.ga;

public interface FitnessFunction<T> {

	double getFitness(T specimen);

}

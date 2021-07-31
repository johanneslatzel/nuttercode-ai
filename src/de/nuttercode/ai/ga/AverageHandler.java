package de.nuttercode.ai.ga;

@FunctionalInterface
public interface AverageHandler {
	void handleAverage(int cycle, double[] averages);
}
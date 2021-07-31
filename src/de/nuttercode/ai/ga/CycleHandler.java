package de.nuttercode.ai.ga;

@FunctionalInterface
public interface CycleHandler<T> {
	void handleCycle(int cycle, Specimen<T> best);
}
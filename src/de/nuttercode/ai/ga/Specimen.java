package de.nuttercode.ai.ga;

public final class Specimen<T> {

	private int age;
	private double fitness;
	private boolean isScored;
	private final T specimen;
	private final FitnessFunction<T> fitnessFunction;

	public Specimen(T specimen, FitnessFunction<T> fitnessFunction) {
		this.specimen = specimen;
		this.fitnessFunction = fitnessFunction;
		age = 0;
		fitness = 0;
		isScored = false;
	}

	public T getSpecimen() {
		return specimen;
	}

	public int getAge() {
		return age;
	}

	public void increaseAge() {
		age++;
	}

	/**
	 * @return fitness of this individual >= 0
	 */
	public double getFitness() {
		if (!isScored) {
			fitness = fitnessFunction.getFitness(specimen);
			isScored = true;
		}
		return fitness;
	}

	public void resetFitness() {
		isScored = false;
	}

	@Override
	public String toString() {
		return "Specimen [age=" + age + ", fitness=" + fitness + ", isScored=" + isScored + "]";
	}

}

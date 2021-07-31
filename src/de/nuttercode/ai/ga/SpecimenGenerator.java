package de.nuttercode.ai.ga;

import java.util.Random;

public abstract class SpecimenGenerator<T> {

	private final Random random;
	private final FitnessFunction<T> fitnessFunction;

	public SpecimenGenerator(long seed, FitnessFunction<T> fitnessFunction) {
		random = new Random(seed);
		this.fitnessFunction = fitnessFunction;
	}

	protected final Random getRandom() {
		return random;
	}

	protected final Specimen<T> create(T specimen) {
		return new Specimen<>(specimen, fitnessFunction);
	}

	protected final Specimen<T> create() {
		return create(generate());
	}

	protected final Specimen<T> crossover(Specimen<T> mother, Specimen<T> father) {
		return create(onCrossover(mother, father));
	}

	public abstract T generate();

	public abstract void mutate(Specimen<T> specimen);

	public abstract T onCrossover(Specimen<T> mother, Specimen<T> father);

}

package de.nuttercode.ai.ga;

import java.util.Collection;

import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.NotNull;

public abstract class GeneticAlgorithm<T> {

	private Specimen<T> best;
	private final GaConfiguration<T> gaConfiguration;
	private final SpecimenGenerator<T> specimenGenerator;
	private long timeSpent;
	private int lastImprovement;
	private long start;

	protected GeneticAlgorithm(@NotNull GaConfiguration<T> gaConfiguration,
			@NotNull SpecimenGenerator<T> specimenGenerator) {
		Assurance.assureNotNull(gaConfiguration);
		Assurance.assureNotNull(specimenGenerator);
		this.gaConfiguration = gaConfiguration;
		this.specimenGenerator = specimenGenerator;
		timeSpent = 0;
		lastImprovement = 0;
		start = 0;
	}

	protected boolean hasImprovedRecently() {
		return lastImprovement < gaConfiguration.getImprovementStopCount();
	}

	protected void increaseLastImprovement() {
		lastImprovement++;
	}

	protected final GaConfiguration<T> getGaConfiguration() {
		return gaConfiguration;
	}

	protected final SpecimenGenerator<T> getSpecimenGenerator() {
		return specimenGenerator;
	}

	protected final void setBest(Specimen<T> best) {
		this.best = best;
		lastImprovement = 0;
	}

	public final Specimen<T> getBest() {
		return best;
	}

	public final void init() {
		setBest(specimenGenerator.create());
		onInit();
	}

	public final void search() {
		start = System.currentTimeMillis();
		onSearch();
		setTimeSpent();
	}

	protected final void setTimeSpent() {
		timeSpent = System.currentTimeMillis() - start;
	}

	protected abstract void onSearch();

	protected abstract void onInit();

	public abstract void populateWith(Collection<T> initialPopulation);

	public abstract void populateWith(T specimen);

	public long getTimeSpent() {
		return timeSpent;
	}

}

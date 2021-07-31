package de.nuttercode.ai.ga;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GaDefault<T> extends GeneticAlgorithm<T> {

	private final List<Specimen<T>> population;
	private final List<Specimen<T>> nextGeneration;
	private final Random random;
	private Specimen<T> generationBest;

	public GaDefault(GaConfiguration<T> gaConfiguration, SpecimenGenerator<T> specimenGenerator) {
		super(gaConfiguration, specimenGenerator);
		population = new ArrayList<>();
		nextGeneration = new ArrayList<>();
		random = new Random(gaConfiguration.getSeed());
	}

	@Override
	public void onSearch() {
		GaConfiguration<T> gaConfiguration = getGaConfiguration();
		GenerationHandler<T> generationHandler = gaConfiguration.getGenerationHandler();
		AverageHandler averageHandler = gaConfiguration.getAverageHandler();
		int maxGenerations = gaConfiguration.getMaxGenerations();
		boolean stopWhenNotImproving = gaConfiguration.isStopWhenNotImproving();
		for (int generation = 0; generation < maxGenerations && (!stopWhenNotImproving || hasImprovedRecently())
				&& getTimeSpent() < gaConfiguration.getMaxTimeSpent(); generation++) {
			selectSurvivors();
			populate();
			findBest();
			if (generationHandler != null)
				generationHandler.handle(generation, Collections.unmodifiableCollection(population));
			if (averageHandler != null)
				averageHandler.handleAverage(generation,
						population.stream().mapToDouble(s -> s.getFitness()).toArray());
			setTimeSpent();
		}
	}

	private void findBest() {
		if (population.size() > 0) {
			generationBest = null;
			for (Specimen<T> specimen : population)
				if (generationBest == null || specimen.getFitness() > generationBest.getFitness())
					generationBest = specimen;
			if (getBest() == null || generationBest.getFitness() > getBest().getFitness())
				setBest(generationBest);
		}
	}

	private void populate() {
		GaConfiguration<T> gaConfiguration = getGaConfiguration();
		SpecimenGenerator<T> specimenGenerator = getSpecimenGenerator();
		while (population.size() < 2)
			population.add(specimenGenerator.create());
		while (population.size() < gaConfiguration.getMaxPopulationSize()) {
			Specimen<T> offspring = specimenGenerator.crossover(selectMate(), selectMate());
			specimenGenerator.mutate(offspring);
			population.add(offspring);
		}
	}

	private Specimen<T> selectMate() {
		return population.get(random.nextInt(population.size()));
	}

	private void selectSurvivors() {
		GaConfiguration<T> gaConfiguration = getGaConfiguration();
		int minSize = gaConfiguration.getMinPopulationSize();
		if (population.size() > minSize) {
			nextGeneration.clear();
			if (gaConfiguration.isEnableElitismn()) {
				population.sort((a, b) -> Double.compare(b.getFitness(), a.getFitness()));
				int eliteCount = (int) (minSize * gaConfiguration.getElitismnPercentage());
				for (int a = 0; a < eliteCount; a++)
					nextGeneration.add(population.remove(0));
			}
			double sum = 0;
			double targetValue;
			double currentValue;
			double currentFitness;
			boolean survivorSelected;
			for (Specimen<T> specimen : population)
				sum += specimen.getFitness();
			while (sum > 0 && nextGeneration.size() < minSize) {
				targetValue = sum * random.nextDouble();
				currentValue = 0;
				survivorSelected = false;
				for (int a = 0; a < population.size() && !survivorSelected; a++) {
					currentFitness = population.get(a).getFitness();
					currentValue += currentFitness;
					if (currentValue >= targetValue) {
						nextGeneration.add(population.remove(a));
						survivorSelected = true;
						sum -= currentFitness;
					}
				}
				if (!survivorSelected) {
					sum -= population.get(0).getFitness();
					nextGeneration.add(population.remove(0));
				}
			}
			population.clear();
			population.addAll(nextGeneration);
		}
	}

	@Override
	protected void onInit() {
		population.clear();
	}

	@Override
	public void populateWith(Collection<T> initialPopulation) {
		for (T specimen : initialPopulation)
			populateWith(specimen);
	}

	@Override
	public void populateWith(T specimen) {
		population.add(getSpecimenGenerator().create(specimen));
	}

}

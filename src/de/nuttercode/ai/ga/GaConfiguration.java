package de.nuttercode.ai.ga;

import de.nuttercode.util.assurance.Assurance;
import de.nuttercode.util.assurance.InDoubleRange;
import de.nuttercode.util.assurance.NotNegative;
import de.nuttercode.util.assurance.Positive;

public class GaConfiguration<T> {

	private int cycles;
	private int maxPopulationSize;
	private int minPopulationSize;
	private float mutationProbability;
	private int cycleTime;
	private int generationTime;
	private int maxGenerations;
	private long seed;
	private int geneticIslandCount;
	private int ageThreshold;
	private int eliteSize;
	private int tournamentCount;
	private CycleHandler<T> cycleHandler;
	private AverageHandler averageHandler;
	private GenerationHandler<T> generationHandler;
	private boolean isRandomSeed;
	private double fitnessThreshold;
	private boolean removeSubAverage;
	private double elitismnPercentage;
	private int improvementStopCount;
	private long maxTimeSpent;
	private boolean stopWhenNotImproving;

	public GaConfiguration() {
		setCycles(13);
		setMaxPopulationSize(200);
		setMinPopulationSize(100);
		setMutationProbability(0.5f);
		setCycleTime(17);
		setGenerationTime(71);
		setSeed(42);
		setGeneticIslandCount(Math.max(Runtime.getRuntime().availableProcessors() - 1, 1));
		setAgeThreshold(10);
		setEliteSize(10);
		setTournamentCount(4);
		setIsRandomSeed(false);
		setFitnessThreshold(Double.NEGATIVE_INFINITY);
		setRemoveSubAverage(true);
		setMaxGenerations(1000);
		setCycleHandler(null);
		setAverageHandler(null);
		setGenerationHandler(null);
		setElitismnPercentage(0.5);
		setImprovementStopCount(Integer.MAX_VALUE);
		setMaxTimeSpent(Long.MAX_VALUE);
		setStopWhenNotImproving(false);
	}

	public boolean isStopWhenNotImproving() {
		return stopWhenNotImproving;
	}

	public void setStopWhenNotImproving(boolean stopWhenNotImproving) {
		this.stopWhenNotImproving = stopWhenNotImproving;
	}

	public void setFitnessThreshold(double fitnessThreshold) {
		this.fitnessThreshold = fitnessThreshold;
	}

	public void setIsRandomSeed(boolean isRandomSeed) {
		this.isRandomSeed = isRandomSeed;
	}

	public int getCycles() {
		return cycles;
	}

	public void setCycles(@Positive int cycles) {
		Assurance.assurePositive(cycles);
		this.cycles = cycles;
	}

	public int getMaxPopulationSize() {
		return maxPopulationSize;
	}

	public void setMaxPopulationSize(@Positive int maxPopulationSize) {
		Assurance.assurePositive(maxPopulationSize);
		this.maxPopulationSize = maxPopulationSize;
	}

	public float getMutationProbability() {
		return mutationProbability;
	}

	public void setMutationProbability(@InDoubleRange(begin = 0, end = 1) float mutationProbability) {
		Assurance.assureBoundaries(mutationProbability, 0, 1);
		this.mutationProbability = mutationProbability;
	}

	public int getCycleTime() {
		return cycleTime;
	}

	public void setCycleTime(@Positive int cycleTime) {
		Assurance.assurePositive(cycleTime);
		this.cycleTime = cycleTime;
	}

	public int getGenerationTime() {
		return generationTime;
	}

	public void setGenerationTime(@Positive int generationTime) {
		Assurance.assurePositive(generationTime);
		this.generationTime = generationTime;
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public int getGeneticIslandCount() {
		return geneticIslandCount;
	}

	public void setGeneticIslandCount(@Positive int geneticIslandCount) {
		Assurance.assurePositive(geneticIslandCount);
		this.geneticIslandCount = geneticIslandCount;
	}

	public int getAgeThreshold() {
		return ageThreshold;
	}

	public void setAgeThreshold(@Positive int ageThreshold) {
		this.ageThreshold = ageThreshold;
	}

	public int getEliteSize() {
		return eliteSize;
	}

	public void setEliteSize(@NotNegative int eliteSize) {
		Assurance.assureNotNegative(eliteSize);
		this.eliteSize = eliteSize;
	}

	public int getTournamentCount() {
		return tournamentCount;
	}

	public void setTournamentCount(@Positive int tournamentCount) {
		Assurance.assurePositive(tournamentCount);
		this.tournamentCount = tournamentCount;
	}

	public void setCycleHandler(CycleHandler<T> cycleHandler) {
		this.cycleHandler = cycleHandler;
	}

	public void setAverageHandler(AverageHandler averageHandler) {
		this.averageHandler = averageHandler;
	}

	public CycleHandler<T> getCycleHandler() {
		return cycleHandler;
	}

	public AverageHandler getAverageHandler() {
		return averageHandler;
	}

	public boolean isRandomSeed() {
		return isRandomSeed;
	}

	public double getFitnessThreshold() {
		return fitnessThreshold;
	}

	public boolean isRemoveSubAverage() {
		return removeSubAverage;
	}

	public void setRemoveSubAverage(boolean removeSubAverage) {
		this.removeSubAverage = removeSubAverage;
	}

	public int getMinPopulationSize() {
		return minPopulationSize;
	}

	public void setMinPopulationSize(int minPopulationSize) {
		this.minPopulationSize = minPopulationSize;
	}

	public int getMaxGenerations() {
		return maxGenerations;
	}

	public void setMaxGenerations(int maxGenerations) {
		this.maxGenerations = maxGenerations;
	}

	public GenerationHandler<T> getGenerationHandler() {
		return generationHandler;
	}

	public void setGenerationHandler(GenerationHandler<T> generationhandler) {
		this.generationHandler = generationhandler;
	}

	public boolean isEnableElitismn() {
		return elitismnPercentage > 0;
	}

	public double getElitismnPercentage() {
		return elitismnPercentage;
	}

	public void setElitismnPercentage(double elitismnPercentage) {
		Assurance.assureBoundaries(elitismnPercentage, 0, 1);
		this.elitismnPercentage = elitismnPercentage;
	}

	public void setImprovementStopCount(int improvementStopCount) {
		this.improvementStopCount = improvementStopCount;
	}

	public int getImprovementStopCount() {
		return improvementStopCount;
	}

	public long getMaxTimeSpent() {
		return maxTimeSpent;
	}

	public void setMaxTimeSpent(long maxTimeSpent) {
		this.maxTimeSpent = maxTimeSpent;
	}

}

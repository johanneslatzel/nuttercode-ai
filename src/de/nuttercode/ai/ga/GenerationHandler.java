package de.nuttercode.ai.ga;

import java.util.Collection;

@FunctionalInterface
public interface GenerationHandler<T> {

	void handle(int generation, Collection<Specimen<T>> population);

}

package io.github.mac_genius.pgen.arguments;

import java.util.Collection;
import java.util.TreeSet;

/**
 * This class simply contains a list of example input test cases with their expected output files.
 *
 * @author John Harrison
 */
public class SampleFiles {
    private Collection<String> inputs;
    private Collection<String> answers;

    /**
     * The constructor for SampleFiles.
     */
    public SampleFiles() {
        inputs = new TreeSet<>();
        answers = new TreeSet<>();
    }

    /**
     * The constructor for SampleFiles.
     *
     * @param inputs - a list of example input test cases
     * @param answers - a list of example output test cases
     */
    public SampleFiles(Collection<String> inputs, Collection<String> answers) {
        this.inputs = inputs;
        this.answers = answers;
    }

    /**
     * Returns a list of example input test cases.
     *
     * @return a list of example input test cases
     */
    public Collection<String> getInputs() {
        return inputs;
    }

    /**
     * Returns a list of example output test cases.
     *
     * @return a list of example output test cases
     */
    public Collection<String> getAnswers() {
        return answers;
    }
}

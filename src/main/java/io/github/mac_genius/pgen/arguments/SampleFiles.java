package io.github.mac_genius.pgen.arguments;

import java.util.Collection;
import java.util.TreeSet;

/**
 * Created by Mac on 10/14/16.
 */
public class SampleFiles {
    private Collection<String> inputs;
    private Collection<String> answers;

    public SampleFiles() {
        inputs = new TreeSet<>();
        answers = new TreeSet<>();
    }

    public SampleFiles(Collection<String> inputs, Collection<String> answers) {
        this.inputs = inputs;
        this.answers = answers;
    }

    public Collection<String> getInputs() {
        return inputs;
    }

    public Collection<String> getAnswers() {
        return answers;
    }
}

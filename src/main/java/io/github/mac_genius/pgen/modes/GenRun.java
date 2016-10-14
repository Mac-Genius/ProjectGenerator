package io.github.mac_genius.pgen.modes;

import io.github.mac_genius.pgen.arguments.SampleFiles;

/**
 * The interface for different modes of the project generator.
 *
 * @author John Harrison
 */
public interface GenRun {

    /**
     * Runs the current mode.
     */
    void run();

    /**
     * Creates the project directory.
     */
    void createProjectDirectory();

    /**
     * Creates the template file.
     */
    void createTemplateFile();

    /**
     * Creates a make file from the give test case files.
     *
     * @param inputFiles - a list of example test cases
     */
    void createMakeFile(SampleFiles inputFiles);
}

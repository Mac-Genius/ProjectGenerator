package io.github.mac_genius.pgen.arguments;

import io.github.mac_genius.pgen.FileTemplate;
import io.github.mac_genius.pgen.RunMode;

/**
 * Arguments contains all of the parsed command line arguments.
 *
 * @author John Harrison
 */
public class Arguments {
    private RunMode mode;
    private int exampleSize;
    private String fileName;
    private FileTemplate template;

    /**
     * The constructor for Arguments.
     *
     * @param mode - the mode to run the project generator in (local, kattis, etc.)
     * @param exampleSize - the amount of example test cases to generate (only available in local mode)
     * @param fileName - the name of the project
     * @param template - the template to use for the project
     */
    public Arguments(RunMode mode, int exampleSize, String fileName, FileTemplate template) {
        this.mode = mode;
        this.exampleSize = exampleSize;
        this.fileName = fileName;
        this.template = template;
    }

    /**
     * Returns the mode in which to run the generator.
     *
     * @return the mode for the generator
     */
    public RunMode getMode() {
        return mode;
    }

    /**
     * Returns the amount of example input test cases to generate.
     *
     * @return the amount of example input test cases to generate
     */
    public int getExampleSize() {
        return exampleSize;
    }

    /**
     * Returns the name of the project.
     *
     * @return a String containing the name of the project
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * The template to use for the generator.
     *
     * @return the template for the generator
     */
    public FileTemplate getFileTemplate() {
        return template;
    }
}

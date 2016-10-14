package io.github.mac_genius.pgen.arguments;

import io.github.mac_genius.pgen.FileTemplate;
import io.github.mac_genius.pgen.RunMode;

/**
 * Builds an Arguments class from command line arguments.
 *
 * @author John Harrison
 */
public class ArgumentBuilder {
    private RunMode mode;
    private int exampleSize;
    private String fileName;
    private FileTemplate template;
    private boolean exampleSizeManual = false;

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

    /**
     * Sets the mode for the project generator.
     *
     * @param mode - the mode to run the generator in (local, kattis, etc.)
     * @return the builder
     */
    public ArgumentBuilder setMode(RunMode mode) {
        this.mode = mode;
        return this;
    }

    /**
     * Sets the amount of example input cases to generate.
     *
     * @param exampleSize - the amount of example input cases to generate
     * @return the builder
     */
    public ArgumentBuilder setExampleSize(int exampleSize) {
        this.exampleSize = exampleSize;
        exampleSizeManual = true;
        return this;
    }

    /**
     * Sets the name of the project.
     *
     * @param fileName - the name of the project
     * @return the builder
     */
    public ArgumentBuilder setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    /**
     * Sets the type of template to use.
     *
     * @param template - the template to use
     * @return the builder
     */
    public ArgumentBuilder setFileTemplate(FileTemplate template) {
        this.template = template;
        return this;
    }

    /**
     * Returns whether the example size was set manually or not.
     *
     * @return true if the example size was set manually, else false
     */
    public boolean isExampleSizeSetManual() {
        return exampleSizeManual;
    }

    /**
     * Builds the Arguments.
     *
     * @return the arguments
     */
    public Arguments build() {
        return new Arguments(mode, exampleSize, fileName, template);
    }
}

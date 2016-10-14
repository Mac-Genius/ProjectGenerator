package io.github.mac_genius.pgen.modes;

import io.github.mac_genius.pgen.FileTemplate;
import io.github.mac_genius.pgen.arguments.SampleFiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The default run choice for the project generator. It will create the project directory, the template file, a
 * makefile, and empty example test cases.
 *
 * @author John Harrison
 */
public class LocalRun extends BaseRun {
    private int amount;

    /**
     * Constructor for LocalRun.
     *
     * @param template - the template to use for this run
     * @param projectDir - the project directory to create
     * @param fileName - the name of the directory
     * @param amount - the amount of example test cases to generate
     */
    public LocalRun(FileTemplate template, File projectDir, String fileName, int amount) {
        super(template, projectDir, fileName);
        this.amount = amount;
    }

    /**
     * Runs the mode.
     */
    @Override
    public void run() {
        createProjectDirectory();
        createTemplateFile();
        Collection<String> files = createExampleFiles(amount);
        createMakeFile(new SampleFiles(files, null));
    }

    /**
     * Create the example files.
     *
     * @param amount - the amount of example files to create
     * @return a collection of example file names
     */
    private Collection<String> createExampleFiles(int amount) {
        ArrayList<String> files = new ArrayList<>();
        System.out.println("Generating example input files...");
        for (int i = 0; i < amount; i++) {
            try {
                String name = fileName + "-" + (i + 1) + ".in";
                files.add(name);
                File file = new File(projectDir, name);
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return files;
    }
}

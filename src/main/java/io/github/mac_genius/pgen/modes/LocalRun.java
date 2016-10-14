package io.github.mac_genius.pgen.modes;

import io.github.mac_genius.pgen.FileTemplate;
import io.github.mac_genius.pgen.arguments.SampleFiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Mac on 10/13/16.
 */
public class LocalRun extends BaseRun {
    private int amount;

    public LocalRun(FileTemplate template, File projectDir, String fileName, int amount) {
        super(template, projectDir, fileName);
        this.amount = amount;
    }

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
     * @param amount     - the amount of example files to create
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

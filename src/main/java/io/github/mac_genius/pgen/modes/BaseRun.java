package io.github.mac_genius.pgen.modes;

import io.github.mac_genius.pgen.FileTemplate;
import io.github.mac_genius.pgen.Template;
import io.github.mac_genius.pgen.arguments.SampleFiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * BaseRun implements some of the default methods that will be used by other modes, such as creating the project
 * directory.
 *
 * @author John Harrison
 */
public abstract class BaseRun implements GenRun {
    protected FileTemplate template;
    protected File projectDir;
    protected String fileName;

    /**
     * The constructor for BaseRun.
     *
     * @param template - the template to use for this run
     * @param projectDir - the project directory to create
     * @param fileName - the name of the directory
     */
    public BaseRun(FileTemplate template, File projectDir, String fileName) {
        this.template = template;
        this.projectDir = projectDir;
        this.fileName = fileName;
    }

    /**
     * Creates the project directory.
     */
    public void createProjectDirectory() {
        System.out.println("Creating project directory...");
        if (projectDir.exists()) {
            System.out.println(String.format("The directory %s already exist!", fileName));
            System.exit(1);
        } else {
            if (!projectDir.mkdir()) {
                System.out.println(String.format("Failed to create the %s directory!", fileName));
                System.exit(1);
            }
        }
    }

    /**
     * Creates a template .java file.
     */
    public void createTemplateFile() {
        System.out.println(String.format("Generating %s file...", template.getExt()));
        Template templateFile = template.getTemplate();
        templateFile.setName(fileName);
        templateFile.sub("name", fileName);
        templateFile.createFile(projectDir);
    }

    /**
     * Create the makefile.
     *
     * @param inputFiles - a list of example input file names
     */
    public void createMakeFile(SampleFiles inputFiles) {
        System.out.println("Generating makefile...");
        try {
            File file = new File(projectDir, "makefile");
            if (file.createNewFile()) {
                FileOutputStream out = new FileOutputStream(file, false);
                if (template.getExt().equals(".java")) {
                    out.write(getJavaMake(inputFiles).getBytes());
                } else if (template.getExt().equals(".cpp")) {
                    out.write(getCppMake(inputFiles).getBytes());
                }
                out.close();
            } else {
                System.out.println("Failed to create the makefile!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns what should be included in the java makefile.
     *
     * @param inputFiles - a list of example test cases
     * @return a String containing what should be included in the java makefile
     */
    public String getJavaMake(SampleFiles inputFiles) {
        String output = "default:\n\tjavac " + fileName + ".java\n";
        for (String inputFile : inputFiles.getInputs()) {
            output += "\tjava " + fileName + " < " + inputFile + "\n";
        }
        return output;
    }

    /**
     * Returns what should be included in the c++ makefile.
     *
     * @param inputFiles - a list of example test cases
     * @return a String containing what should be included in the c++ makefile
     */
    public String getCppMake(SampleFiles inputFiles) {
        String output = String.format("default: %s\n", fileName + ".o");
        output += String.format("\tgcc -o %s %s -I.\n", fileName, fileName + ".o");
        output += "\trm ./*.o\n";

        for (String inputFile : inputFiles.getInputs()) {
            output += String.format("\t./%s < %s\n", fileName, inputFile);
        }
        return output;
    }
}

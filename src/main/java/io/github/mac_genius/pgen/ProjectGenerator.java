package io.github.mac_genius.pgen;

import java.io.*;

/**
 * ProjectGenerator is designed to create template project directories for hacking with Kattis or another code site of
 * your choice. To use, simply type 'java -jar ProjectGenerator.jar <project name> <example files>'
 *
 * @author John Harrison
 */
public class ProjectGenerator {
    public static void main(String args[]) {
        if (args.length < 2) {
            System.out.println("usage: pgen <project name> <# of test cases>");
        } else {
            int amount = Integer.parseInt(args[1]);
            File projectDir = new File(System.getProperty("user.dir"), args[0]);
            if (projectDir.exists()) {
                System.out.println(args[1] + " already exists!");
            } else {
                System.out.println("Creating the project directory...");
                if (projectDir.mkdir()) {
                    createJavaFile(projectDir, args[0]);
                    createExampleFiles(projectDir, args[0], amount);
                    createMakeFile(projectDir, args[0], amount);
                    System.out.println("Done!");
                } else {
                    System.out.println("Failed to create the project directory!");
                }
            }
        }
    }

    /**
     * Creates a template .java file.
     *
     * @param projectDir - the directory to create the file in
     * @param fileName - the name of the project directory
     */
    private static void createJavaFile(File projectDir, String fileName) {
        System.out.println("Generating .java file...");
        try {
            File file = new File(projectDir, fileName + ".java");
            if (file.createNewFile()) {
                String output = "import java.util.*;\nimport java.io.*;\n\n";
                output += "public class " + fileName + " {\n";
                output += "    public static void main(String args[]) throws IOException {\n";
                output += "        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));\n\n";
                output += "    }\n";
                output += "}\n";
                FileOutputStream out = new FileOutputStream(file, false);
                out.write(output.getBytes());
                out.close();
            } else {
                System.out.println("Failed to generate the .java file!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the example files.
     *
     * @param projectDir - the directory to create the file in
     * @param fileName - the name of the project directory
     * @param amount - the amount of example files to create
     */
    private static void createExampleFiles(File projectDir, String fileName, int amount) {
        System.out.println("Generating example input files...");
        for (int i = 0; i < amount; i++) {
            try {
                File file = new File(projectDir, fileName + "-" + (i + 1) + ".in");
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Create the makefile.
     *
     * @param projectDir - the directory to create the file in
     * @param fileName - the name of the project directory
     * @param amount - the amount of example files to create
     */
    private static void createMakeFile(File projectDir, String fileName, int amount) {
        System.out.println("Generating makefile...");
        try {
            File file = new File(projectDir, "makefile");
            if (file.createNewFile()) {
                FileOutputStream out = new FileOutputStream(file, false);
                String output = "default:\n\t\tjavac " + fileName + ".java\n";
                for (int i = 0; i < amount; i++) {
                    output += "\t\tjava " + fileName + " < " + fileName + "-" + (i + 1) + ".in\n";
                }
                out.write(output.getBytes());
                out.close();
            } else {
                System.out.println("Failed to create the makefile!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

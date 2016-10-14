package io.github.mac_genius.pgen;

import io.github.mac_genius.pgen.arguments.ArgumentBuilder;
import io.github.mac_genius.pgen.arguments.Arguments;
import io.github.mac_genius.pgen.modes.GenRun;
import io.github.mac_genius.pgen.modes.KattisRun;
import io.github.mac_genius.pgen.modes.LocalRun;

import java.io.File;

/**
 * ProjectGenerator is designed to create template project directories for hacking with Kattis or another code site of
 * your choice. To use, simply type 'java -jar ProjectGenerator.jar <project name> <example files>'
 *
 * @author John Harrison
 */
public class ProjectGenerator {

    /**
     * The main function of the program.
     *
     * @param args - the arguments for the generator
     */
    public static void main(String args[]) {
        if (args.length < 1) {
            System.out.println("usage: pgen <project name> <args>");
        } else {
            Arguments arguments = parseArgs(args);
            File projectDir = new File(System.getProperty("user.dir"), arguments.getFileName());
            GenRun run = null;
            switch (arguments.getMode()) {
                case KATTIS:
                    run = new KattisRun(arguments.getFileTemplate(), projectDir, arguments.getFileName());
                    break;
                default:
                    run = new LocalRun(arguments.getFileTemplate(), projectDir, arguments.getFileName(), arguments.getExampleSize());
            }
            run.run();
        }
    }

    /**
     * Parses the arguments from the commandline and returns them.
     *
     * @param args - the arguments from the commandline
     * @return an Arguments class containing all of the parsed arguments from the command line
     */
    public static Arguments parseArgs(String args[]) {
        // Set defaults
        ArgumentBuilder builder = new ArgumentBuilder();
        builder.setFileName(args[0]);
        builder.setMode(RunMode.LOCAL);
        builder.setExampleSize(2);
        builder.setFileTemplate(FileTemplate.JAVA_DEFAUlT);

        for (int i = 1; i < args.length; i++) {
            switch (args[i]) {
                case "-h":
                case "-help":
                    help();
                    break;
                case "-size":
                case "-s":
                    builder.setExampleSize(Integer.parseInt(args[i + 1]));
                    i++;
                    break;
                case "-kattis":
                    builder.setMode(RunMode.KATTIS);
                    break;
                case "-local":
                    builder.setMode(RunMode.LOCAL);
                    break;
                case "-template":
                case "-t":
                    builder.setFileTemplate(FileTemplate.valueOf(args[i + 1].toUpperCase()));
                    i++;
                    break;
            }
        }
        if (builder.getMode() == RunMode.LOCAL && builder.isExampleSizeSetManual()) {
            System.out.println("No example size set, using default of 2.");
        }
        return builder.build();
    }

    /**
     * Prints out the help menu.
     */
    private static void help() {
        System.out.println("usage: pgen <project name> <args>");
        System.out.println("Arguments:");
        System.out.println("\t-h | -help : help menu");
        System.out.println("\t-s | -size <example file amount> : the amount of example files to create. Defaults to 2");
        System.out.println("\t-t | -template <template> : the template to use. Defaults to java_default");
        System.out.println("\t\tValid Templates:");
        System.out.println("\t\t\t* java_default");
        System.out.println("\t\t\t* cpp_default");
        System.out.println("\t-kattis : fetches the problems from Kattis and creates a makefile and test script for them");
        System.out.println("\t-local : fetches nothing and creates empty example files");
        System.exit(0);
    }
}

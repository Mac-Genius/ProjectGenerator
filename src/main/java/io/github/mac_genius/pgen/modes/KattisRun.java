package io.github.mac_genius.pgen.modes;

import io.github.mac_genius.pgen.FileTemplate;
import io.github.mac_genius.pgen.arguments.SampleFiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * The run for Kattis. This will generate the project directory, download the sample use cases with answers, create the
 * template file, a makefile, and a test script.
 *
 * @author John Harrison
 */
public class KattisRun extends BaseRun {

    /**
     * Constructor for KattisRun.
     *
     * @param template - the template to use for this run
     * @param projectDir - the project directory to create
     * @param fileName - the name of the directory
     */
    public KattisRun(FileTemplate template, File projectDir, String fileName) {
        super(template, projectDir, fileName);
    }

    /**
     * Runs the Kattis mode.
     */
    @Override
    public void run() {
        createProjectDirectory();
        SampleFiles inputFiles = downloadExampleFiles();
        createTemplateFile();
        createMakeFile(inputFiles);
        createTestScript(inputFiles);
    }

    /**
     * Downloads the example files from Kattis.
     *
     * @return the sample file names
     */
    private SampleFiles downloadExampleFiles() {
        TreeSet<String> inputFiles = new TreeSet<>();
        TreeSet<String> answerFiles = new TreeSet<>();
        
        try {
            System.out.println("Downloading sample files...");
            // Downloads file and saves it
            URL url = new URL("https://open.kattis.com/problems/" + fileName.toLowerCase() + "/file/statement/samples.zip");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Referer", "https://open.kattis.com/problems/" + fileName.toLowerCase());
            connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
            connection.connect();
            File samples = new File(projectDir, "samples.zip");
            samples.createNewFile();
            FileOutputStream out = new FileOutputStream(samples);
            byte[] array = new byte[8000];
            int size = 0;
            InputStream in = connection.getInputStream();
            while ((size = in.read(array)) != -1) {
                out.write(array, 0, size);
            }
            connection.disconnect();
            out.close();

            // Unzip the zip file
            ZipFile file = new ZipFile(samples);
            Enumeration<? extends ZipEntry> entries = file.entries();
            while (entries.hasMoreElements()) {
                ZipEntry current = entries.nextElement();
                if (isInputFile(current.getName())) {
                    inputFiles.add(current.getName());
                } else {
                    answerFiles.add(current.getName());
                }
                in = file.getInputStream(current);
                File entryFile = new File(projectDir, current.getName());
                entryFile.createNewFile();
                out = new FileOutputStream(entryFile);
                while ((size = in.read(array)) != -1) {
                    out.write(array, 0, size);
                }
                out.close();
                in.close();
            }
            samples.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SampleFiles(inputFiles, answerFiles);
    }

    private boolean isInputFile(String name) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9-_.]*\\.[a-zA-Z0-9]*(in)[a-zA-Z0-9]*");
        Matcher match = pattern.matcher(name);
        return match.matches();
    }

    /**
     * Create the makefile.
     *
     * @param inputFiles - a list of example input file names
     */
    @Override
    public void createMakeFile(SampleFiles inputFiles) {
        System.out.println("Generating makefile...");
        try {
            File file = new File(projectDir, "makefile");
            if (file.createNewFile()) {
                FileOutputStream out = new FileOutputStream(file, false);
                String output = "";
                if (template.getExt().equals(".java")) {
                    output += getJavaMake(new SampleFiles());
                } else if (template.getExt().equals(".cpp")) {
                    output += getCppMake(new SampleFiles());
                }
                output += "\t./test_script.sh\n";
                out.write(output.getBytes());
                out.close();
            } else {
                System.out.println("Failed to create the makefile!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the test script for running your program against the test cases.
     *
     * @param inputFiles - a list of example file names
     */
    public void createTestScript(SampleFiles inputFiles) {
        System.out.println("Generating the test script...");
        try {
            File file = new File(projectDir, "test_script.sh");
            if (file.createNewFile()) {
                file.setExecutable(true);
                FileOutputStream out = new FileOutputStream(file, false);
                if (template.getExt().equals(".java")) {
                    out.write(getJavaTestScript(inputFiles).getBytes());
                } else if (template.getExt().equals(".cpp")) {
                    out.write(getCppTestScript(inputFiles).getBytes());
                }
                out.close();
            } else {
                System.out.println("Failed to create test_script.sh");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns what should be included in the java test script.
     *
     * @param inputFiles - a list of example file names
     * @return a String containing the contents for the java test script
     */
    private String getJavaTestScript(SampleFiles inputFiles) {
        String output = "";
        int index = 1;
        Iterator<String> inputFile = inputFiles.getInputs().iterator();
        Iterator<String> outputFile = inputFiles.getAnswers().iterator();
        while (inputFile.hasNext()) {
            String tempIn = inputFile.next();
            String tempOut = outputFile.next();
            output += String.format("test%d=\"$(java %s < %s)\"\n", index, fileName, tempIn);
            output += String.format("if [[ $test%d == $(cat %s) ]]\n", index, tempOut);
            output += "then\n";
            output += String.format("    printf \"java %s < %s \\033[32m[Correct]\\033[0m\\nExpected: $(cat %s), Actual: ${test%d}\\n\\n\"\n", fileName, tempIn, tempOut, index);
            output += "else\n";
            output += String.format("printf \"java %s < %s \\033[31m[Incorrect]\\033[0m\\nExpected: $(cat %s), Actual: ${test%d}\\n\\n\"\n", fileName, tempIn, tempOut, index);
            output += "fi\n\n";
            index++;
        }
        return output;
    }

    /**
     * Returns what should be included in the c++ test script.
     *
     * @param inputFiles - a list of example file names
     * @return a String containing the contents for the c++ test script
     */
    private String getCppTestScript(SampleFiles inputFiles) {
        String output = "";
        int index = 1;
        Iterator<String> inputFile = inputFiles.getInputs().iterator();
        Iterator<String> outputFile = inputFiles.getAnswers().iterator();
        while (inputFile.hasNext()) {
            String tempIn = inputFile.next();
            String tempOut = outputFile.next();
            output += String.format("test%d=\"$(./%s < %s)\"\n", index, fileName, tempOut);
            output += String.format("if [[ $test%d == $(cat %s) ]]\n", index, tempOut);
            output += "then\n";
            output += String.format("    printf \"./%s < %s \\033[32m[Correct]\\033[0m\\nExpected: $(cat %s), Actual: ${test%d}\\n\\n\"\n", fileName, tempIn, tempOut, index);
            output += "else\n";
            output += String.format("printf \"./%s < %s \\033[31m[Incorrect]\\033[0m\\nExpected: $(cat %s), Actual: ${test%d}\\n\\n\"\n", fileName, tempIn, tempOut, index);
            output += "fi\n\n";
            index++;
        }
        return output;
    }
}

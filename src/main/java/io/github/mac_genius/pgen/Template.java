package io.github.mac_genius.pgen;

import java.io.*;

/**
 * Template stores the main info about a template file.
 *
 * @author John Harrison
 */
public class Template {
    private String uri;
    private String contents;
    private String name;
    private String ext;

    /**
     * Constructor for Template.
     *
     * @param uri - the name of the template file
     * @param ext - the extension of the template file
     */
    public Template(String uri, String ext) {
        this.uri = uri;
        this.ext = ext;
        loadFile();
    }

    /**
     * Loads the template file from the jar.
     */
    private void loadFile() {
        try {
            InputStream in = Template.class.getResourceAsStream(String.format("/templates/%s", uri));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] array = new byte[8000];
            int size = 0;
            while ((size = in.read(array)) != -1) {
                out.write(array, 0, size);
            }
            in.close();
            contents = out.toString();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the contents of the template file.
     *
     * @return a string containing the contents of the template file
     */
    public String getContents() {
        return contents;
    }

    /**
     * Substitutes a string in a template file for another string.
     *
     * @param find - the string to find in the template file
     * @param replace - the string to replace the find string
     */
    public void sub(String find, String replace) {
        this.contents = this.contents.replaceAll("\\$\\{" + find + "\\}", replace);
    }

    /**
     * Sets the name of the template file to be generated.
     *
     * @param name - the name of the file to be generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Creates the file using the contents of the template file.
     *
     * @param directory - the directory to put the file in
     */
    public void createFile(File directory) {
        try {
            File template = new File(directory, name + ext);
            if (template.createNewFile()) {
                FileOutputStream out = new FileOutputStream(template);
                out.write(contents.getBytes());
                out.close();
            } else {
                System.out.println(String.format("Failed to create %s!", name + ext));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

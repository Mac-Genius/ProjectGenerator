package io.github.mac_genius.pgen;

import java.io.*;

/**
 * Created by Mac on 10/13/16.
 */
public class Template {
    private String uri;
    private String contents;
    private String name;
    private String ext;

    public Template(String uri, String ext) {
        this.uri = uri;
        this.ext = ext;
        loadFile();
    }

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

    public String getContents() {
        return contents;
    }

    public void sub(String find, String replace) {
        this.contents = this.contents.replaceAll("\\$\\{" + find + "\\}", replace);
    }

    public void setName(String name) {
        this.name = name;
    }

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

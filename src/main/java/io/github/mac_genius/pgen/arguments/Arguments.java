package io.github.mac_genius.pgen.arguments;

import io.github.mac_genius.pgen.FileTemplate;
import io.github.mac_genius.pgen.RunMode;

/**
 * Created by Mac on 10/13/16.
 */
public class Arguments {
    private RunMode mode;
    private int exampleSize;
    private String fileName;
    private FileTemplate template;

    public Arguments(RunMode mode, int exampleSize, String fileName, FileTemplate template) {
        this.mode = mode;
        this.exampleSize = exampleSize;
        this.fileName = fileName;
        this.template = template;
    }

    public RunMode getMode() {
        return mode;
    }

    public int getExampleSize() {
        return exampleSize;
    }

    public String getFileName() {
        return fileName;
    }

    public FileTemplate getFileTemplate() {
        return template;
    }
}

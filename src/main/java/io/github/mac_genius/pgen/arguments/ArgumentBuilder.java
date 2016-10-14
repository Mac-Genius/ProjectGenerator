package io.github.mac_genius.pgen.arguments;

import io.github.mac_genius.pgen.FileTemplate;
import io.github.mac_genius.pgen.RunMode;

/**
 * Created by Mac on 10/14/16.
 */
public class ArgumentBuilder {
    private RunMode mode;
    private int exampleSize;
    private String fileName;
    private FileTemplate template;

    private boolean exampleSizeManual = false;

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

    public ArgumentBuilder setMode(RunMode mode) {
        this.mode = mode;
        return this;
    }

    public ArgumentBuilder setExampleSize(int exampleSize) {
        this.exampleSize = exampleSize;
        exampleSizeManual = true;
        return this;
    }

    public ArgumentBuilder setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public ArgumentBuilder setFileTemplate(FileTemplate template) {
        this.template = template;
        return this;
    }

    public boolean isExampleSizeSetManual() {
        return exampleSizeManual;
    }

    public Arguments build() {
        return new Arguments(mode, exampleSize, fileName, template);
    }
}

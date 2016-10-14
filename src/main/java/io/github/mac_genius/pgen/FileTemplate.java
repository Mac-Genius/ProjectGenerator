package io.github.mac_genius.pgen;

/**
 * All current templates matched to their respective template files.
 */
public enum FileTemplate {
    JAVA_DEFAUlT("JavaDefault.java.tmp", ".java"),
    CPP_DEFAULT("CppDefault.cpp.tmp", ".cpp");

    private String file;
    private String ext;

    FileTemplate(String file, String ext) {
        this.file = file;
        this.ext = ext;
    }

    public String getFile() {
        return this.file;
    }

    public String getExt() {
        return this.ext;
    }

    public Template getTemplate() {
        return new Template(this.file, this.ext);
    }
}

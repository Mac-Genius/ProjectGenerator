package io.github.mac_genius.pgen;

/**
 * All current templates matched to their respective template files.
 *
 * @author John Harrison
 */
public enum FileTemplate {
    JAVA_DEFAUlT("JavaDefault.java.tmp", ".java"),
    CPP_DEFAULT("CppDefault.cpp.tmp", ".cpp");

    private String file;
    private String ext;

    /**
     * Constructor for FileTemplate.
     *
     * @param file - the name of the template file in the jar
     * @param ext - the type of file the template will generate
     */
    FileTemplate(String file, String ext) {
        this.file = file;
        this.ext = ext;
    }

    /**
     * Returns the file name of the template.
     *
     * @return a string containing the name of the template in the jar
     */
    public String getFile() {
        return this.file;
    }

    /**
     * Returns the extension type of this template.
     *
     * @return a string with the extension type
     */
    public String getExt() {
        return this.ext;
    }

    /**
     * Returns a new Template based on this template.
     *
     * @return a new Template clasee
     */
    public Template getTemplate() {
        return new Template(this.file, this.ext);
    }
}

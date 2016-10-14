package io.github.mac_genius.pgen.modes;

import io.github.mac_genius.pgen.arguments.SampleFiles;

/**
 * Created by Mac on 10/13/16.
 */
public interface GenRun {
    void run();

    void createProjectDirectory();

    void createTemplateFile();

    void createMakeFile(SampleFiles inputFiles);
}

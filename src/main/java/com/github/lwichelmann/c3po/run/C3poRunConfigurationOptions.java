package com.github.lwichelmann.c3po.run;

import com.intellij.execution.configurations.RunConfigurationOptions;
import com.intellij.openapi.components.StoredProperty;

public class C3poRunConfigurationOptions extends RunConfigurationOptions {

    private final StoredProperty<String> executablePath =
            string("").provideDelegate(this, "executablePath");

    private final StoredProperty<String> scriptPath =
            string("").provideDelegate(this, "scriptPath");

    public String getExecutablePath() {
        return executablePath.getValue(this);
    }

    public void setExecutablePath(String path) {
        executablePath.setValue(this, path);
    }

    public String getScriptPath() {
        return scriptPath.getValue(this);
    }

    public void setScriptPath(String path) {
        scriptPath.setValue(this, path);
    }
}

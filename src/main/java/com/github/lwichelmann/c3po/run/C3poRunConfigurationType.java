package com.github.lwichelmann.c3po.run;

import com.github.lwichelmann.c3po.C3poIcons;
import com.intellij.execution.configurations.ConfigurationTypeBase;

public class C3poRunConfigurationType extends ConfigurationTypeBase {

    public static final String ID = "C3poRunConfiguration";

    public C3poRunConfigurationType() {
        super(ID, "C3PO", "Run a C3PO script with the C3PO interpreter", C3poIcons.FILE);
        addFactory(new C3poConfigurationFactory(this));
    }
}

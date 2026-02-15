package com.github.lwichelmann.c3po.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.components.BaseState;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class C3poConfigurationFactory extends ConfigurationFactory {

    public C3poConfigurationFactory(@NotNull ConfigurationType type) {
        super(type);
    }

    @NotNull
    @Override
    public String getId() {
        return C3poRunConfigurationType.ID;
    }

    @NotNull
    @Override
    public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new C3poRunConfiguration(project, this, "C3PO");
    }

    @Nullable
    @Override
    public Class<? extends BaseState> getOptionsClass() {
        return C3poRunConfigurationOptions.class;
    }
}

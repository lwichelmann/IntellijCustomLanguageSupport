package com.github.lwichelmann.c3po.run;

import com.intellij.execution.DefaultExecutionResult;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessHandlerFactory;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class C3poRunConfiguration extends RunConfigurationBase<C3poRunConfigurationOptions> {

    protected C3poRunConfiguration(@NotNull Project project,
                                   @NotNull ConfigurationFactory factory,
                                   @Nullable String name) {
        super(project, factory, name);
    }

    @NotNull
    @Override
    protected C3poRunConfigurationOptions getOptions() {
        return (C3poRunConfigurationOptions) super.getOptions();
    }

    public String getExecutablePath() {
        return getOptions().getExecutablePath();
    }

    public void setExecutablePath(String path) {
        getOptions().setExecutablePath(path);
    }

    public String getScriptPath() {
        return getOptions().getScriptPath();
    }

    public void setScriptPath(String path) {
        getOptions().setScriptPath(path);
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new C3poSettingsEditor();
    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor,
                                    @NotNull ExecutionEnvironment environment) {
        return new CommandLineState(environment) {
            @NotNull
            @Override
            protected ProcessHandler startProcess() throws com.intellij.execution.ExecutionException {
                GeneralCommandLine commandLine = new GeneralCommandLine(getExecutablePath(), getScriptPath());
                commandLine.setWorkDirectory(getProject().getBasePath());

                OSProcessHandler processHandler = ProcessHandlerFactory.getInstance()
                        .createColoredProcessHandler(commandLine);
                ProcessTerminatedListener.attach(processHandler);
                return processHandler;
            }

            @NotNull
            @Override
            public ExecutionResult execute(@NotNull Executor exec, @NotNull ProgramRunner<?> runner)
                    throws com.intellij.execution.ExecutionException {
                ProcessHandler processHandler = startProcess();
                ConsoleView console = TextConsoleBuilderFactory.getInstance()
                        .createBuilder(getProject()).getConsole();
                console.attachToProcess(processHandler);
                return new DefaultExecutionResult(console, processHandler);
            }
        };
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
        String exe = getExecutablePath();
        if (exe == null || exe.isBlank()) {
            throw new RuntimeConfigurationError("C3PO executable path is not set");
        }
        String script = getScriptPath();
        if (script == null || script.isBlank()) {
            throw new RuntimeConfigurationError("Script path is not set");
        }
    }
}

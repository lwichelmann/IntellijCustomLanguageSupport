package com.github.lwichelmann.c3po.run;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class C3poSettingsEditor extends SettingsEditor<C3poRunConfiguration> {

    private final TextFieldWithBrowseButton executablePathField;
    private final TextFieldWithBrowseButton scriptPathField;

    public C3poSettingsEditor() {
        executablePathField = new TextFieldWithBrowseButton();
        executablePathField.addBrowseFolderListener(
                "Select C3PO Executable", null, null,
                FileChooserDescriptorFactory.createSingleFileDescriptor());

        scriptPathField = new TextFieldWithBrowseButton();
        scriptPathField.addBrowseFolderListener(
                "Select C3PO Script", null, null,
                FileChooserDescriptorFactory.createSingleFileDescriptor());
    }

    @Override
    protected void resetEditorFrom(@NotNull C3poRunConfiguration configuration) {
        executablePathField.setText(configuration.getExecutablePath());
        scriptPathField.setText(configuration.getScriptPath());
    }

    @Override
    protected void applyEditorTo(@NotNull C3poRunConfiguration configuration) {
        configuration.setExecutablePath(executablePathField.getText());
        configuration.setScriptPath(scriptPathField.getText());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return FormBuilder.createFormBuilder()
                .addLabeledComponent("C3PO executable:", executablePathField)
                .addLabeledComponent("Script file:", scriptPathField)
                .getPanel();
    }
}

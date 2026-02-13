package com.github.lwichelmann.c3po;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class C3poFileType extends LanguageFileType {

    public static final C3poFileType INSTANCE = new C3poFileType();

    private C3poFileType() {
        super(C3poLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "C3PO File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "C3PO language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "c3po";
    }

    @Override
    public Icon getIcon() {
        return C3poIcons.FILE;
    }
}

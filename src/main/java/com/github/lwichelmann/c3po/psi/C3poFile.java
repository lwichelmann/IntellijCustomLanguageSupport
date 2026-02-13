package com.github.lwichelmann.c3po.psi;

import com.github.lwichelmann.c3po.C3poFileType;
import com.github.lwichelmann.c3po.C3poLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class C3poFile extends PsiFileBase {

    public C3poFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, C3poLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return C3poFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "C3PO File";
    }
}

package com.github.lwichelmann.c3po.psi;

import com.github.lwichelmann.c3po.C3poLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class C3poElementType extends IElementType {

    public C3poElementType(@NotNull @NonNls String debugName) {
        super(debugName, C3poLanguage.INSTANCE);
    }
}

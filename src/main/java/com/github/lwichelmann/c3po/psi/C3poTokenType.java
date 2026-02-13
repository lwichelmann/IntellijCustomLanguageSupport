package com.github.lwichelmann.c3po.psi;

import com.github.lwichelmann.c3po.C3poLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class C3poTokenType extends IElementType {

    public C3poTokenType(@NotNull @NonNls String debugName) {
        super(debugName, C3poLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "C3poTokenType." + super.toString();
    }
}

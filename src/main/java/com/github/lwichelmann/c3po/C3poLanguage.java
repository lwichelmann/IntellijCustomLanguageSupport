package com.github.lwichelmann.c3po;

import com.intellij.lang.Language;

public class C3poLanguage extends Language {

    public static final C3poLanguage INSTANCE = new C3poLanguage();

    private C3poLanguage() {
        super("C3PO");
    }
}

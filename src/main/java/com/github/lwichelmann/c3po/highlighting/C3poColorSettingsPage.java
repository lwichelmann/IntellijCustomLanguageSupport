package com.github.lwichelmann.c3po.highlighting;

import com.github.lwichelmann.c3po.C3poIcons;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class C3poColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = {
            new AttributesDescriptor("Keyword", C3poSyntaxHighlighter.KEYWORD),
            new AttributesDescriptor("Number", C3poSyntaxHighlighter.NUMBER),
            new AttributesDescriptor("String", C3poSyntaxHighlighter.STRING),
            new AttributesDescriptor("Identifier", C3poSyntaxHighlighter.IDENTIFIER),
            new AttributesDescriptor("Line comment", C3poSyntaxHighlighter.LINE_COMMENT),
            new AttributesDescriptor("Operator", C3poSyntaxHighlighter.OPERATION_SIGN),
            new AttributesDescriptor("Semicolon", C3poSyntaxHighlighter.SEMICOLON),
            new AttributesDescriptor("Parentheses", C3poSyntaxHighlighter.PARENTHESES),
            new AttributesDescriptor("Braces", C3poSyntaxHighlighter.BRACES),
            new AttributesDescriptor("Bad character", C3poSyntaxHighlighter.BAD_CHARACTER),
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return C3poIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new C3poSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return """
                // Variable declarations
                var greeting = "Hello, World!";
                var count = 42;
                var name = "C3PO";

                // Print statement
                print greeting;

                // For loop
                for {
                    var x = 1;
                    print x + count;
                }
                """;
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "C3PO";
    }
}

package com.github.lwichelmann.c3po.parser;

import com.github.lwichelmann.c3po.C3poLanguage;
import com.github.lwichelmann.c3po.lexer.C3poLexer;
import com.github.lwichelmann.c3po.psi.C3poFile;
import com.github.lwichelmann.c3po.psi.C3poTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class C3poParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(C3poLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new C3poLexer();
    }

    @NotNull
    @Override
    public PsiParser createParser(Project project) {
        return new C3poParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return C3poTypes.COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return C3poTypes.STRINGS;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return C3poTypes.WHITE_SPACES;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        throw new UnsupportedOperationException("Not yet implemented: " + node.getElementType());
    }

    @NotNull
    @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new C3poFile(viewProvider);
    }
}

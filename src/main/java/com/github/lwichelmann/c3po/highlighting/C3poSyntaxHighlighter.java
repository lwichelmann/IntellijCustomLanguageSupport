package com.github.lwichelmann.c3po.highlighting;

import com.github.lwichelmann.c3po.lexer.C3poLexer;
import com.github.lwichelmann.c3po.psi.C3poTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class C3poSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("C3PO_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("C3PO_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey STRING =
            createTextAttributesKey("C3PO_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey IDENTIFIER =
            createTextAttributesKey("C3PO_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey LINE_COMMENT =
            createTextAttributesKey("C3PO_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey OPERATION_SIGN =
            createTextAttributesKey("C3PO_OPERATION_SIGN", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey SEMICOLON =
            createTextAttributesKey("C3PO_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey PARENTHESES =
            createTextAttributesKey("C3PO_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey BRACES =
            createTextAttributesKey("C3PO_BRACES", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("C3PO_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] KEYWORD_KEYS = {KEYWORD};
    private static final TextAttributesKey[] NUMBER_KEYS = {NUMBER};
    private static final TextAttributesKey[] STRING_KEYS = {STRING};
    private static final TextAttributesKey[] IDENTIFIER_KEYS = {IDENTIFIER};
    private static final TextAttributesKey[] COMMENT_KEYS = {LINE_COMMENT};
    private static final TextAttributesKey[] OPERATOR_KEYS = {OPERATION_SIGN};
    private static final TextAttributesKey[] SEMICOLON_KEYS = {SEMICOLON};
    private static final TextAttributesKey[] PAREN_KEYS = {PARENTHESES};
    private static final TextAttributesKey[] BRACE_KEYS = {BRACES};
    private static final TextAttributesKey[] BAD_CHARACTER_KEYS = {BAD_CHARACTER};
    private static final TextAttributesKey[] EMPTY_KEYS = {};

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new C3poLexer();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(C3poTypes.KEYWORD_VAR)
                || tokenType.equals(C3poTypes.KEYWORD_PRINT)
                || tokenType.equals(C3poTypes.KEYWORD_FOR)) {
            return KEYWORD_KEYS;
        }
        if (tokenType.equals(C3poTypes.NUMBER)) {
            return NUMBER_KEYS;
        }
        if (tokenType.equals(C3poTypes.STRING)) {
            return STRING_KEYS;
        }
        if (tokenType.equals(C3poTypes.IDENTIFIER)) {
            return IDENTIFIER_KEYS;
        }
        if (tokenType.equals(C3poTypes.LINE_COMMENT)) {
            return COMMENT_KEYS;
        }
        if (tokenType.equals(C3poTypes.PLUS)
                || tokenType.equals(C3poTypes.MINUS)
                || tokenType.equals(C3poTypes.EQUALS)) {
            return OPERATOR_KEYS;
        }
        if (tokenType.equals(C3poTypes.SEMICOLON)) {
            return SEMICOLON_KEYS;
        }
        if (tokenType.equals(C3poTypes.LEFT_PAREN)
                || tokenType.equals(C3poTypes.RIGHT_PAREN)) {
            return PAREN_KEYS;
        }
        if (tokenType.equals(C3poTypes.LEFT_BRACE)
                || tokenType.equals(C3poTypes.RIGHT_BRACE)) {
            return BRACE_KEYS;
        }
        if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHARACTER_KEYS;
        }
        return EMPTY_KEYS;
    }
}

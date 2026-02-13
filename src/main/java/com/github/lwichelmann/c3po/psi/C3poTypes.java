package com.github.lwichelmann.c3po.psi;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public interface C3poTypes {

    // Keywords
    IElementType KEYWORD_VAR = new C3poTokenType("KEYWORD_VAR");
    IElementType KEYWORD_PRINT = new C3poTokenType("KEYWORD_PRINT");
    IElementType KEYWORD_FOR = new C3poTokenType("KEYWORD_FOR");

    // Literals
    IElementType NUMBER = new C3poTokenType("NUMBER");
    IElementType STRING = new C3poTokenType("STRING");

    // Identifiers
    IElementType IDENTIFIER = new C3poTokenType("IDENTIFIER");

    // Operators
    IElementType PLUS = new C3poTokenType("PLUS");
    IElementType MINUS = new C3poTokenType("MINUS");
    IElementType EQUALS = new C3poTokenType("EQUALS");

    // Delimiters
    IElementType SEMICOLON = new C3poTokenType("SEMICOLON");
    IElementType LEFT_PAREN = new C3poTokenType("LEFT_PAREN");
    IElementType RIGHT_PAREN = new C3poTokenType("RIGHT_PAREN");
    IElementType LEFT_BRACE = new C3poTokenType("LEFT_BRACE");
    IElementType RIGHT_BRACE = new C3poTokenType("RIGHT_BRACE");

    // Comments
    IElementType LINE_COMMENT = new C3poTokenType("LINE_COMMENT");

    // Token sets
    TokenSet KEYWORDS = TokenSet.create(KEYWORD_VAR, KEYWORD_PRINT, KEYWORD_FOR);
    TokenSet COMMENTS = TokenSet.create(LINE_COMMENT);
    TokenSet STRINGS = TokenSet.create(STRING);
    TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    TokenSet BAD_CHARACTERS = TokenSet.create(TokenType.BAD_CHARACTER);
}

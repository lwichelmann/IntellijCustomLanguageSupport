package com.github.lwichelmann.c3po.lexer;

import com.github.lwichelmann.c3po.psi.C3poTypes;
import com.intellij.lexer.LexerBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class C3poLexer extends LexerBase {

    private static final Map<String, IElementType> KEYWORDS = Map.of(
            "var", C3poTypes.KEYWORD_VAR,
            "print", C3poTypes.KEYWORD_PRINT,
            "for", C3poTypes.KEYWORD_FOR
    );

    private CharSequence buffer;
    private int bufferEnd;
    private int tokenStart;
    private int tokenEnd;
    private IElementType tokenType;

    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {
        this.buffer = buffer;
        this.bufferEnd = endOffset;
        this.tokenStart = startOffset;
        this.tokenEnd = startOffset;
        this.tokenType = null;
        advance();
    }

    @Override
    public int getState() {
        return 0;
    }

    @Nullable
    @Override
    public IElementType getTokenType() {
        return tokenType;
    }

    @Override
    public int getTokenStart() {
        return tokenStart;
    }

    @Override
    public int getTokenEnd() {
        return tokenEnd;
    }

    @Override
    public void advance() {
        tokenStart = tokenEnd;

        if (tokenStart >= bufferEnd) {
            tokenType = null;
            return;
        }

        char c = buffer.charAt(tokenStart);

        if (Character.isWhitespace(c)) {
            scanWhitespace();
        } else if (c == '/' && tokenStart + 1 < bufferEnd && buffer.charAt(tokenStart + 1) == '/') {
            scanLineComment();
        } else if (c == '"') {
            scanString();
        } else if (Character.isDigit(c)) {
            scanNumber();
        } else if (Character.isLetter(c) || c == '_') {
            scanIdentifierOrKeyword();
        } else {
            scanSymbol(c);
        }
    }

    @NotNull
    @Override
    public CharSequence getBufferSequence() {
        return buffer;
    }

    @Override
    public int getBufferEnd() {
        return bufferEnd;
    }

    private void scanWhitespace() {
        tokenEnd = tokenStart + 1;
        while (tokenEnd < bufferEnd && Character.isWhitespace(buffer.charAt(tokenEnd))) {
            tokenEnd++;
        }
        tokenType = TokenType.WHITE_SPACE;
    }

    private void scanLineComment() {
        tokenEnd = tokenStart + 2;
        while (tokenEnd < bufferEnd && buffer.charAt(tokenEnd) != '\n') {
            tokenEnd++;
        }
        tokenType = C3poTypes.LINE_COMMENT;
    }

    private void scanString() {
        tokenEnd = tokenStart + 1;
        while (tokenEnd < bufferEnd) {
            char c = buffer.charAt(tokenEnd);
            tokenEnd++;
            if (c == '"') {
                break;
            }
            if (c == '\\' && tokenEnd < bufferEnd) {
                tokenEnd++; // skip escaped character
            }
        }
        tokenType = C3poTypes.STRING;
    }

    private void scanNumber() {
        tokenEnd = tokenStart + 1;
        while (tokenEnd < bufferEnd && Character.isDigit(buffer.charAt(tokenEnd))) {
            tokenEnd++;
        }
        tokenType = C3poTypes.NUMBER;
    }

    private void scanIdentifierOrKeyword() {
        tokenEnd = tokenStart + 1;
        while (tokenEnd < bufferEnd) {
            char c = buffer.charAt(tokenEnd);
            if (Character.isLetterOrDigit(c) || c == '_') {
                tokenEnd++;
            } else {
                break;
            }
        }
        String word = buffer.subSequence(tokenStart, tokenEnd).toString();
        tokenType = KEYWORDS.getOrDefault(word, C3poTypes.IDENTIFIER);
    }

    private void scanSymbol(char c) {
        tokenEnd = tokenStart + 1;
        tokenType = switch (c) {
            case '+' -> C3poTypes.PLUS;
            case '-' -> C3poTypes.MINUS;
            case '=' -> C3poTypes.EQUALS;
            case ';' -> C3poTypes.SEMICOLON;
            case '(' -> C3poTypes.LEFT_PAREN;
            case ')' -> C3poTypes.RIGHT_PAREN;
            case '{' -> C3poTypes.LEFT_BRACE;
            case '}' -> C3poTypes.RIGHT_BRACE;
            default -> TokenType.BAD_CHARACTER;
        };
    }
}

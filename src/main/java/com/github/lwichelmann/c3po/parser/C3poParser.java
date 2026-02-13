package com.github.lwichelmann.c3po.parser;

import com.github.lwichelmann.c3po.psi.C3poTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class C3poParser implements PsiParser {

    public static final IElementType VARIABLE_DECLARATION = new IElementType("VARIABLE_DECLARATION", null);
    public static final IElementType FOR_STATEMENT = new IElementType("FOR_STATEMENT", null);
    public static final IElementType BLOCK = new IElementType("BLOCK", null);
    public static final IElementType PRINT_STATEMENT = new IElementType("PRINT_STATEMENT", null);
    public static final IElementType EXPRESSION = new IElementType("EXPRESSION", null);

    @NotNull
    @Override
    public ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder) {
        PsiBuilder.Marker rootMarker = builder.mark();
        while (!builder.eof()) {
            parseStatement(builder);
        }
        rootMarker.done(root);
        return builder.getTreeBuilt();
    }

    private void parseStatement(@NotNull PsiBuilder builder) {
        IElementType tokenType = builder.getTokenType();

        if (tokenType == C3poTypes.KEYWORD_VAR) {
            parseVariableDeclaration(builder);
        } else if (tokenType == C3poTypes.KEYWORD_FOR) {
            parseForStatement(builder);
        } else if (tokenType == C3poTypes.KEYWORD_PRINT) {
            parsePrintStatement(builder);
        } else if (tokenType == C3poTypes.LEFT_BRACE) {
            parseBlock(builder);
        } else {
            // Consume any other token to avoid infinite loops
            builder.advanceLexer();
        }
    }

    private void parseVariableDeclaration(@NotNull PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer(); // consume 'var'

        // Expect identifier
        if (builder.getTokenType() == C3poTypes.IDENTIFIER) {
            builder.advanceLexer();
        } else {
            marker.error("Expected identifier");
            return;
        }

        // Expect '='
        if (builder.getTokenType() == C3poTypes.EQUALS) {
            builder.advanceLexer();
        } else {
            marker.error("Expected '='");
            return;
        }

        // Parse expression (simple: single literal or identifier)
        parseExpression(builder);

        // Expect ';'
        if (builder.getTokenType() == C3poTypes.SEMICOLON) {
            builder.advanceLexer();
        }

        marker.done(VARIABLE_DECLARATION);
    }

    private void parseForStatement(@NotNull PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer(); // consume 'for'

        // Expect block
        if (builder.getTokenType() == C3poTypes.LEFT_BRACE) {
            parseBlock(builder);
        }

        marker.done(FOR_STATEMENT);
    }

    private void parsePrintStatement(@NotNull PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer(); // consume 'print'

        // Parse expression
        parseExpression(builder);

        // Expect ';'
        if (builder.getTokenType() == C3poTypes.SEMICOLON) {
            builder.advanceLexer();
        }

        marker.done(PRINT_STATEMENT);
    }

    private void parseBlock(@NotNull PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer(); // consume '{'

        while (!builder.eof() && builder.getTokenType() != C3poTypes.RIGHT_BRACE) {
            parseStatement(builder);
        }

        // Consume '}'
        if (builder.getTokenType() == C3poTypes.RIGHT_BRACE) {
            builder.advanceLexer();
        }

        marker.done(BLOCK);
    }

    private void parseExpression(@NotNull PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();

        if (builder.getTokenType() == C3poTypes.NUMBER
                || builder.getTokenType() == C3poTypes.STRING
                || builder.getTokenType() == C3poTypes.IDENTIFIER) {
            builder.advanceLexer();

            // Handle binary expressions: expr OP expr
            while (builder.getTokenType() == C3poTypes.PLUS
                    || builder.getTokenType() == C3poTypes.MINUS) {
                builder.advanceLexer();
                if (builder.getTokenType() == C3poTypes.NUMBER
                        || builder.getTokenType() == C3poTypes.STRING
                        || builder.getTokenType() == C3poTypes.IDENTIFIER) {
                    builder.advanceLexer();
                }
            }
        }

        marker.done(EXPRESSION);
    }
}

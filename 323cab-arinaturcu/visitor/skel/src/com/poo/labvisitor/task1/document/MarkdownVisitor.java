package com.poo.labvisitor.task1.document;

public class MarkdownVisitor implements DocumentVisitor {
    StringBuilder document;

    @Override
    public void visit(ItalicTextSegment italicSegment) {
        document = new StringBuilder("_");
        document.append(italicSegment.getContent());
        document.append("_");
    }

    @Override
    public void visit(BoldTextSegment boldSegment) {
        document = new StringBuilder("__");
        document.append(boldSegment.getContent());
        document.append("__");
    }

    @Override
    public void visit(UrlSegment urlSegment) {
        document = new StringBuilder("[");
        document.append(urlSegment.getDescription());
        document.append("](");
        document.append(urlSegment.getUrl());
        document.append(")");
    }

    @Override
    public void visit(PlainTextSegment plainSegment) {
        document = new StringBuilder(plainSegment.getContent());
    }

    @Override
    public StringBuilder getDocument() {
        return document;
    }
}

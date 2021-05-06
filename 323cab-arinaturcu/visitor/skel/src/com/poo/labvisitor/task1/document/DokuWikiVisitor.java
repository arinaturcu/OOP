package com.poo.labvisitor.task1.document;

public class DokuWikiVisitor implements DocumentVisitor {
    StringBuilder document;

    @Override
    public void visit(ItalicTextSegment italicSegment) {
        document = new StringBuilder("//");
        document.append(italicSegment.getContent());
        document.append("//");
    }

    @Override
    public void visit(BoldTextSegment boldSegment) {
        document = new StringBuilder("**");
        document.append(boldSegment.getContent());
        document.append("**");
    }

    @Override
    public void visit(UrlSegment urlSegment) {
        document = new StringBuilder("[[");
        document.append(urlSegment.getUrl());
        document.append("|");
        document.append(urlSegment.getDescription());
        document.append("]]");
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

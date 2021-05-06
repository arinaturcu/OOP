package com.poo.labvisitor.task1.document;

public interface DocumentVisitor {

    public void visit(ItalicTextSegment italicSegment);
    public void visit(BoldTextSegment boldSegment);
    public void visit(UrlSegment urlSegment);
    public void visit(PlainTextSegment plainSegment);
    public StringBuilder getDocument();
}

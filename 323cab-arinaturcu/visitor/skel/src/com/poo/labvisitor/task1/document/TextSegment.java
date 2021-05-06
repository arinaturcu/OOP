package com.poo.labvisitor.task1.document;

/**
 * Represents a text segment of a document that needs to be parsed.
 */
public abstract class TextSegment {
    private String content;

    TextSegment(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    // TODO add method for applying the visitor
    public void accept (DocumentVisitor dVisitor) {
        if (this instanceof BoldTextSegment) {
            dVisitor.visit((BoldTextSegment) this);
        }
        if (this instanceof ItalicTextSegment) {
            dVisitor.visit((ItalicTextSegment) this);
        }
        if (this instanceof UrlSegment) {
            dVisitor.visit((UrlSegment) this);
        }
        if (this instanceof PlainTextSegment) {
            dVisitor.visit((PlainTextSegment)this);
        }
    }
}

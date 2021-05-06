package com.poo.labvisitor.task1.document;

public class UrlSegment extends TextSegment {
    private String url;
    private String description;

    public UrlSegment(String url, String description) {
        super(url);
        this.url = url;
        this.description = description;
    }

//    public void accept(DocumentVisitor dVisitor) {
//        dVisitor.visit(this);
//    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}

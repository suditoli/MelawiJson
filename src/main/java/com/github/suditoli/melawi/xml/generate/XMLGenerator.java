/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.suditoli.melawi.xml.generate;

/**
 *
 * @author chin
 */
public class XMLGenerator {

    StringBuilder sb = new StringBuilder();

    public XMLGenerator() {
        this.sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    }

    public XMLGenerator startElement(String k) {
        this.sb.append("<").append(k.trim()).append(">");
        return this;
    }

    public XMLGenerator addField(String k, String v) {
        this.sb.append("<").append(k.trim()).append(">")
                .append(v.trim())
                .append("</").append(k.trim()).append(">");
        return this;
    }

    public XMLGenerator endElement(String k) {
        this.sb.append("</").append(k.trim()).append(">");
        return this;
    }
    
    @Override
    public String toString() {
        return this.sb.toString();
    }
}

package com.mc2023.template;

public class MyModel {
    String textContent = "";
    String meaning="";

    public MyModel(String textContent, String meaning_content) {
        this.meaning = meaning_content;
        this.textContent = textContent;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}

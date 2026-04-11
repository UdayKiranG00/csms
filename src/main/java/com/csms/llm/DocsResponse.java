package com.csms.llm;

import java.util.List;

public class DocsResponse {

    private List<String> docs;
    public DocsResponse(){

    }

    public List<String> getDocs() {
        return docs;
    }

    public void setDocs(List<String> docs) {
        this.docs = docs;
    }
}

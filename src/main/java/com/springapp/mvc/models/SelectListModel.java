package com.springapp.mvc.models;


import org.apache.chemistry.opencmis.client.api.Document;

import java.util.ArrayList;

public class SelectListModel {
    private ArrayList<String> selectionList = new ArrayList<String>();
    private String selected;
    private String newName;
    private String newDescription;
    private String selectedPath;
    private Document currentDoc;

    public void setSelectedPath(String selectedPath) {
        this.selectedPath = selectedPath;
    }

    public String getSelectedPath() {
        return selectedPath;
    }

    public void setCurrentDoc(Document currentDoc) {
        this.currentDoc = currentDoc;
    }

    public Document getCurrentDoc() {
        return currentDoc;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public String getNewName() {
        return newName;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelectionList(ArrayList<String> selectionList) {
        this.selectionList = selectionList;
    }

    public ArrayList<String> getSelectionList() {
        return selectionList;
    }
}

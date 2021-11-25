package com.airjob.airjobs.ui.manageProfil;

import com.google.firebase.firestore.Exclude;

public class ModelProfil {

    private String titre;
    private String note;
    private String documentId;

    public ModelProfil() {
    }

    public ModelProfil(String titre, String note) {
        this.titre = titre;
        this.note = note;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
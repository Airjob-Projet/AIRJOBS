package com.airjob.airjobs;

public class Model {
    int image;
    String nom;
    String prenom;
    String job;
    String activite1;
    String activite2;
    String activite3;
    String activite4;
    String activite5;
    String qualite1;
    String qualite2;
    String qualite3;
    String qualite4;
    String qualite5;

    public Model() {
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Model(int image, String nom, String prenom, String job, String activite1, String activite2,
                 String activite3, String activite4, String activite5, String qualite1, String qualite2,
                 String qualite3, String qualite4, String qualite5) {
        this.image = image;
        this.nom = nom;
        this.prenom = prenom;
        this.job = job;
        this.activite1 = activite1;
        this.activite2 = activite2;
        this.activite3 = activite3;
        this.activite4 = activite4;
        this.activite5 = activite5;
        this.qualite1 = qualite1;
        this.qualite2 = qualite2;
        this.qualite3 = qualite3;
        this.qualite4 = qualite4;
        this.qualite5 = qualite5;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getActivite1() {
        return activite1;
    }

    public void setActivite1(String activite1) {
        this.activite1 = activite1;
    }

    public String getActivite2() {
        return activite2;
    }

    public void setActivite2(String activite2) {
        this.activite2 = activite2;
    }

    public String getActivite3() {
        return activite3;
    }

    public void setActivite3(String activite3) {
        this.activite3 = activite3;
    }

    public String getActivite4() {
        return activite4;
    }

    public void setActivite4(String activite4) {
        this.activite4 = activite4;
    }

    public String getActivite5() {
        return activite5;
    }

    public void setActivite5(String activite5) {
        this.activite5 = activite5;
    }

    public String getQualite1() {
        return qualite1;
    }

    public void setQualite1(String qualite1) {
        this.qualite1 = qualite1;
    }

    public String getQualite2() {
        return qualite2;
    }

    public void setQualite2(String qualite2) {
        this.qualite2 = qualite2;
    }

    public String getQualite3() {
        return qualite3;
    }

    public void setQualite3(String qualite3) {
        this.qualite3 = qualite3;
    }

    public String getQualite4() {
        return qualite4;
    }

    public void setQualite4(String qualite4) {
        this.qualite4 = qualite4;
    }

    public String getQualite5() {
        return qualite5;
    }

    public void setQualite5(String qualite5) {
        this.qualite5 = qualite5;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
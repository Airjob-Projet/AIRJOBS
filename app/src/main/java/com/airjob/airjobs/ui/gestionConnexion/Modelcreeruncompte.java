package com.airjob.airjobs.ui.gestionConnexion;

public class Modelcreeruncompte {

    private String identifiant;
    private String email;
    private String motdepasse;

    public Modelcreeruncompte(String identifiant, String email, String motdepasse) {
        this.identifiant = identifiant;
        this.email = email;
        this.motdepasse = motdepasse;
    }

    public Modelcreeruncompte() {
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
}

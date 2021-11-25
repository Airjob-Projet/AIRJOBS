package com.airjob.airjobs.ui.manageProfil;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airjob.airjobs.R;

public class Creeunprofil extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView tvType;
    private EditText etNom, etPrenom;
    private Spinner sActivitySector;
    private Spinner sProfilType;
    private Spinner sMyJob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout_bdd);
        init();
        SpinnerChoix();
    }

    String[] sTypeProfil = {
            "Vous êtes?",
            "Recruteur",
            "Candidat",
            "Start-upper",
    };

    String[] sSectorActivity = {
            "Votre Secteur d'activité",
            "Agriculture, Forêt, Pêche",
            "Agroalimentaire",
            "Aménagement paysager & espaces verts",
            "Assurance, Mutualité",
            "Audiovisuel, Media",
            "Automobile",
            "Autres services aux entreprises",
            "Banque, Finance",
            "BTP - Bâtiment Travaux Publics",
            "Cabinets de conseils",
            "Commerce de gros et Import/export",
            "Communication, Publicité, Edition",
            "Culture, Arts, Spectacles",
            "Défense, Sécurité",
            "Distribution & Commerce de détail",
            "Energie",
            "Enseignement, Formation",
            "Environnement, Eau",
            "ESN, Editeurs de logiciel, Services informatiques",
            "Fonction publique",
            "Hôtellerie, Restauration",
            "Immobilier, Infrastructures",
            "Industrie / Production autres",
            "Industrie Electronique, Biens d'équipements",
            "Industries Aéro, Naval et Défense",
            "Industries Chimique, Plastique",
            "Industries Cosmétique, Pharma et Biotech",
            "Ingénierie & services associés",
            "Internet, e-commerce",
            "Logistique, Transport de biens, Courrier",
            "Recrutement et placement",
            "Santé",
            "Secteur associatif",
            "Services à domicile",
            "Sociétés d'audit et comptables",
            "Sports",
            "Télécommunications",
            "Tourisme, Transport de passagers, loisirs",
    };

    String[] sJob= {
            "Votre métiers",
            "Adjoint administratif de la police nationale",
            "Agent administratif et gestion",
            "Agent administratif public",
            "Agent d'accueil / réceptionniste",
            "Agent d'accueil en agence bancaire",
            "Agent d'entretien et de nettoyage urbain",
            "Agent d'entretien urbain",
            "Agent d'usinage",
            "Agent de fabrication polyvalent",
            "Agent de logistique",
            "Agent de nettoyage industriel",
            "Agent de nettoyage",
            "Agent de production",
            "Agent de quai",
            "Agent de restauration",
            "Agent de tri",
            "Agent immobilier",
            "Aide ménagère",
            "Aide à domicile",
            "Analyste Xamarin",
            "Animateur QSE",
            "Assistant administratif",
            "Assistant commercial",
            "Assistant de direction",
            "Assistant de gestion",
            "Assistant en ressources humaines",
            "Assistant juridique",
            "Assistant logistique",
            "Assistant marketing",
            "Assistant qualité",
            "Assistant RH",
            "Assistant webmarketing",
            "Assistant",
            "Auxiliaire de vie",
            "Baby sitter",
            "Barman",
            "Caissier",
            "Cariste",
            "Chargé de clientèle particuliers banque",
            "Chargé de clientèle",
            "Chargé de développement Xamarin",
            "Chargé de recrutement",
            "Chargé de webmarketing",
            "Chauffeur / Livreur",
            "Chauffeur PL",
            "Chauffeur routier",
            "Chef de projet web",
            "Chef de projet",
            "Chef de réception",
            "Chef de zone export",
            "Claviste au kilomètre",
            "Commercial B to B",
            "Commercial B to C",
            "Commercial sédentaire",
            "Commercial",
            "Comptable unique",
            "Concepteur développeur Xamarin",
            "Conducteur de ligne",
            "Conducteur de travaux",
            "Conseiller de vente",
            "Conseiller téléphonique",
            "Consultant Xamarin",
            "Consultant Zend",
            "Contrôleur de gestion",
            "Contrôleur qualité",
            "Couvreur zingueur",
            "Directeur de magasin",
            "Distributeur de journaux et prospectus",
            "Développeur informatique",
            "Développeur JAVA",
            "Développeur web",
            "Développeur Xamarin",
            "Educateur de jeunes enfants",
            "Employé de libre-service",
            "Employé de magasin",
            "Employé de nettoyage / aide ménagère",
            "Employé de rayon",
            "Employé de restauration",
            "Employé libre service",
            "Employé polyvalent",
            "Femme de ménage chez un particulier",
            "Garde d'enfants hors du domicile",
            "Garde d'enfants à domicile",
            "Garde d'enfants",
            "Gestionnaire de paie",
            "Graphiste / webdesigner",
            "Graphiste",
            "Guichetier",
            "Gérant de magasin",
            "Hote/hôtesse de vente",
            "Hôte / hôtesse d'accueil dans une office de tourisme",
            "Hôte / hôtesse d'accueil standardiste",
            "Infirmier",
            "Infographiste",
            "Ingénieur commercial",
            "Ingénieur d'études et développement Xamarin",
            "Ingénieur d'études et développement Zend",
            "Ingénieur d'études Xamarin",
            "Ingénieur d'études",
            "Ingénieur de développement Xamarin",
            "Ingénieur qualité",
            "Ingénieur Xamarin",
            "Ingénieur étude Zend",
            "Intégrateur Web",
            "Inventoriste",
            "Jardinier / paysagiste",
            "Jardinier d'espaces verts",
            "Jardinier",
            "Journaliste",
            "Juriste droit des affaires",
            "Juriste",
            "Key account manager marketplace",
            "Key account manager",
            "Kinésithérapeute",
            "Kiwiculteur",
            "Képissier de type artisanal",
            "Livreur",
            "Magasinier",
            "Manager",
            "Manutentionnaire",
            "Masseur kinésithérapeute rééducateur",
            "Masseur kinésithérapeute",
            "Moniteur cadre de masso-kinésithérapie",
            "Moniteur de canoë-kayak",
            "Nourrice",
            "Négociateur immobilier",
            "Négociateur",
            "Office Manager",
            "Opérateur centre d'usinage",
            "Opérateur commande numérique",
            "Opérateur de montage / assemblage",
            "Opérateur de production",
            "Opérateur de saisie",
            "Opérateur",
            "Ouvrier agricole",
            "Ouvrier agroalimentaire",
            "Ouvrier du bâtiment et des travaux publics",
            "Ouvrier paysagiste",
            "Peintre en bâtiment",
            "Plongeur",
            "Professeur de zumba",
            "Programmeur Xamarin",
            "Préparateur de commandes",
            "Responsable de la qualité",
            "Responsable de magasin",
            "Responsable de zone",
            "Responsable logistique",
            "Responsable qualité",
            "Rédacteur web",
            "Secrétaire administratif d'administration centrale",
            "Secrétaire administratif des affaires sanitaires et sociales",
            "Secrétaire administratif",
            "Secrétaire",
            "Serveur",
            "Soigneur en parc zoologique",
            "Spécialiste qualité / contrôle qualité",
            "Standardiste",
            "Technicien d'exploitation informatique",
            "Technicien de laboratoire",
            "Technicien de maintenance informatique",
            "Technicien de maintenance",
            "Technicien en électricité / électronique",
            "Technicien qualité contrôle et mesure",
            "Technicien qualité",
            "Technicien usinage",
            "Technicien",
            "Technico commercial",
            "Téléconseiller",
            "Télévendeur",
            "Usineur",
            "UX designer",
            "Veilleur de nuit",
            "Vendeur de vêtements",
            "Vendeur en alimentation",
            "Vendeur en articles de luxe",
            "Vendeur en biens d'équipement",
            "Vendeur en magasin",
            "Vendeur en produits de beauté",
            "Vendeur en prêt-à-porter",
            "Vendeur non alimentaire",
            "Vendeur",
            "Web designer",
            "Webdesigner",
            "Webmaster",
            "Yield manager",
            "Zingueur",
            "Zootechnicien",
    };

    public void init(){
        tvType = findViewById(R.id.tvType);
        etNom = findViewById(R.id.etNom);
        etPrenom = findViewById(R.id.etPrenom);
    }
    private void SpinnerChoix(){
        sActivitySector = findViewById(R.id.sTypeProfil);
        ArrayAdapter<String> list1 = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, sTypeProfil
        );
        sActivitySector.setAdapter(list1);
        sActivitySector.setOnItemSelectedListener(this);
        sProfilType = findViewById(R.id.sSectorActivity);
        ArrayAdapter<String> list2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, sSectorActivity
        );
        sProfilType.setAdapter(list2);
        sProfilType.setOnItemSelectedListener(this);
        sMyJob = findViewById(R.id.sJob);
        ArrayAdapter<String> list3 = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, sJob
        );
        sMyJob.setAdapter(list3);
        sMyJob.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int variable = 0;
        switch (adapterView.getId()){
            case R.id.sTypeProfil:
                if(i == 0){
                    sProfilType.setVisibility(View.INVISIBLE);
                    sMyJob.setVisibility(View.INVISIBLE);
                    etNom.setVisibility(View.INVISIBLE);
                    etPrenom.setVisibility(View.INVISIBLE);
                    variable = 0;
                }
                else
                    sProfilType.setVisibility(View.VISIBLE);
                if(i == 1){
                    tvType.setText("Recruteur");
                }
                else if(i == 2){
                    variable = 1;
                    tvType.setText("Candidat");
                }
                else if(i == 3){
                    variable = 1;
                    tvType.setText("Start-Upper");
                }
                break;
            case R.id.sSectorActivity:
                if(i == 0){
                    sMyJob.setVisibility(View.INVISIBLE);
                }
                else if (variable == 0)
                    sMyJob.setVisibility(View.VISIBLE);
                break;
            case R.id.sJob:
                if(i == 0){
                    etNom.setVisibility(View.INVISIBLE);
                    etPrenom.setVisibility(View.INVISIBLE);
                }
                else{
                    etNom.setVisibility(View.VISIBLE);
                    etPrenom.setVisibility(View.VISIBLE);
                }
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        final Toast myToast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);
        myToast.cancel();
    }

}
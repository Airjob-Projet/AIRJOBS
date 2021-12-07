package com.airjob.airjobs.ui.manageProfil;
<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
=======

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
>>>>>>> 638de1e42d93a5e9353a9e2f75136f242b42484b
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

<<<<<<< HEAD
import com.airjob.airjobs.MainActivity;
import com.airjob.airjobs.R;
import com.airjob.airjobs.databinding.FragmentProfilBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProfilFragment extends Fragment implements android.widget.AdapterView.OnItemSelectedListener {


    private DocumentReference noteRef;
    private String variableGlobalJob;
    private String variableGlobalSecteur;
    private String variableGlobalChamps;
    private String variableGlobalExp;



    private FragmentProfilBinding binding;
    private TextView tvQuestionHobbies, tvQuestionPerso, tvDescription;
    private EditText etNom, etPrenom, etEntreprise, etPoste, etDescription, etHobbies1, etHobbies2, etHobbies3, etHobbies4, etHobbies5, etPerso1, etPerso2, etPerso3, etPerso4, etPerso5;
    private Spinner sSectorActivity, sProfilType, sJob, sExp;
    private Button AddBdd;

    // Variables Firebase
    private FirebaseFirestore db;
    private CollectionReference profilsRef;
    private CollectionReference secteurRef;
    private CollectionReference metierRef;

    // Variables des listes
    List<String> profilList = new ArrayList<>();
    List<String> secteurList = new ArrayList<>();
    List<String> metierList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        binding = FragmentProfilBinding.inflate(inflater, container, false);
        View v = binding.getRoot();


        db.collection("Type de profils")
                .whereEqualTo("Employeur", true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("tag", document.getId() + " => " + document.getData());
                                System.out.println(document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d("tag", "Error getting documents: ", task.getException());
                        }
                    }
                });

        Button layout = binding.AddBdd;
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String variableGlobalNom = etNom.getText().toString();
                String variableGlobalPrenom = etPrenom.getText().toString();
                String hobbie1 = etHobbies1.getText().toString();
                String hobbie2 = etHobbies2.getText().toString();
                String hobbie3 = etHobbies3.getText().toString();
                String hobbie4 = etHobbies4.getText().toString();
                String hobbie5 = etHobbies5.getText().toString();
                String traitdp1 = etPerso1.getText().toString();
                String traitdp2 = etPerso2.getText().toString();
                String traitdp3 = etPerso3.getText().toString();
                String traitdp4 = etPerso4.getText().toString();
                String traitdp5 = etPerso5.getText().toString();
                String description = etDescription.getText().toString();
                ModelProfilCandidat contenuNote = new ModelProfilCandidat(variableGlobalChamps, variableGlobalSecteur, variableGlobalJob,description,
                        user.getEmail(),
                        variableGlobalNom, variableGlobalPrenom, "https://www.google.fr/url?sa=i&url=https%3A%2F%2Fhardforce.com%2Fartist%2F1229%2Ftoto&psig=AOvVaw24is2mcdIAC5xzCLv28Z9F&ust=1638960278386000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCMi50fzA0fQCFQAAAAAdAAAAABAD", "url/pdf",
                        hobbie1, hobbie2, hobbie3, hobbie4, hobbie5, traitdp1, traitdp2, traitdp3, traitdp4, traitdp5, variableGlobalExp);
                if (variableGlobalChamps.contains("Employeur")) {
                    noteRef = db.document("Recruteur/" + uid);
                } else {
                    noteRef = db.document("Candidat/" + uid);
                }
                noteRef.set(contenuNote);


            }
        });
        init(v);
        spinnerChoix(v);
        spinnerProfil(v);
        return v;

    }

=======
import com.airjob.airjobs.R;
import com.airjob.airjobs.databinding.FragmentProfilBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class ProfilFragment extends Fragment implements android.widget.AdapterView.OnItemSelectedListener{

    private ProfilViewModel profilViewModel;
    private FragmentProfilBinding binding;

    private FirebaseFirestore db;
    private DocumentReference noteRef;
    private TextView tvType;
    private EditText etNom, etPrenom;
    private Spinner sActivitySector;
    private Spinner sProfilType;
    private Spinner sMyJob;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        db= FirebaseFirestore.getInstance();
        noteRef= db.document("Profil/User");

        profilViewModel =
                new ViewModelProvider(this).get(ProfilViewModel.class);

        binding = FragmentProfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//        final TextView textView = binding.tvProfil;
//        profilViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//
//            }
//
//        });
//          startActivity(new Intent(getActivity(), Creeunprofil.class));
        Button layout = binding.AddBdd;
        layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ModelProfil contenuNote = new ModelProfil("test1", "test2");




                noteRef.set(contenuNote)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                System.out.println("success");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println("fail");
                            }
                        });




                System.out.println("test");
            }
        });
        init(root);
        SpinnerChoix(root);
        return root;
    }


>>>>>>> 638de1e42d93a5e9353a9e2f75136f242b42484b
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
<<<<<<< HEAD

    String[] sMyExp = {
            "Niveau d'expérience",
            "0-2 ans : Junior",
            "2-5 ans : Expérimenté",
            "5 ans+ : Senior"
    };

    public void init(View root) {
        sProfilType = root.findViewById(R.id.sTypeProfil);
        sSectorActivity = root.findViewById(R.id.sSectorActivity);
        sJob = root.findViewById(R.id.sJob);
        etEntreprise = root.findViewById(R.id.etEntreprise);
        etDescription = root.findViewById(R.id.etDescription);
        tvDescription = root.findViewById(R.id.tvDescription);
        etPoste = root.findViewById(R.id.etPoste);
        etNom = root.findViewById(R.id.etNom);
        etPrenom = root.findViewById(R.id.etPrenom);
        AddBdd = root.findViewById(R.id.AddBdd);
        tvQuestionHobbies = root.findViewById(R.id.tvQuestionHobbies);
        tvQuestionPerso = root.findViewById(R.id.tvQuestionPerso);
        etHobbies1 = root.findViewById(R.id.etHobbies1);
        etHobbies2 = root.findViewById(R.id.etHobbies2);
        etHobbies3 = root.findViewById(R.id.etHobbies3);
        etHobbies4 = root.findViewById(R.id.etHobbies4);
        etHobbies5 = root.findViewById(R.id.etHobbies5);
        etPerso1 = root.findViewById(R.id.etPerso1);
        etPerso2 = root.findViewById(R.id.etPerso2);
        etPerso3 = root.findViewById(R.id.etPerso3);
        etPerso4 = root.findViewById(R.id.etPerso4);
        etPerso5 = root.findViewById(R.id.etPerso5);

    }

    private void spinnerChoix(View root) {
        sExp = root.findViewById(R.id.sExp);
        ArrayAdapter<String> list1 = new ArrayAdapter<String>(
                root.getContext(), android.R.layout.simple_list_item_1, sMyExp
        );
        sExp.setAdapter(list1);
        sExp.setOnItemSelectedListener(this);
=======
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

    public void init(View root){
        tvType = root.findViewById(R.id.tvType);
        etNom = root.findViewById(R.id.etNom);
        etPrenom = root.findViewById(R.id.etPrenom);
    }
    private void SpinnerChoix(View root){
        sActivitySector = root.findViewById(R.id.sTypeProfil);
        ArrayAdapter<String> list1 = new ArrayAdapter<String>(
                root.getContext(), android.R.layout.simple_list_item_1, sTypeProfil
        );
        sActivitySector.setAdapter(list1);
        sActivitySector.setOnItemSelectedListener(this);
        sProfilType = root.findViewById(R.id.sSectorActivity);
        ArrayAdapter<String> list2 = new ArrayAdapter<String>(
                root.getContext(), android.R.layout.simple_list_item_1, sSectorActivity
        );
        sProfilType.setAdapter(list2);
        sProfilType.setOnItemSelectedListener(this);
        sMyJob = root.findViewById(R.id.sJob);
        ArrayAdapter<String> list3 = new ArrayAdapter<String>(
                root.getContext(), android.R.layout.simple_list_item_1, sJob
        );
        sMyJob.setAdapter(list3);
        sMyJob.setOnItemSelectedListener(this);
>>>>>>> 638de1e42d93a5e9353a9e2f75136f242b42484b
    }

    @Override
    public void onItemSelected(android.widget.AdapterView<?> adapterView, View view, int i, long l) {
<<<<<<< HEAD
        switch (adapterView.getId()) {
            case R.id.sTypeProfil:
                if (i == 0) {
                    invisibility(4);
                } else {
                    variableGlobalChamps = profilList.get(i);
                    spinnerSecteur(view, profilList.get(i));
                    if (variableGlobalChamps.equals("Employeur")) {
                        etEntreprise.setVisibility(View.VISIBLE);
                        etPoste.setVisibility(View.VISIBLE);
                    } else {
                        etEntreprise.setVisibility(View.GONE);
                        etPoste.setVisibility(View.GONE);
                    }
                    etNom.setVisibility(View.VISIBLE);
                    etPrenom.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.sSectorActivity:
                if (i == 0) {
                    sSectorActivity.setVisibility(View.VISIBLE);
                    invisibility(3);

                } else {
                    variableGlobalSecteur = secteurList.get(i);
                    spinnerJob(view, variableGlobalChamps, variableGlobalSecteur);
                    sJob.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.sJob:
                if (i == 0) {
                    invisibility(2);
                } else {
                    variableGlobalJob = metierList.get(i);
                    sExp.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.sExp:
                if (i == 0) {
                    invisibility(1);
                } else {
                    variableGlobalExp = sMyExp[i];
                    tvDescription.setVisibility(View.VISIBLE);
                    etDescription.setVisibility(View.VISIBLE);
                    AddBdd.setVisibility(View.VISIBLE);
                    tvQuestionHobbies.setVisibility(View.VISIBLE);
                    etHobbies1.setVisibility(View.VISIBLE);
                    etHobbies2.setVisibility(View.VISIBLE);
                    etHobbies3.setVisibility(View.VISIBLE);
                    etHobbies4.setVisibility(View.VISIBLE);
                    etHobbies5.setVisibility(View.VISIBLE);
                    tvQuestionPerso.setVisibility(View.VISIBLE);
                    etPerso1.setVisibility(View.VISIBLE);
                    etPerso2.setVisibility(View.VISIBLE);
                    etPerso3.setVisibility(View.VISIBLE);
                    etPerso4.setVisibility(View.VISIBLE);
                    etPerso5.setVisibility(View.VISIBLE);
                    if (variableGlobalChamps.equals("Employeur")) {
                        tvQuestionHobbies.setText("Quel recherchez vous chez la personne a recruter ?");
                        etHobbies1.setHint("1er recherche");
                        etHobbies2.setHint("2eme recherche");
                        etHobbies3.setHint("3eme recherche");
                        etHobbies4.setHint("4eme recherche");
                        etHobbies5.setHint("5eme recherche");
                        tvQuestionPerso.setText("Quels avantages votre entreprise offre ?");
                        etPerso1.setHint("1er avantage");
                        etPerso2.setHint("2eme avantage");
                        etPerso3.setHint("3eme avantage");
                        etPerso4.setHint("4eme avantage");
                        etPerso5.setHint("5eme avantage");
                    } else {
                        tvQuestionHobbies.setText("Quels sont vos hobbies ?");
                        etHobbies1.setHint("1er hobby");
                        etHobbies2.setHint("2eme hobby");
                        etHobbies3.setHint("3eme hobby");
                        etHobbies4.setHint("4eme hobby");
                        etHobbies5.setHint("5eme hobby");
                        tvQuestionPerso.setText("Quels sont vos traits de personnalités ?");
                        etPerso1.setHint("1er trait personnel");
                        etPerso2.setHint("2eme trait personnel");
                        etPerso3.setHint("3eme trait personnel");
                        etPerso4.setHint("4eme trait personnel");
                        etPerso5.setHint("5eme trait personnel");
                    }
                }
                break;
=======
        switch (adapterView.getId()){
            case R.id.sTypeProfil:
                if(i == 0){
                    sProfilType.setVisibility(View.INVISIBLE);
                    sMyJob.setVisibility(View.INVISIBLE);
                    etNom.setVisibility(View.INVISIBLE);
                    etPrenom.setVisibility(View.INVISIBLE);
                }
                else
                    sProfilType.setVisibility(View.VISIBLE);
                if(i == 1){
                    tvType.setText("Recruteur");
                }
                else if(i == 2){
                    tvType.setText("Candidat");
                }
                else if(i == 3){
                    tvType.setText("Start-Upper");
                }
                break;
            case R.id.sSectorActivity:
                if(i == 0){
                    sMyJob.setVisibility(View.INVISIBLE);
                }
                else
                    sMyJob.setVisibility(View.VISIBLE) ;
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
>>>>>>> 638de1e42d93a5e9353a9e2f75136f242b42484b
            default:
                break;
        }
    }

<<<<<<< HEAD

    public void spinnerProfil(View v) {
        profilsRef = db.collection("Type de profils");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, profilList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sProfilType.setAdapter(adapter);
        profilsRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String secteur = document.getId();
                                profilList.add(secteur);
                            }
                            adapter.notifyDataSetChanged();
                            sProfilType.setOnItemSelectedListener(ProfilFragment.this);
                        }
                    }
                });
    }

    public void spinnerSecteur(View v, String variableGlobalChamps) {
        secteurList.clear();
        secteurRef = db.collection("Type de profils/" + variableGlobalChamps + "/Secteurs");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, secteurList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sSectorActivity.setAdapter(adapter);
        secteurRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String secteur = document.getId();
                                secteurList.add(secteur);
                            }
                            adapter.notifyDataSetChanged();
                            sSectorActivity.setOnItemSelectedListener(ProfilFragment.this);
                        }
                    }
                });
    }

    public void spinnerJob(View v, String variableGlobalChamps, String variableGlobalSecteur) {
        metierList.clear();
        metierRef = db.collection("Type de profils/" + variableGlobalChamps + "/Secteurs/" + variableGlobalSecteur + "/Metier");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, metierList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sJob.setAdapter(adapter);
        metierRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String secteur = document.getId();
                                metierList.add(secteur);
                            }
                            adapter.notifyDataSetChanged();
                            sJob.setOnItemSelectedListener(ProfilFragment.this);
                        }
                    }
                });
    }

    @Override
    public void onNothingSelected(android.widget.AdapterView<?> adapterView) {
    }

    public void invisibility(int niveau) {
        if(niveau >= 1)
        {
            tvQuestionHobbies.setVisibility(View.INVISIBLE);
            etHobbies1.setVisibility(View.INVISIBLE);
            etHobbies2.setVisibility(View.INVISIBLE);
            etHobbies3.setVisibility(View.INVISIBLE);
            etHobbies4.setVisibility(View.INVISIBLE);
            etHobbies5.setVisibility(View.INVISIBLE);
            tvQuestionPerso.setVisibility(View.INVISIBLE);
            etPerso1.setVisibility(View.INVISIBLE);
            etPerso2.setVisibility(View.INVISIBLE);
            etPerso3.setVisibility(View.INVISIBLE);
            etPerso4.setVisibility(View.INVISIBLE);
            etPerso5.setVisibility(View.INVISIBLE);
            tvDescription.setVisibility(View.INVISIBLE);
            etDescription.setVisibility(View.INVISIBLE);
            AddBdd.setVisibility(View.INVISIBLE);
        }
        if(niveau >= 2){
            sExp.setVisibility(View.INVISIBLE);
        }
        if(niveau >= 3){
            sJob.setVisibility(View.INVISIBLE);
        }
        if(niveau >= 4){
            sSectorActivity.setVisibility(View.INVISIBLE);
            etEntreprise.setVisibility(View.GONE);
            etPoste.setVisibility(View.GONE);
            etNom.setVisibility(View.INVISIBLE);
            etPrenom.setVisibility(View.INVISIBLE);
        }

    }
=======
    @Override
    public void onNothingSelected(android.widget.AdapterView<?> adapterView) {
    }
>>>>>>> 638de1e42d93a5e9353a9e2f75136f242b42484b
}
package com.airjob.airjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.util.Util;

import org.jetbrains.annotations.NotNull;

public class Creeuncompte extends AppCompatActivity {

    private FirebaseUser firebaseUser;
    private FirebaseFirestore db;
    private DocumentReference noteRef;
    private CollectionReference noteCollectionRef;

    private EditText Email, Identifiant, motdepasse, motdepasse2;

    private TextView textView3, textView7, textView4;

    private void init() {

        Email = findViewById(R.id.login);
        Identifiant = findViewById(R.id.motdepasselog);
        motdepasse = findViewById(R.id.motdepasse);
        motdepasse2 = findViewById(R.id.motdepasse2);


        db = FirebaseFirestore.getInstance();

        noteCollectionRef = db.collection("notes");

        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView7 = findViewById(R.id.textView7);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creeuncompte);

        init();

    }

    public void addcompte(View view) {

        String Email1 = Email.getText().toString().trim();
        String Identifiant1 = Identifiant.getText().toString().trim();
        String motdepasse1 = motdepasse.getText().toString().trim();
        String motdepassebis = motdepasse2.getText().toString().trim();


        if (motdepasse1.length() < 6) {
            textView3.setVisibility(View.VISIBLE);}
        else{textView3.setVisibility(View.INVISIBLE);}
        if (!Patterns.EMAIL_ADDRESS.matcher(Email1).matches()) {
            textView7.setVisibility(View.VISIBLE);

        } else if (!motdepasse1.equals(motdepassebis)) {
            textView4.setVisibility(View.VISIBLE);
        }

        if (Identifiant1.equals("")) {
            Identifiant.setError("Enter name");
        } else if (Email1.equals("")) {
            Email.setError("Enter Email");
        } else if (motdepasse1.equals("")) {
            motdepasse.setError("enterpassword");
        } else if (motdepassebis.equals("")) {
            motdepasse2.setError("enterpassword");
        }

        else {

            // 12 Ajout de la vérification de la connection internet
            if(com.airjob.airjobs.Util.connectionAvailable(this))// Si la connexion fonctionne

            { // Alors on exécute la méthode
                // 11.9 ProgressBar
                // progressBar.setVisibility(View.VISIBLE);
                final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.createUserWithEmailAndPassword(Email1, motdepasse1)
                        // Ajout la méthode addOnCompleteListener pour vérifier la bonne transmition des
                        // informations à Firebase Authenticator
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                // 11.10 ProgressBar
                                // progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    /** 5.2 Association de l'utilisateur courant à FirebaseUser dans le cadre du
                                     * changement de nom **/
                                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//                               // Affichage d'un toast de réussite
//                               Toast.makeText(SignupActivity.this, R.string.user_created_successfully, Toast.LENGTH_SHORT).show();
//                               // Lancement de l'activité suivante
//                               startActivity(new Intent(SignupActivity.this, LoginActivity.class));
//                                /** 6.3 On appelle la méthode updateOnlyUser() pour valider l'enreistrement dans Authenticator
//                                 * et dans RealTime db les 2 lignes commentées ci-dessus ne s'affiche que si l'enregistrement
//                                 * c'est bien passé dans les 2 endroits
//                                 */
//                                updateOnlyUser();
                                    /** 8 On lance la bonne méthode d'enregistrement dans la base en fonction de l'ajout d'un avatar ou non **/
//                                    if (localFileUri != null) {
//                                        updateNameAndPhoto();
//                                    } else {
//                                        updateNameOnly();
//                                    }
                                } else {
                                    // Affichage d'un Toast d'erreur avec l'erreur de la task (%1$s)
//                                    Toast.makeText(SignupActivity.this,
//                                            getString(R.string.signup_failed, task.getException()),
//                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                // A noter qu'il est possible d'ajouter la méthode suivante, identique au else ci-dessus
//            .addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                  Toast.makeText(SignupActivity.this,
//                                       getString(R.string.signup_failed, task.getException()),
//                                       Toast.LENGTH_SHORT).show();
//                }
//            });
                // 9.1 Sinon
            } else {
                // startActivity(new Intent(SignupActivity.this, NoInternetActivity.class));
            }

            Modelcreeruncompte contenuNote = new Modelcreeruncompte(Identifiant1, Email1, motdepasse1);

            noteCollectionRef.add(contenuNote)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(Creeuncompte.this, "Success", Toast.LENGTH_SHORT).show();
                            textView3.setVisibility(View.INVISIBLE);
                        }
                    });

        }
    }

//    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
//        @Override
//        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//            // Utilisation de actionId qui correspond à l'action ajouter dans le xml
//            switch (actionId){
//                case EditorInfo.IME_ACTION_DONE:
//                    addcompte(v);
//            }
//            return false; // On laisse le return à false pour empêcher le comportement normal du clavier
//        }
//    };

    /** 12 Ajout des boutons next et send à la place du retour chariot du keyboard **/
    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            // Utilisation de actionId qui correspond à l'action ajouter dans le xml
            switch (actionId){
                case EditorInfo.IME_ACTION_DONE:
                    addcompte(v);
            }
            return false; // On laisse le return à false pour empêcher le comportement normal du clavier
        }
    };

}
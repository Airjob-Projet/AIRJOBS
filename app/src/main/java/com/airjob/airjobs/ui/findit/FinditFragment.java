package com.airjob.airjobs.ui.findit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.airjob.airjobs.ItemAdapter;
import com.airjob.airjobs.databinding.FragmentFinditBinding;
import com.airjob.airjobs.ui.manageProfil.ModelProfilCandidat;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FinditFragment extends Fragment {

    private FirebaseFirestore db;
    private CollectionReference candidat;
    private CollectionReference noteCollectionRef;

private String id1;
private String id2;

private String profil;
private String profilmetier;



    private DocumentReference noteRef;





    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();


    RecyclerView recyclerView;
    List<ModelProfilCandidat> itemList;
    ItemAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    int X,Y;
=======
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.airjob.airjobs.databinding.FragmentFinditBinding;

public class FinditFragment extends Fragment {

    private FinditViewModel finditViewModel;
>>>>>>> 638de1e42d93a5e9353a9e2f75136f242b42484b
    private FragmentFinditBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
<<<<<<< HEAD


        binding = FragmentFinditBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        recyclerView= binding.rvProfil;
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
//        linearLayoutManager.scrollToPositionWithOffset(0,0);

        recyclerView.setLayoutManager(linearLayoutManager);

        // If you tap on the phone while the RecyclerView is scrolling it will stop in the middle.
        // This code fixes this. This code is not strictly necessary but it improves the behaviour.


        LinearSnapHelper snapHelper = new LinearSnapHelper() {
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                View centerView = findSnapView(layoutManager);
                if (centerView == null)
                    return RecyclerView.NO_POSITION;

                int position = layoutManager.getPosition(centerView);
                System.out.println("pos : "+ position);
                int targetPosition = -1;
                if (layoutManager.canScrollHorizontally()) {
                    if (velocityX < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                if (layoutManager.canScrollVertically()) {
                    if (velocityY < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                final int firstItem = 0;
                final int lastItem = layoutManager.getItemCount() - 1;
                targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem));
                return targetPosition;
            }
        };
        snapHelper.attachToRecyclerView(recyclerView);

        initData();
//        System.out.println("list : "+ initData());

//        recyclerView.setAdapter(new ItemAdapter(initData(),getContext()));


        return view;
    }

    private void initData() {

        db = FirebaseFirestore.getInstance();

        noteCollectionRef= db.collection("Candidat");
        noteCollectionRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot listeSnapshots) {
                        //String notes = "";
                        for(QueryDocumentSnapshot documentSnapshot : listeSnapshots){
                            // pour chaque element(snapshot) de la liste

                            ModelProfilCandidat contenuNote2 = documentSnapshot.toObject(ModelProfilCandidat.class);




                            id1=documentSnapshot.getId();
                            id2=uid;

                            System.out.println("id list1: "+id1+ " idsub: "+id2);


                            if(id1.equals(id2)){
                                System.out.println("id Candidat good: "+uid);

                                profil="Recruteur";
                                profilmetier=contenuNote2.getJob();

                                System.out.println("metier: "+profilmetier);

                            }







                        }
                        //tvSavedNote.setText(notes);
                    }
                });







        noteCollectionRef= db.collection("Recruteur");
        noteCollectionRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot listeSnapshots) {
                        //String notes = "";
                        for(QueryDocumentSnapshot documentSnapshot : listeSnapshots){
                            // pour chaque element(snapshot) de la liste

                             ModelProfilCandidat contenuNote3 = documentSnapshot.toObject(ModelProfilCandidat.class);

                             System.out.println("contenu de la note 3 :" + contenuNote3.getJob());



                            id1=documentSnapshot.getId();
                            id2=uid;

                            System.out.println("id list2: "+id1+ " idsub2: "+id2);


                            if(id1.equals(id2)){
                                System.out.println("id recruteur good: "+uid);

                                profil="Candidat";
                                profilmetier=contenuNote3.getJob();

                                //profilmetier=contenuNote3.getNom();
                                System.out.println("metier2: "+profilmetier+" profil2: "+profil);

                            }







                        }
                        //tvSavedNote.setText(notes);
                    }
                });


















        candidat= db.collection("Candidat");

        //System.out.println("candid: " + candidat.getId());


        noteRef = db.document("Candidat/"+uid);

        itemList=new ArrayList<ModelProfilCandidat>();







        candidat.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot listeSnapshots) {


                        for(QueryDocumentSnapshot documentSnapshot : listeSnapshots){
                            // pour chaque element(snapshot) de la liste


                            ModelProfilCandidat contenuNote6 = documentSnapshot.toObject(ModelProfilCandidat.class);

                            System.out.println("id7: " + documentSnapshot.getId()+ " profil: "+profil+" profilmetier: "+ profilmetier);
//                            System.out.println("contenu de la note candidat :" + contenuNote.getJob());





                            noteRef = db.document(profil+"/"+documentSnapshot.getId());
                            noteRef.get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            if(documentSnapshot.exists()){


                                                System.out.println("hello3: ");
                                                ModelProfilCandidat contenuNote= documentSnapshot.toObject(ModelProfilCandidat.class);

                                                System.out.println("metier affiché :" + contenuNote.getJob());



                                                    if(contenuNote.getJob().equals(profilmetier)) {
                                                         System.out.println("hello: " + contenuNote.getNom());
                                                        System.out.println("hello2: ");

                                                        itemList.add(new ModelProfilCandidat(contenuNote.getChamps(), contenuNote.getSecteur(), contenuNote.getDescription(), contenuNote.getEmail2(),
                                                                contenuNote.getJob(),contenuNote.getNom(),contenuNote.getPrenom(), contenuNote.getImageurl(), contenuNote.getPdfurl(),
                                                                contenuNote.getHobbie1(),contenuNote.getHobbie2(),contenuNote.getHobbie3(),contenuNote.getHobbie4()
                                                        , contenuNote.getHobbie5(), contenuNote.getTraitdep1(), contenuNote.getTraitdep2(), contenuNote.getTraitdep3(),
                                                                contenuNote.getTraitdep4(), contenuNote.getTraitdep5(), contenuNote.getExperience()));

                                                    }


                                               System.out.println("regarde la"+itemList);







                                            }else{

                                            }
                                            recyclerView.setAdapter(new ItemAdapter(itemList,getContext()));
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                        }
                                    });










                        }
                        //tvSavedNote.setText(notes);
                    }
                });
































=======
        finditViewModel =
                new ViewModelProvider(this).get(FinditViewModel.class);

        binding = FragmentFinditBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        finditViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
>>>>>>> 638de1e42d93a5e9353a9e2f75136f242b42484b
    }



}
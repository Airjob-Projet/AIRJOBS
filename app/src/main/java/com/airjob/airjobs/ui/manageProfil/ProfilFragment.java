package com.airjob.airjobs.ui.manageProfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.airjob.airjobs.databinding.FragmentProfilBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class ProfilFragment extends Fragment {

    private ProfilViewModel profilViewModel;
    private FragmentProfilBinding binding;

    private FirebaseFirestore db;
    private DocumentReference noteRef;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        db= FirebaseFirestore.getInstance();
        noteRef= db.document("Profil/User");

        profilViewModel =
                new ViewModelProvider(this).get(ProfilViewModel.class);

//        startActivity(new Intent(getActivity(), Creeunprofil.class));
        startActivity(new Intent(getActivity(), Creeunprofil.class));

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



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
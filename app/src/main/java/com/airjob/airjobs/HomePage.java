package com.airjob.airjobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.airjob.airjobs.databinding.ActivityBarNavBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {

    private ActivityBarNavBinding binding;

//    public void deco(View view) {
//        FirebaseAuth.getInstance().signOut();
////        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
////        firebaseUser = null;
//        startActivity(new Intent(HomePage.this, MainActivity.class));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBarNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_setting,
                R.id.navigation_localisation,
                R.id.navigation_findit,
                R.id.navigation_chat,
                R.id.navigation_profil/*,
                R.id.btnDeconnexion*/)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
}
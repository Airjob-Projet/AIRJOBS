package com.airjob.airjobs.ui.chat;

import static com.airjob.airjobs.ui.chat.ConstantNode.NODE_CHATLIST;
import static com.airjob.airjobs.ui.chat.ConstantNode.NODE_CHATS;
import static com.airjob.airjobs.ui.chat.ConstantNode.NODE_CANDIDATS;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airjob.airjobs.MainActivity;
import com.airjob.airjobs.R;
import com.airjob.airjobs.ui.chat.Adapter.MessageAdapter;
import com.airjob.airjobs.ui.chat.Model.ChatModel;
//import com.airjob.airjobs.ui.chat.Model.UserModel;
import com.airjob.airjobs.ui.manageProfil.ModelProfilCandidat;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {

    private static final String TAG = "#######>>>>>";

    // Var des widgets
    private CircleImageView profile_image;
    private TextView username;
    private ImageButton btn_send;
    private EditText text_send;
    private RecyclerView recyclerView;
    private String idParticipantChat;

    // Var globales
    private MessageAdapter messageAdapter;
    private List<ChatModel> mchat;
    private Intent intent;
    private String CHANNEL_ID = "Messages notifications";
    private ValueEventListener seenListener;

    // Var Firebase
    private FirebaseUser currentUser;
    private FirebaseFirestore db;

    private CollectionReference chatCollectionRef;
    private CollectionReference chatListCollectionRef;

    private DocumentReference userDocumentRef;
    private DocumentReference chatDocumentRef;
    private DocumentReference chatlistDocumentRef;


    // Initialisation des widgets
    private void init() {

        recyclerView = findViewById(R.id.recycler_view_message_activity);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        profile_image = findViewById(R.id.profile_image_ChatItemLeft);
        username = findViewById(R.id.username);
        btn_send = findViewById(R.id.btn_send);
        text_send = findViewById(R.id.text_send);
    }

    // Initialisation de FirebaseUser
    private void initFirebase() {
         currentUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        chatCollectionRef = db.collection(NODE_CHATS);
        chatListCollectionRef = db.collection(NODE_CHATLIST);

        userDocumentRef = db.collection(NODE_CANDIDATS).document(currentUser.getUid());
        chatDocumentRef = db.collection(NODE_CHATS).document(currentUser.getUid());
        chatlistDocumentRef = db.collection(NODE_CHATLIST).document(currentUser.getUid());
    }

    // Gestion des clics sur les boutons
    private void btnSend() {
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = ""; //###a voir
                String msg = text_send.getText().toString();
                if (!msg.equals("")) {
                    sendMessage(currentUser.getUid(), idParticipantChat, msg);
                    sendNotification(idParticipantChat, msg, token);
                    createNotificationChannel();
                } else {
                    Toast.makeText(MessageActivity.this, "You can't send an empty message", Toast.LENGTH_SHORT).show();
                }
                text_send.setText("");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        Log.i("##------->>>", "MessageActivity -> idParticipantChat : " + intent.getStringExtra("idParticipantChat"));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

//        ActionBar actionBar=getSupportActionBar();
//        actionBar.hide();

        // Initialisation des widgets
        init();
        // Initialisation de Firebase
        initFirebase();

        // Gestion de la toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Gestion de la navigation de la toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MessageActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        // R??cup??ration de l'id du participant au chat via l'intent

        intent = getIntent();
        idParticipantChat = intent.getStringExtra("idParticipantChat");

        // Appel des clics sur les boutons
        btnSend();

        // Query pour le SnapshotListner
        Query query = db.collection("Candidat");

        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (QueryDocumentSnapshot documentSnapshot : value) {
                    ModelProfilCandidat user = documentSnapshot.toObject(ModelProfilCandidat.class);
                    if(idParticipantChat.equals(user.getIDprofil())) {
                        username.setText(user.getNom() + " " + user.getPrenom());
                        if (user.getimageurl().equals("default")) {
                            profile_image.setImageResource(R.mipmap.ic_launcher);
                        } else {
                            Glide.with(getApplicationContext()).load(user.getimageurl()).into(profile_image);
                        }
                        readMessages(currentUser.getUid(), idParticipantChat, user.getimageurl());
                    }
                }
            }
        });
        seenMessage(idParticipantChat);
    }

    // Les messages on-ils ??t?? vu ?
    private void seenMessage(String userid) {
//        reference = FirebaseDatabase.getInstance().getReference("Chats");
//        seenListener = reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    ChatModel chat = snapshot.getValue(ChatModel.class);
//                    if (chat.getReceiver().equals(fuser.getUid()) && chat.getSender().equals(userid)) {
//                        HashMap<String, Object> hashMap = new HashMap<>();
//                        hashMap.put("isseen", true);
//                        snapshot.getRef().updateChildren(hashMap);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }

    // Envoi des messages
    private void sendMessage(String sender, final String receiver, String message) {

        // Upload du message dans la table Chats
        ChatModel newChat = new ChatModel(sender, receiver, message, false);
        long time= System.currentTimeMillis();
        String docId = String.valueOf(time);
        chatCollectionRef.document(docId).set(newChat)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });

        // Upload de la liaison des participants du chat en question dans Chatlist
//        chatlistDocumentRef.set(idParticipantChat, idParticipantChat);
        HashMap<String, Object> addUserToArrayMap = new HashMap<>();
        addUserToArrayMap.put("id", FieldValue.arrayUnion(idParticipantChat));


        chatListCollectionRef
                .document(currentUser.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (!task.getResult().exists()) {
                            chatListCollectionRef.document(currentUser.getUid()).set(addUserToArrayMap);
                        } else {
                            chatListCollectionRef.document(currentUser.getUid()).update(addUserToArrayMap);
                        }
                    }
                });
    }

    // Affichage des messages
    private void readMessages(final String myid, final String userid, final String imageurl) {

        mchat = new ArrayList<ChatModel>();

        Query query = db.collection(NODE_CHATS);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                mchat.clear();
                for (QueryDocumentSnapshot documentSnapshot : value) {
                    ChatModel chat = documentSnapshot.toObject(ChatModel.class);
                    if (chat.getReceiver().equals(myid) && chat.getSender().equals(userid) ||
                            chat.getReceiver().equals(userid) && chat.getSender().equals(myid)) {
                        mchat.add(chat);
                    }
                    messageAdapter = new MessageAdapter(MessageActivity.this, mchat, imageurl);
                    recyclerView.setAdapter(messageAdapter);
                }
            }
        });

    }



    // Cr??ation du contenu de la notification Push qui sera envoy??e au destinataire
    private void sendNotification(String sender, String msg, String token){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MessageActivity.this,
                CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_chat)
                .setContentTitle(sender)
                .setContentText(msg)
//                .setContentIntent(pendingIntent) // Ouverture de l'application sur la page du chat
                .setAutoCancel(true)
//                .setLargeIcon(bitmap) // Ajout de l'avatar de l'exp??diteur
                .setColor(getResources().getColor(R.color.teal_200))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT); // La priorit?? de la notification en g??n??ral DEFAULT

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MessageActivity.this);
        notificationManagerCompat.notify(1, builder.build()); // ATTENTION l'id est UNIQUE pour chacune des notifications que vous allez cr??er
    }

    // 2 Pour afficher les notifications sur les versions sup??rieures ?? Android 8 // Api26+
    private void createNotificationChannel() {
        // On cr???? des Notification channel seulement pour les versions API 26 et +

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //O = Oreo 8.0
            String name = "Message notification";
            String description = "Message's channel notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);

            channel.setDescription(description);

            // Enregistrement de la channel avec le service d'android OS
            NotificationManager manager = getSystemService(NotificationManager.class);
            Log.i("###---------------->>>>", "createNotificationChannel: " + "channel : " + channel);
            manager.createNotificationChannel(channel);
        }
    }

    // Pending Intent pour ouvrir l'activit?? message lors du clic sur la notification
    private void creaIntent() {
        Intent intent = new Intent(MessageActivity.this, MessageActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                MessageActivity.this, 0, intent, 0);
    }





    // Gestion du statut de l'utilisateur
    private void status(String status) {
        userDocumentRef.update("status", status);
    }

    @Override
    protected void onResume() {
        super.onResume();
        status("Online");
    }

    @Override
    protected void onStop() {
        super.onStop();
        status("offline");
    }
}
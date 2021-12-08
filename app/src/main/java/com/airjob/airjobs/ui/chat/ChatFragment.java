package com.airjob.airjobs.ui.chat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airjob.airjobs.R;
import com.airjob.airjobs.databinding.FragmentChatBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

//    private TextView tvChat;


     RecyclerView recyclerView;
     List<ModelChat> itemChatList;
     ItemChatAdapter chatAdapter;

//    private ChatViewModel finditViewModel;
    private FragmentChatBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        finditViewModel =
//                new ViewModelProvider(this).get(ChatViewModel.class);

        binding = FragmentChatBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        recyclerView = binding.rvChat;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        initData();

        recyclerView.setAdapter(new ItemChatAdapter(initData(),getContext()));





        return view;
    }

    private List<ModelChat> initData() {
        itemChatList=new ArrayList<>();
        itemChatList.add(new ModelChat(R.drawable.musk, "Elon musk", "9:30"));
        itemChatList.add(new ModelChat(R.drawable.macron, "Emmanuel Macron", "15:20"));
        itemChatList.add(new ModelChat(R.drawable.dsk, "Dominique Strauss Khan", "22:15"));
        itemChatList.add(new ModelChat(R.drawable.cheguevara, "Che Guevarra", "00:45"));



        return itemChatList;

    }

    public void ChatMessage(){

        startActivity(new Intent(getActivity(),ChatCommFragment.class));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
package com.airjob.airjobs.ui.chat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airjob.airjobs.R;
import com.airjob.airjobs.databinding.FragmentChatBinding;
import com.airjob.airjobs.databinding.FragmentChatCommBinding;


public class ChatCommFragment extends Fragment {

    private ChatViewModel chatViewModel;
    private FragmentChatCommBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        chatViewModel =
                new ViewModelProvider(this).get(ChatViewModel.class);


        binding = FragmentChatCommBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.tvChat;
        chatViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
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
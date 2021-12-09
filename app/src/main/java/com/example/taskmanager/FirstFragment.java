package com.example.taskmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.taskmanager.databinding.FragmentFirstBinding;
import com.example.taskmanager.network.RetroFitGenerator;
import com.example.taskmanager.network.dto.LoginDto;
import com.example.taskmanager.network.dto.TokenDto;
import com.example.taskmanager.network.service.AuthService;
import com.example.taskmanager.network.storage.Storage;
import com.example.taskmanager.network.storage.impl.SharedPreferencesStorage;

import retrofit2.Retrofit;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * FirstFragment
 *
 * @author 
 */
public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    private Storage storage;

    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(otherView -> {
            super.onViewCreated(view, savedInstanceState);
            sharedPreferences = requireContext().getSharedPreferences("shared_preferences", Context.MODE_PRIVATE);
            storage = new SharedPreferencesStorage(sharedPreferences);
            sendAuthRequest();

        });
    }

    private void sendAuthRequest() {
        Retrofit retrofit = RetroFitGenerator.getInstance(storage);
        AuthService authService = retrofit.create(AuthService.class);
        LoginDto loginDto = new LoginDto("santiago@mail.com", "passw0rd");
        Action1<TokenDto> successAction = tokenDto -> onSuccess(tokenDto.getAccessToken());
        Action1<Throwable> failedAction = throwable -> Log.e("Developer", "Auth error", throwable);

        authService.auth(loginDto).observeOn(Schedulers.from(ContextCompat.getMainExecutor(requireContext()))).subscribe(successAction, failedAction);
    }

    private void onSuccess(String token) {
        Log.d("Developer", "Token: " + token);
        binding.textviewFirst.setText(token);
        storage.saveToken(token);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
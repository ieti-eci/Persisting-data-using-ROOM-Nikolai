package com.example.taskmanager.network.service;

import com.example.taskmanager.network.dto.LoginDto;
import com.example.taskmanager.network.dto.TokenDto;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Signature service to get a login
 *
 * @author 
 */
public interface AuthService {

    @POST("auth")
    Observable<TokenDto> auth(@Body LoginDto loginDto);

}

package com.android.teayudo.retrofit;


import com.android.teayudo.retrofit.request.RequestLogin;
import com.android.teayudo.retrofit.request.RequestSignup;
import com.android.teayudo.retrofit.response.ResponseAuth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TeAyudoService {

    @POST("auth/login")
    Call<ResponseAuth> doLogin(@Body RequestLogin requestLogin);

    @POST("auth/signup")
    Call<ResponseAuth> doSingUp(@Body RequestSignup requestSignup);
}

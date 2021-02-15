package com.example.teayudo.retrofit;

import com.example.teayudo.common.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TeAyudoClient {
    private static TeAyudoClient instance = null;
    private TeAyudoService teayudoService;
    private Retrofit retrofit;

    public TeAyudoClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_TEAYUDO_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        teayudoService = retrofit.create(TeAyudoService.class);

    }
    //Patrón Singleton
    public static TeAyudoClient getInstance() {
        if (instance == null) {
            instance = new TeAyudoClient();
        }
        return instance;
    }
    public TeAyudoService getTeayudoService(){
        return  teayudoService;
    }
}

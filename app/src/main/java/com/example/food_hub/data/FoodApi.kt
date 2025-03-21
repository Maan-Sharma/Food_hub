package com.example.food_hub.data

import com.example.food_hub.data.models.AuthResponse
import com.example.food_hub.data.models.SignUpRequest
import com.example.food_hub.data.models.SingInRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodApi {
    @GET("/food")
    suspend fun getFood(): List<String>

    //request  model
    ///response model
    //endpoint



    @POST("/auth/signup")
    suspend fun signUp(@Body request: SignUpRequest):  AuthResponse


    @POST("/auth/login")
    suspend fun signIn(@Body request: SingInRequest):  AuthResponse


}
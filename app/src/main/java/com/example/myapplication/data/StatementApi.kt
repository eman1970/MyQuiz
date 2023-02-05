package com.example.myapplication.data

import retrofit2.Response
import retrofit2.http.GET

interface StatementApi {
    @GET("ws.json")
    suspend fun getProducts(): Response<List<Statement>>
}
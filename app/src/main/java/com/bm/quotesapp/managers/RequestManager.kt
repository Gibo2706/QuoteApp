package com.bm.quotesapp.managers

import com.bm.quotesapp.data.QuotesResponse
import com.bm.quotesapp.listeners.QuotesResponseListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://api.quotable.io/"

class RequestManager {
    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRandQuote(listener: QuotesResponseListener){
        val call = retrofit.create(CallQuotes::class.java).callRandomQuote()
        call.enqueue(object: Callback<QuotesResponse>{
            override fun onResponse(
                call: Call<QuotesResponse>,
                response: Response<QuotesResponse>
            ) {
                if(!response.isSuccessful){
                    listener.onFailure(response.message())
                    return
                }
                listener.onSuccess(response.body()!!, response.message())
            }

            override fun onFailure(call: Call<QuotesResponse>, t: Throwable) {
                listener.onFailure(t.message!!)
            }

        })
    }
    private interface CallQuotes {
        @GET("random")
        fun callRandomQuote(): Call<QuotesResponse>
    }
}

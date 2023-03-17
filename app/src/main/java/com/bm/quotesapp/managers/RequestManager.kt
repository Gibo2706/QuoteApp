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

    fun getLoveQuote(listener: QuotesResponseListener){
        val call = retrofit.create(CallQuotes::class.java).callLoveQuote()
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

    fun getLifeQuote(listener: QuotesResponseListener){
        val call = retrofit.create(CallQuotes::class.java).callLifeQuote()
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
    fun getInspirationalQuote(listener: QuotesResponseListener){
        val call = retrofit.create(CallQuotes::class.java).callInspirationalQuote()
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

    fun getFamousQuote(listener: QuotesResponseListener){
        val call = retrofit.create(CallQuotes::class.java).callFamousQuote()
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

    fun getTechQuote(listener: QuotesResponseListener){
        val call = retrofit.create(CallQuotes::class.java).callTechQuote()
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
    fun getMotivationalQuote(listener: QuotesResponseListener){
        val call = retrofit.create(CallQuotes::class.java).callMotivationalQuote()
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

    fun getFunnyQuote(listener: QuotesResponseListener){
        val call = retrofit.create(CallQuotes::class.java).callFunnyQuote()
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

        @GET("random?tags=love")
        fun callLoveQuote(): Call<QuotesResponse>

        @GET("random?tags=life")
        fun callLifeQuote(): Call<QuotesResponse>

        @GET("random?tags=famous")
        fun callFamousQuote(): Call<QuotesResponse>

        @GET("random?tags=technology")
        fun callTechQuote(): Call<QuotesResponse>

        @GET("random?tags=inspirational")
        fun callInspirationalQuote(): Call<QuotesResponse>

        @GET("random?tags=motivational")
        fun callMotivationalQuote(): Call<QuotesResponse>

        @GET("random?tags=funny")
        fun callFunnyQuote(): Call<QuotesResponse>

    }
}

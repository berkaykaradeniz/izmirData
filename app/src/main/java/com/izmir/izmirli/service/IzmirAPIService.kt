package com.kafein.weatherapp


import com.izmir.izmirli.model.GameTypeResponse
import com.izmir.izmirli.model.HastanelerResponse
import com.izmir.izmirli.model.NobetciEczaneResponse
import com.izmir.izmirli.model.TrenGarlariResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class IzmirAPIService {

    private val BASE_URL = "https://openapi.izmir.bel.tr/api/ibb/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(IzmirAPI::class.java)

    fun getTrenGarlari(): Single<TrenGarlariResponse> {
        return api.getTrenGarlari()
    }
    fun getTypeResponse(): Single<GameTypeResponse> {
        return api.getGameType()
    }
    fun getNobetciEczane() : Single<NobetciEczaneResponse> {
        return api.getNobetciEczane()
    }
    fun getHastaneler() : Single<HastanelerResponse> {
        return api.getHastaneler()
    }
}
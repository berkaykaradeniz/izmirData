package com.izmir.izmirli.model


import com.google.gson.annotations.SerializedName

class GameTypeResponse : ArrayList<GameTypeResponse.GameTypeResponseItem>(){
    data class GameTypeResponseItem(
        @SerializedName("tur_ad")
        var turAd: String? = null,
        @SerializedName("tur_id")
        var turId: String? = null
    )
}
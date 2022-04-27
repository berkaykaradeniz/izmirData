package com.izmir.izmirli.model

data class HastanelerResponse(
    val kayit_sayisi: Int, // 84
    val onemliyer: List<Onemliyer>,
    val sayfa_numarasi: Int, // 1
    val sayfadaki_kayitsayisi: Int, // 10
    val toplam_sayfa_sayisi: Int // 9
)

data class Onemliyer(
    val ACIKLAMA: String,
    val ADI: String, // Aliağa Devlet Hastanesi
    val BOYLAM: Double, // 26.974983403458
    val ENLEM: Double, // 38.787045353073
    val ILCE: String, // ALİAĞA
    val ILCEID: String, // 3
    val KAPINO: String, // 7
    val MAHALLE: String, // YENİ
    val MAHALLEID: Any, // null
    val YOL: String // RUMELİ
)
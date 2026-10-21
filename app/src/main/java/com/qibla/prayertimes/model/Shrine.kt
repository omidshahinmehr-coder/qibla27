package com.qibla.prayertimes.model

import androidx.annotation.StringRes
import com.qibla.prayertimes.R

/**
 * A holy shrine / mosque the "direction and distance" screen can point to.
 *
 * [id] is also used to look for an optional photo: if a drawable named `shrine_<id>` exists in
 * res/drawable-nodpi (e.g. shrine_najaf.png) it is shown, otherwise a drawn illustration is used.
 */
data class Shrine(
    val id: String,
    @StringRes val nameRes: Int,
    @StringRes val cityRes: Int,
    val lat: Double,
    val lon: Double,
    /** Illustration style: dome color (ARGB) and number of minarets. */
    val domeColor: Long,
    val minarets: Int
)

private const val GOLD = 0xFFE8B84A
private const val GREEN = 0xFF2E9E6B

/** Coordinates of the shrine buildings themselves (supplied by the app owner). */
val SHRINES: List<Shrine> = listOf(
    Shrine("najaf", R.string.shrine_najaf_name, R.string.shrine_najaf_city, 31.99592790, 44.31458190, GOLD, 2),
    Shrine("karbala", R.string.shrine_karbala_name, R.string.shrine_karbala_city, 32.61633410, 44.03253360, GOLD, 2),
    Shrine("kadhimiya", R.string.shrine_kadhimiya_name, R.string.shrine_kadhimiya_city, 33.38023000, 44.33819000, GOLD, 4),
    Shrine("samarra", R.string.shrine_samarra_name, R.string.shrine_samarra_city, 34.19893000, 43.87353000, GOLD, 2),
    Shrine("ruqayya", R.string.shrine_ruqayya_name, R.string.shrine_ruqayya_city, 33.51333000, 36.30722000, GOLD, 2),
    Shrine("zaynab", R.string.shrine_zaynab_name, R.string.shrine_zaynab_city, 33.44444000, 36.34083000, GOLD, 2),
    Shrine("mashhad", R.string.shrine_mashhad_name, R.string.shrine_mashhad_city, 36.28800000, 59.61570000, GOLD, 2),
    Shrine("masoumeh", R.string.shrine_masoumeh_name, R.string.shrine_masoumeh_city, 34.64170000, 50.87900000, GOLD, 2),
    Shrine("nabawi", R.string.shrine_nabawi_name, R.string.shrine_nabawi_city, 24.46833000, 39.61083000, GREEN, 4)
)

package com.qibla.prayertimes.model

import android.content.Context
import com.qibla.prayertimes.data.QiblaMath
import com.qibla.prayertimes.util.LocalePrefs

/** Resolves which of the catalog's three languages should be used to display city names. */
private fun currentCatalogLanguage(context: Context): String = LocalePrefs.currentLanguage(context)

/**
 * Returns the built-in city list with names in whichever of the app's three supported
 * languages (Persian, English, Arabic) is currently active — the user's in-app language
 * choice if they've set one, otherwise the system language. Falls back to English for
 * any other language.
 *
 * The underlying data (name in all three languages + coordinates) lives in the :shared module
 * as [DEFAULT_CITY_CATALOG], so the watch app draws from the exact same list.
 */
fun defaultCities(context: Context): List<City> {
    val language = currentCatalogLanguage(context)
    return DEFAULT_CITY_CATALOG.map { entry -> City(entry.nameFor(language), entry.lat, entry.lon) }
}

/**
 * If [lat]/[lon] match one of the built-in catalog entries, returns that entry's name in
 * whichever language is currently active (in-app override, else system) — used to re-localize
 * a *previously saved* selected city after the in-app language changes, since the name
 * persisted to storage is a plain string frozen in whatever language was active when it was
 * saved.
 *
 * Returns null for coordinates that aren't in the catalog (custom cities the user added, or
 * ones picked via the map/geocoding search) — those names are user-owned and must never be
 * overwritten. A small tolerance absorbs floating-point round-tripping through JSON storage.
 */
fun localizedCatalogName(context: Context, lat: Double, lon: Double): String? {
    val entry = DEFAULT_CITY_CATALOG.firstOrNull {
        kotlin.math.abs(it.lat - lat) < 0.0001 && kotlin.math.abs(it.lon - lon) < 0.0001
    } ?: return null
    return entry.nameFor(currentCatalogLanguage(context))
}

/**
 * The built-in catalog city closest to [lat]/[lon], if one lies within [maxKm]; used to give a
 * GPS fix a proper city name instead of whatever the geocoder returns.
 */
fun nearestCatalogEntry(lat: Double, lon: Double, maxKm: Int = 20): CityCatalogEntry? {
    val best = DEFAULT_CITY_CATALOG.minByOrNull {
        QiblaMath.distanceKmBetween(lat, lon, it.lat, it.lon)
    } ?: return null
    return if (QiblaMath.distanceKmBetween(lat, lon, best.lat, best.lon) <= maxKm) best else null
}

/** The catalog name (in the active language) for a [City.catalogKey], or null if unknown. */
fun catalogNameForKey(context: Context, key: String): String? =
    DEFAULT_CITY_CATALOG.firstOrNull { it.nameEn == key }?.nameFor(currentCatalogLanguage(context))

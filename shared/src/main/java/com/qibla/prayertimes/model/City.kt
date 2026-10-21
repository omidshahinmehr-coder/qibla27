package com.qibla.prayertimes.model

data class City(
    val name: String,
    val lat: Double,
    val lon: Double,
    /**
     * Set when this city's name comes from a built-in catalog entry (value = that entry's English
     * name) even though [lat]/[lon] are the user's real GPS fix rather than the catalog's own
     * coordinates. It lets the name follow the in-app language later.
     */
    val catalogKey: String? = null
)

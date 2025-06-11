package cz.home.nbaplayers.library.networking.model

import com.squareup.moshi.Json

data class TeamDto(
    val id: Int,
    val abbreviation: String,
    val city: String,
    val conference: String,
    val division: String,
    @Json(name = "full_name") val fullName: String,
    val name: String
)
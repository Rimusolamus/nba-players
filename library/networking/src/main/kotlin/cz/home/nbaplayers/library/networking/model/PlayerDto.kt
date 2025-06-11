package cz.home.nbaplayers.library.networking.model

import com.squareup.moshi.Json

data class PlayerDto(
    val id: Int,
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String,
    val position: String?,
    @Json(name = "height_feet") val heightFeet: Int?,
    @Json(name = "height_inches") val heightInches: Int?,
    @Json(name = "weight_pounds") val weightPounds: Int?,
    val team: TeamDto
)
package cz.home.nbaplayers.library.networking.model

import com.google.gson.annotations.SerializedName

data class TeamDetailDto(
    @SerializedName("data") val data: TeamDto
)

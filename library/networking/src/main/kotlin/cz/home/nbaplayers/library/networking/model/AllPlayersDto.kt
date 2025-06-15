package cz.home.nbaplayers.library.networking.model

import com.google.gson.annotations.SerializedName

data class AllPlayersDto(
    @SerializedName("data") val data : ArrayList<PlayerDto>,
    @SerializedName("meta") val meta : MetaDto
)
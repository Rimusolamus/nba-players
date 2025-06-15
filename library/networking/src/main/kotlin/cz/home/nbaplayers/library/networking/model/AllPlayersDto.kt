package cz.home.nbaplayers.library.networking.model

import com.google.gson.annotations.SerializedName

data class AllPlayersDto(
    @SerializedName("data") var data : ArrayList<PlayerDto>,
    @SerializedName("meta") var meta : MetaDto
)
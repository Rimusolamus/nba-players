package cz.home.nbaplayers.library.networking.model

import com.google.gson.annotations.SerializedName

data class PlayerDetailsDto(
    @SerializedName("data") var data: PlayerDto,
)
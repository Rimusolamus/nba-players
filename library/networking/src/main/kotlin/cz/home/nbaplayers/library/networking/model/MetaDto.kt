package cz.home.nbaplayers.library.networking.model

import com.google.gson.annotations.SerializedName


data class MetaDto(
    @SerializedName("next_cursor") var nextCursor: Int? = null,
    @SerializedName("per_page") var perPage: Int? = null
)
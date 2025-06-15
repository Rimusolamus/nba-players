package cz.home.nbaplayers.library.networking.model

import com.google.gson.annotations.SerializedName

data class TeamDto(
    @SerializedName("id") var id: Int,
    @SerializedName("conference") var conference: String,
    @SerializedName("division") var division: String,
    @SerializedName("city") var city: String,
    @SerializedName("name") var name: String,
    @SerializedName("full_name") var fullName: String,
    @SerializedName("abbreviation") var abbreviation: String
)
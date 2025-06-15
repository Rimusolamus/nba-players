package cz.home.nbaplayers.library.networking.model

import com.google.gson.annotations.SerializedName

data class PlayerDto(
    @SerializedName("id") var id: Int,
    @SerializedName("first_name") var firstName: String,
    @SerializedName("last_name") var lastName: String,
    @SerializedName("position") var position: String,
    @SerializedName("height") var height: String? = null,
    @SerializedName("weight") var weight: String? = null,
    @SerializedName("jersey_number") var jerseyNumber: String? = null,
    @SerializedName("college") var college: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("draft_year") var draftYear: Int? = null,
    @SerializedName("draft_round") var draftRound: Int? = null,
    @SerializedName("draft_number") var draftNumber: Int? = null,
    @SerializedName("team") var team: TeamDto
)
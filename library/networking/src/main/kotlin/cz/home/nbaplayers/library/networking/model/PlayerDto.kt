package cz.home.nbaplayers.library.networking.model

import com.google.gson.annotations.SerializedName

data class PlayerDto(
    @SerializedName("id") var id: Int,
    @SerializedName("first_name") var firstName: String,
    @SerializedName("last_name") var lastName: String,
    @SerializedName("position") var position: String,
    @SerializedName("height") var height: String,
    @SerializedName("weight") var weight: String,
    @SerializedName("jersey_number") var jerseyNumber: String,
    @SerializedName("college") var college: String,
    @SerializedName("country") var country: String,
    @SerializedName("draft_year") var draftYear: Int,
    @SerializedName("draft_round") var draftRound: Int,
    @SerializedName("draft_number") var draftNumber: Int,
    @SerializedName("team") var team: TeamDto
)
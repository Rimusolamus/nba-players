package cz.home.nbaplayers.feature.playerdetails.data

import cz.home.nbaplayers.feature.playerdetails.model.Player
import cz.home.nbaplayers.library.networking.model.PlayerDto

fun PlayerDto.toModel() = Player(
    id = id,
    firstName = firstName,
    lastName = lastName,
    position = position,
    height = height,
    weight = weight,
    jerseyNumber = jerseyNumber,
    college = college,
    country = country,
    draftYear = draftYear,
    draftRound = draftRound,
    draftNumber = draftNumber,
    team = team.toModel()
)
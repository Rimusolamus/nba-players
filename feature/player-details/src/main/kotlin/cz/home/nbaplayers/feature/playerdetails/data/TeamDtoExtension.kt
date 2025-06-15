package cz.home.nbaplayers.feature.playerdetails.data

import cz.home.nbaplayers.feature.playerdetails.model.Team
import cz.home.nbaplayers.library.networking.model.TeamDto

fun TeamDto.toModel() = Team(
    id = id,
    city = city,
    name = name
)
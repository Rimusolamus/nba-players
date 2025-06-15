package cz.home.nbaplayers.feature.allplayers.data

import cz.home.nbaplayers.feature.allplayers.model.Team
import cz.home.nbaplayers.library.networking.model.TeamDto

fun TeamDto.toModel() = Team(
    id = id,
    city = city,
    name = name
)
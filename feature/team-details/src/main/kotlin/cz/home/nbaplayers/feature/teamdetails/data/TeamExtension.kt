package cz.home.nbaplayers.feature.teamdetails.data

import cz.home.nbaplayers.feature.teamdetails.model.Team
import cz.home.nbaplayers.library.networking.model.TeamDto

fun TeamDto.toModel() = Team(
    id = id,
    abbreviation = abbreviation,
    city = city,
    conference = conference,
    division = division,
    fullName = fullName,
    name = name
)
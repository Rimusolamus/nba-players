package cz.home.nbaplayers.feature.allplayers.data

import cz.home.nbaplayers.feature.allplayers.model.Player
import cz.home.nbaplayers.library.networking.model.PlayerDto

fun PlayerDto.toModel() = Player(
    id = id,
    firstName = firstName,
    lastName = lastName,
    position = position,
    team = team.toModel()
)
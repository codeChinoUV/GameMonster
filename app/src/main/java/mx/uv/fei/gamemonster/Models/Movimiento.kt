package mx.uv.fei.gamemonster.Models

import mx.uv.fei.gamemonster.Models.Enums.TipoJugador

data class Movimiento (
    var tipoJugador: TipoJugador,
    var movimiento: String)
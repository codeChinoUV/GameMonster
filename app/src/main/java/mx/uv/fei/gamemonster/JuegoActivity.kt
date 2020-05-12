package mx.uv.fei.gamemonster

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class JuegoActivity: AppCompatActivity(){
    var porcentajeVidaJuegador: Int = 100
    var porcentajeVidaMounstro: Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)
        var nombre = intent.getStringExtra("nombreUsuario");
        colocarNombreUsuario(nombre)
    }

    fun colocarNombreUsuario(nombreUsuario: String){
        var tvNombreUsuario = findViewById<TextView>(R.id.tvJugador)
        tvNombreUsuario.text = nombreUsuario
    }
    
}
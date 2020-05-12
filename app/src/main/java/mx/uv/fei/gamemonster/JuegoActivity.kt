package mx.uv.fei.gamemonster

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
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

    /**
     * Coloca el nombreUsuario en el TextView tvJugador
     * @param nombreUsuario El nombre a colocar en el tvJugador
     */
    fun colocarNombreUsuario(nombreUsuario: String){
        var tvNombreUsuario = findViewById<TextView>(R.id.tvJugador)
        tvNombreUsuario.text = nombreUsuario
    }

    /**
     * Genera un numero aletorio entre 5 y 20 y se lo resta a la vida total del jugador
     */
    fun atacarAJugador(){
        var cantidadDaño = (5 until 20).random()
        porcentajeVidaJuegador -= cantidadDaño
        if(porcentajeVidaJuegador <= 0){
            porcentajeVidaJuegador = 0
            refrescarElementosVidaDeJugador()
            mostrarMensajeEstadoPartida("¡Perdiste!")
        }else{
            refrescarElementosVidaDeJugador()
        }
    }

    /**
     * Actualiza la informacion del progressbar y del textview de la vida del jugador
     */
    fun refrescarElementosVidaDeJugador(){
        Thread.sleep(500)
        var pbVidaJugador = findViewById<ProgressBar>(R.id.pbJugador)
        var tvVidaJugador = findViewById<TextView>(R.id.tvPorcentajeVidaJugador)
        pbVidaJugador.progress = porcentajeVidaJuegador
        tvVidaJugador.text = porcentajeVidaJuegador.toString() + "%"
    }

    /**
     * Descuenta una cantidad aleatoria entre 5 y 15 al porcentajeVidaMounstro
     */
    fun atacarAMounstro(view: View?){
        var cantidadDeDaño = (5 until 15).random()
        porcentajeVidaMounstro -= cantidadDeDaño
        if(porcentajeVidaMounstro <= 0){
            porcentajeVidaMounstro = 0
            refrecarElementosVidaDeMounstro()
            mostrarMensajeEstadoPartida("¡Ganaste!")
        }else{
            refrecarElementosVidaDeMounstro()
            atacarAJugador()
        }
    }

    /**
     * Actualiza la información del progressbar y textview del mounstro
     */
    fun refrecarElementosVidaDeMounstro(){
        Thread.sleep(500)
        var pbVidaMountro = findViewById<ProgressBar>(R.id.pbMounstro)
        var tvVidaMountro = findViewById<TextView>(R.id.tvPorcentajeVidaMounstro)
        pbVidaMountro.progress = porcentajeVidaMounstro
        tvVidaMountro.text = porcentajeVidaMounstro.toString() + "%"
    }

    /**
     * Habilita el textview estado y le estable el texto
     * @param estado El texto que tendra el TextView
     */
    fun mostrarMensajeEstadoPartida(estado: String){
        var tvEstado = findViewById<TextView>(R.id.tvEstadoPartida)
        tvEstado.text = estado
        tvEstado.visibility= View.VISIBLE
        deshabilitarBotones()
        mostrarBotonIrAPantallaPrincipal()
    }

    /**
     * Deshabilita la funcionalidad de los botonoes de Curar, Atacar y Rendirse
     */
    fun deshabilitarBotones(){
        var btnRendirse = findViewById<Button>(R.id.btnRendirse)
        var btnCurar = findViewById<Button>(R.id.btnCurar)
        var btnAtacar = findViewById<Button>(R.id.btnAtacar)

        btnRendirse.isClickable = false
        btnCurar.isClickable = false
        btnAtacar.isClickable = false
    }

    /**
     * Aumenta una cantidad aleatoria de vida entre 10 y 25 al porcentajeVidaJugador
     */
    fun curarse(view: View?){
        var vidaRecuperar = (10 until 25).random()
        porcentajeVidaJuegador += vidaRecuperar
        if(porcentajeVidaJuegador > 100){
            porcentajeVidaJuegador = 100
        }
        refrescarElementosVidaDeJugador()
        atacarAJugador()
    }

    /**
     * Establece a 0 el porcetanjeVidaJugador
     */
    fun rendirse(view: View?){
        porcentajeVidaJuegador = 0
        mostrarMensajeEstadoPartida("¡Perdiste!")
        refrescarElementosVidaDeJugador()
    }

    /**
     * Muestra el boton PantallaPrincipal
     */
    fun mostrarBotonIrAPantallaPrincipal(){
        var btnPantallaPrincipal = findViewById<Button>(R.id.btnPantallaPrincipal)
        btnPantallaPrincipal.visibility = View.VISIBLE
    }

    fun clickBtnPantallaPrincipal(view: View?){
        val intento = Intent(this, MainActivity::class.java)
        startActivity(intento)
    }
}
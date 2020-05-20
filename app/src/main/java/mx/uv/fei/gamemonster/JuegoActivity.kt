package mx.uv.fei.gamemonster

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.uv.fei.gamemonster.Models.Enums.TipoJugador
import mx.uv.fei.gamemonster.Models.Movimiento

class JuegoActivity: AppCompatActivity(){
    private var porcentajeVidaJuegador: Int = 100
    private var porcentajeVidaMounstro: Int = 100
    private val movimientosAdapter: RecyclerAdapterMovimientos = RecyclerAdapterMovimientos()
    private var descripcionDanioAMountro = "El Jugador ataco con "
    private var descripcionDanioAJugador = "El Mounstro ataco con "
    private var descripcionRecuperarVida = "El Jugador recupero el "
    private var descripcionRendirse = "El Jugador se rindio"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)
        val nombre = intent.getStringExtra("nombreUsuario");
        colocarNombreUsuario(nombre)
        iniciarRecyclerView()
    }

    /**
     * Coloca el nombreUsuario en el TextView tvJugador
     * @param nombreUsuario El nombre a colocar en el tvJugador
     */
    private fun colocarNombreUsuario(nombreUsuario: String){
        val tvNombreUsuario = findViewById<TextView>(R.id.tvJugador)
        tvNombreUsuario.text = nombreUsuario
    }

    /**
     * Genera un numero aletorio entre 5 y 20 y se lo resta a la vida total del jugador
     */
    private fun atacarAJugador(){
        val cantidadDanio = (5 until 20).random()
        val descripcionMovimiento = "$descripcionDanioAJugador$cantidadDanio%"
        val movimiento = Movimiento(TipoJugador.MOUNSTRO, descripcionMovimiento)
        movimientosAdapter.agregarMovimiento(movimiento)
        porcentajeVidaJuegador -= cantidadDanio
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
    private fun refrescarElementosVidaDeJugador(){
        Thread.sleep(500)
        val pbVidaJugador = findViewById<ProgressBar>(R.id.pbJugador)
        val tvVidaJugador = findViewById<TextView>(R.id.tvPorcentajeVidaJugador)
        pbVidaJugador.progress = porcentajeVidaJuegador
        tvVidaJugador.text = "$porcentajeVidaJuegador%"
    }

    /**
     * Descuenta una cantidad aleatoria entre 5 y 15 al porcentajeVidaMounstro
     */
    fun atacarAMounstro(view: View?){
        val cantidadDeDanio = (5 until 15).random()
        val descripcionMovimiento = "$descripcionDanioAMountro$cantidadDeDanio%"
        val movimiento = Movimiento(TipoJugador.JUGADOR, descripcionMovimiento)
        movimientosAdapter.agregarMovimiento(movimiento)
        porcentajeVidaMounstro -= cantidadDeDanio
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
    private fun refrecarElementosVidaDeMounstro(){
        Thread.sleep(500)
        val pbVidaMountro = findViewById<ProgressBar>(R.id.pbMounstro)
        val tvVidaMountro = findViewById<TextView>(R.id.tvPorcentajeVidaMounstro)
        pbVidaMountro.progress = porcentajeVidaMounstro
        tvVidaMountro.text = "$porcentajeVidaMounstro%"
    }

    /**
     * Habilita el textview estado y le estable el texto
     * @param estado El texto que tendra el TextView
     */
    private fun mostrarMensajeEstadoPartida(estado: String){
        val tvEstado = findViewById<TextView>(R.id.tvEstadoPartida)
        tvEstado.text = estado
        tvEstado.visibility= View.VISIBLE
        deshabilitarBotones()
        mostrarBotonIrAPantallaPrincipal()
    }

    /**
     * Deshabilita la funcionalidad de los botonoes de Curar, Atacar y Rendirse
     */
    private fun deshabilitarBotones(){
        val btnRendirse = findViewById<Button>(R.id.btnRendirse)
        val btnCurar = findViewById<Button>(R.id.btnCurar)
        val btnAtacar = findViewById<Button>(R.id.btnAtacar)

        btnRendirse.isClickable = false
        btnCurar.isClickable = false
        btnAtacar.isClickable = false
    }

    /**
     * Aumenta una cantidad aleatoria de vida entre 10 y 25 al porcentajeVidaJugador
     */
    fun curarse(view: View?){
        val vidaRecuperar = (10 until 25).random()
        val descripcionMovimiento = "$descripcionRecuperarVida$vidaRecuperar%"
        val movimiento = Movimiento(TipoJugador.JUGADOR, descripcionMovimiento)
        movimientosAdapter.agregarMovimiento(movimiento)
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
        val movimiento = Movimiento(TipoJugador.JUGADOR, descripcionRendirse)
        movimientosAdapter.agregarMovimiento(movimiento)
        mostrarMensajeEstadoPartida("¡Perdiste!")
        refrescarElementosVidaDeJugador()
    }

    /**
     * Muestra el boton PantallaPrincipal
     */
    private fun mostrarBotonIrAPantallaPrincipal(){
        val btnPantallaPrincipal = findViewById<Button>(R.id.btnPantallaPrincipal)
        btnPantallaPrincipal.visibility = View.VISIBLE
    }

    fun clickBtnPantallaPrincipal(view: View?){
        val intento = Intent(this, MainActivity::class.java)
        startActivity(intento)
    }

    private fun iniciarRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.rvListaDeMovimientos)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        movimientosAdapter.RecyclerAdapter( this)
        recyclerView.adapter = movimientosAdapter
    }
}
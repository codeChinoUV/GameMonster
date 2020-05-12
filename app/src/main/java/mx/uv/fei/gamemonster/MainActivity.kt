package mx.uv.fei.gamemonster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Se encarga de cambiar de actividad si el nombre no esta vacio
     * @param view La vista de donde viene el evento
     */
    fun clickBtnComenzarJuego(view: View?){
        var etNombre = findViewById<EditText>(R.id.etNombre)
        var nombre = etNombre.text

        if (nombre.trim().isNotEmpty()){
            ocultarAlerta()
            mostrarActivityJuego(nombre.toString())
        }else{
            mostrarAlerta()
        }
    }

    /**
     * Cambia la actividad actual por la actividad JuegoActivity
     * @param nombreUsuario El nombre del usuario que se pasara a la otra activity
     */
    fun mostrarActivityJuego(nombreUsuario: String){
        val intento = Intent(this, JuegoActivity::class.java)
        intento.putExtra("nombreUsuario", nombreUsuario)
        startActivity(intento)
    }

    /**
     * Muestra la alerta nombre
     */
    fun mostrarAlerta(){
        var tvAlertaNombre = findViewById<TextView>(R.id.tvAlertaNombre)
        tvAlertaNombre.visibility = View.VISIBLE
    }

    /**
     * Oculta la alerta nombre
     */
    fun ocultarAlerta(){
        var tvAlertaNombre = findViewById<TextView>(R.id.tvAlertaNombre)
        tvAlertaNombre.visibility = View.GONE
    }
}

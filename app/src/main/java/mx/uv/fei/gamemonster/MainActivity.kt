package mx.uv.fei.gamemonster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickBtnComenzarJuego(view: View?){
        var etNombre = findViewById<EditText>(R.id.etNombre)

        var nombre = etNombre.text
        if (nombre.trim().isNotEmpty()){

        }
    }
}

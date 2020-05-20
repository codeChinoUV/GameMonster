package mx.uv.fei.gamemonster

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import mx.uv.fei.gamemonster.Models.Enums.TipoJugador
import mx.uv.fei.gamemonster.Models.Movimiento
import mx.uv.fei.gamemonster.R.*

class RecyclerAdapterMovimientos : RecyclerView.Adapter<RecyclerAdapterMovimientos.ViewHolder>(){
    var Movimentos: MutableList<Movimiento> = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapter(context:Context ){
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = Movimentos[position]
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(layout.movimiento_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return Movimentos.size
    }

    fun agregarMovimiento(movimiento: Movimiento){
        Movimentos.add(0, movimiento)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvMovimiento = view.findViewById<TextView>(id.tvmovimiento)

        fun bind(Movimiento: Movimiento, context:Context){
            tvMovimiento.text = Movimiento.movimiento
            if(Movimiento.tipoJugador.equals(TipoJugador.JUGADOR)){
                tvMovimiento.background = ColorDrawable(
                    ContextCompat.getColor(context,color.colorPrimary))
                tvMovimiento.setTextColor(ContextCompat.getColor(context,color.colorBlanco))
            }else{
                tvMovimiento.background = ColorDrawable(
                    ContextCompat.getColor(context,color.colorPrimaryDark))
                tvMovimiento.setTextColor(ContextCompat.getColor(context,color.colorBlanco))
            }
        }
    }
}
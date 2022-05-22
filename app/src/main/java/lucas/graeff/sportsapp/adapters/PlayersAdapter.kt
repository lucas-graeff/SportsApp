package lucas.graeff.sportsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso
import lucas.graeff.sportsapp.R
import lucas.graeff.sportsapp.models.Player


class PlayersAdapter(
    private val players: List<Player>,
    private val onItemClicked: (Player) -> Unit
) : RecyclerView.Adapter<PlayersAdapter.ViewHolder>() {

    class ViewHolder(view: View, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
        val layout: View = view.findViewById(R.id.layout)
        val imgView: ImageView = view.findViewById(R.id.img_thumbnail)
        val nameView: TextView = view.findViewById(R.id.txt_player_name)
        val sportView: Chip = view.findViewById(R.id.txt_sport)
        val teamView: TextView = view.findViewById(R.id.txt_team)

        init {
            layout.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_player, parent, false)
        return ViewHolder(view) { onItemClicked(players[it]) }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (players[position].strThumb != null) {
            Picasso.get().load(players[position].strThumb).into(holder.imgView)
        } else {
            holder.imgView.setImageResource(R.drawable.ic_person_150)
        }

        holder.nameView.text = players[position].strPlayer
        holder.sportView.text = players[position].strSport
        holder.teamView.text = players[position].strTeam

        //Set color of sport chip based off on string
//        holder.sportView.chipStrokeColor =
//            ColorStateList.valueOf(Color.parseColor(stringToColor(players[position].strSport)))


        holder.layout.setOnClickListener {
            onItemClicked(players[position])
        }

    }

    override fun getItemCount() = players.size

    private fun stringToColor(str: String): String {
        val hash = str.hashCode()
        return "#" + Integer.toHexString(hash shr 24 and 0xFF) +
                Integer.toHexString(hash shr 16 and 0xFF) +
                Integer.toHexString(hash shr 8 and 0xFF) +
                Integer.toHexString(hash and 0xFF)
    }
}
package com.example.beerapp.ui.list

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beerapp.databinding.ListItemBinding
import com.example.beerapp.model.dto.BeerDTO


class ListAdapter(
    private val dataSet: MutableList<BeerDTO>,
    private val onClick: (id: Int) -> Unit
) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val root: LinearLayout = binding.root
        val tvName: TextView = binding.tvName
        val tvYear: TextView = binding.tvYear
        val imageView: ImageView = binding.imageView
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(viewGroup.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvName.text = dataSet[position].name
        viewHolder.tvYear.text = dataSet[position].formattedYear
        Glide.with(viewHolder.imageView.context)
            .load(dataSet[position].image.toUri())
            .into(viewHolder.imageView)

        viewHolder.root.setOnClickListener {
            onClick(dataSet[position].id)
        }
    }

    override fun getItemCount() = dataSet.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateBeers(beers: List<BeerDTO>) {
        dataSet.clear()
        dataSet.addAll(beers)
        notifyDataSetChanged()
    }

}

package ru.tech.feature_main_screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.tech.feature_main_screen.data.remote.response.HomeStore
import ru.tech.feature_main_screen.databinding.HotSalesItemBinding

class HotSalesAdapter(
    private val context: Context,
    private val list: List<HomeStore>
) : RecyclerView.Adapter<HotSalesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HotSalesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            background.load(list[position].picture)
            title.text = list[position].title
            subtitle.text = list[position].subtitle
            new.visibility =
                if (list[position].is_new) VISIBLE
                else GONE
            buy.visibility =
                if (list[position].is_buy) VISIBLE
                else GONE
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class ViewHolder(binding: HotSalesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val background = binding.background
        val title = binding.title
        val subtitle = binding.subtitle
        val new = binding.neww
        val buy = binding.buy
    }

}
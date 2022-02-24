package ru.tech.feature_main_screen.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.tech.feature_main_screen.data.remote.response.BestSeller
import ru.tech.feature_main_screen.databinding.BestSellerItemBinding
import java.text.DecimalFormat

class BestSellersAdapter(private val context: Context, private val list: List<BestSeller>) :
    RecyclerView.Adapter<BestSellersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BestSellerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dec = DecimalFormat("#,###")
        holder.apply {
            if (list[position].is_favorites) favSwitcher.showNext()
            image.load(list[position].picture)
            discount.text = "$${dec.format(list[position].discount_price)}"
            noDiscount.text = "$${dec.format(list[position].price_without_discount)}"
            title.text = list[position].title
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(binding: BestSellerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.image
        val title = binding.title
        val discount = binding.discount
        val favSwitcher = binding.addToFav
        val noDiscount = binding.withoutDiscount

        init {
            noDiscount.paintFlags = noDiscount.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            favSwitcher.setOnClickListener { favSwitcher.showNext() }
        }
    }

}

package ru.tech.ecommerce.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.tech.ecommerce.R
import ru.tech.ecommerce.databinding.CategoryItemBinding
import ru.tech.ecommerce.extensions.Extensions.setTint
import ru.tech.ecommerce.viewModel.MainViewModel

class CategoryAdapter(
    private val context: Context,
    private val categoriesList: List<Pair<Int, Int>>,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = context.getString(categoriesList[position].first)
        if (position == viewModel.selectedItemPos)
            holder.selectedItem(position)
        else
            holder.unselectedItem(position)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    inner class ViewHolder(binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val text = binding.text
        private val imageView = binding.background

        init {
            itemView.setOnClickListener {
                viewModel.selectedItemPos = layoutPosition
                viewModel.lastItemSelectedPos = if (viewModel.lastItemSelectedPos == -1)
                    viewModel.selectedItemPos
                else {
                    notifyItemChanged(viewModel.lastItemSelectedPos)
                    viewModel.selectedItemPos
                }
                notifyItemChanged(viewModel.selectedItemPos)
            }
        }

        fun selectedItem(position: Int) {
            imageView.setTint(ContextCompat.getColor(context, R.color.white))
            imageView.setImageResource(categoriesList[position].second)
            imageView.setBackgroundResource(R.drawable.ellipse)
            text.setTextColor(ContextCompat.getColor(context, R.color.primary))
        }

        fun unselectedItem(position: Int) {
            imageView.setTint(ContextCompat.getColor(context, R.color.unchecked))
            imageView.setImageResource(categoriesList[position].second)
            imageView.setBackgroundResource(R.drawable.ellipse_white)
            text.setTextColor(ContextCompat.getColor(context, R.color.black))
        }
    }
}
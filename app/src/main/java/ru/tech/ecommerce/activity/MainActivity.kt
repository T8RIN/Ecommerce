package ru.tech.ecommerce.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.PagerSnapHelper
import dagger.hilt.android.AndroidEntryPoint
import ru.tech.ecommerce.R
import ru.tech.ecommerce.adapter.BestSellersAdapter
import ru.tech.ecommerce.adapter.CategoryAdapter
import ru.tech.ecommerce.adapter.HotSalesAdapter
import ru.tech.ecommerce.databinding.ActivityMainBinding
import ru.tech.ecommerce.fragment.ModalBottomSheet
import ru.tech.ecommerce.viewModel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Ecommerce)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.categoriesRecycler.adapter =
            CategoryAdapter(this, viewModel.getCategoriesList(), viewModel)

        viewModel.home.observe(this) {
            binding.hotSalesRecycler.adapter =
                HotSalesAdapter(this, viewModel.home.value!![0].home_store)
            binding.bestSellerRecycler.adapter =
                BestSellersAdapter(this, viewModel.home.value!![0].best_seller)
        }

        binding.filter.setOnClickListener {
            val modalBottomSheet = ModalBottomSheet()
            modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
        }

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.hotSalesRecycler)

    }

}
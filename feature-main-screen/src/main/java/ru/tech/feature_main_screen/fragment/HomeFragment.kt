package ru.tech.feature_main_screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
import dagger.hilt.android.AndroidEntryPoint
import ru.tech.feature_main_screen.adapter.BestSellersAdapter
import ru.tech.feature_main_screen.adapter.CategoryAdapter
import ru.tech.feature_main_screen.adapter.HotSalesAdapter
import ru.tech.feature_main_screen.databinding.FragmentHomeBinding
import ru.tech.feature_main_screen.viewModel.FeatureMainScreenViewModel

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FeatureMainScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoriesRecycler.adapter =
            CategoryAdapter(
                requireContext(),
                viewModel.getCategoriesList(),
                viewModel
            )

        viewModel.home.observe(requireActivity()) {
            binding.hotSalesRecycler.adapter =
                HotSalesAdapter(
                    requireContext(),
                    viewModel.home.value!![0].home_store
                )
            binding.bestSellerRecycler.adapter =
                BestSellersAdapter(
                    requireContext(),
                    viewModel.home.value!![0].best_seller
                )
        }

        binding.filter.setOnClickListener {
            val modalBottomSheet = ModalBottomSheet()
            modalBottomSheet.show(requireActivity().supportFragmentManager, ModalBottomSheet.TAG)
        }

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.hotSalesRecycler)

    }

}
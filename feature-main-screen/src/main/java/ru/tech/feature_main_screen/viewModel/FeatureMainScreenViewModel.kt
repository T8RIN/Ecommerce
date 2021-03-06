package ru.tech.feature_main_screen.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.tech.feature_main_screen.R
import ru.tech.feature_main_screen.data.remote.response.Home
import ru.tech.feature_main_screen.data.repository.EcommerceRepository
import ru.tech.feature_main_screen.utils.Resource
import javax.inject.Inject

@HiltViewModel
class FeatureMainScreenViewModel @Inject constructor(
    private val repository: EcommerceRepository
) : ViewModel() {

    var isLoading = false
    var errorMessage = ""

    var selectedItemPos = 0
    var lastItemSelectedPos = 0


    val home: MutableLiveData<Home> by lazy {
        MutableLiveData()
    }

    fun getCategoriesList(): List<Pair<Int, Int>> {
        return listOf(
            Pair(R.string.phones, R.drawable.phone),
            Pair(R.string.computer, R.drawable.computer),
            Pair(R.string.health, R.drawable.health),
            Pair(R.string.books, R.drawable.books)
        )
    }

    init {
        getHome()
    }

    private fun getHome() {
        viewModelScope.launch {
            isLoading = true
            when (val result = repository.getHome()) {
                is Resource.Success -> {
                    home.value = result.data
                    errorMessage = ""
                }
                else -> {
                    errorMessage = result.message.toString()
                }
            }
            isLoading = false
        }
    }
}
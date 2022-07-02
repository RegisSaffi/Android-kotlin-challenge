package com.regis.weather.viewModel

import androidx.lifecycle.*
import com.regis.weather.model.Weather
import com.regis.weather.repository.DbRepository
import kotlinx.coroutines.launch

class DbViewModel(private val repository: DbRepository) : ViewModel() {

    val allRecords: LiveData<List<Weather>> = repository.allRecords.asLiveData()
    val latest: LiveData<List<Weather>> = repository.latest.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(weather: Weather) = viewModelScope.launch {
        repository.insert(weather)
    }
}

class DbViewModelFactory(private val repository: DbRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DbViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DbViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
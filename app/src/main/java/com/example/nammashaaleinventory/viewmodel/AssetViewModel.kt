package com.example.nammashaaleinventory.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.nammashaaleinventory.data.local.AppDatabase
import com.example.nammashaaleinventory.data.model.Asset
import com.example.nammashaaleinventory.data.repository.AssetRepository
import kotlinx.coroutines.launch

class AssetViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AssetRepository
    val allAssets: LiveData<List<Asset>>

    init {
        val dao = AppDatabase.getDatabase(application).assetDao()
        repository = AssetRepository(dao)
        allAssets = repository.allAssets
    }

    fun insert(asset: Asset) = viewModelScope.launch {
        repository.insert(asset)
    }

    fun update(asset: Asset) = viewModelScope.launch {
        repository.update(asset)
    }

    fun delete(asset: Asset) = viewModelScope.launch {
        repository.delete(asset)
    }
}
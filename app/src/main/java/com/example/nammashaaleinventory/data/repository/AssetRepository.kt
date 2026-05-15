package com.example.nammashaaleinventory.data.repository

import com.example.nammashaaleinventory.data.local.AssetDao
import com.example.nammashaaleinventory.data.model.Asset

class AssetRepository(private val dao: AssetDao) {

    val allAssets = dao.getAllAssets()

    suspend fun insert(asset: Asset) {
        dao.insertAsset(asset)
    }

    suspend fun update(asset: Asset) {
        dao.updateAsset(asset)
    }

    suspend fun delete(asset: Asset) {
        dao.deleteAsset(asset)
    }
}
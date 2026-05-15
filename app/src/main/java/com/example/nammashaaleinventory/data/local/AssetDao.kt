package com.example.nammashaaleinventory.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nammashaaleinventory.data.model.Asset

@Dao
interface AssetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsset(asset: Asset)

    @Update
    suspend fun updateAsset(asset: Asset)

    @Delete
    suspend fun deleteAsset(asset: Asset)

    @Query("SELECT * FROM assets ORDER BY name ASC")
    fun getAllAssets(): LiveData<List<Asset>>
}
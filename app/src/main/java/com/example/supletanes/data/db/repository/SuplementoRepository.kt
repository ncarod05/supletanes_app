package com.example.supletanes.data.db.repository

import com.example.supletanes.data.db.dao.SuplementoDao
import com.example.supletanes.data.db.entities.Suplemento
import kotlinx.coroutines.flow.Flow

class SuplementoRepository(private val suplementoDao: SuplementoDao) {
    // Exponer el Flow directamente desde el DAO. Esto es reactivo.
    val allSupplements: Flow<List<Suplemento>> = suplementoDao.getAllSupplements()

    suspend fun insert(supplement: Suplemento) {
        suplementoDao.insertSupplement(supplement)
    }

    suspend fun delete(id: Int) {
        suplementoDao.deleteSupplementById(id)
    }
}
package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data

import android.content.Context
import kotlinx.coroutines.flow.Flow

class LocalRepository(private val stockDao: StockDao): StockRepository {
    override fun obtenerStockTodo(): Flow<List<Producto>> {
        return stockDao.obtenerStockTodo()
    }

    override fun obtenerStockPorId(id: Int): Flow<Producto?> {
        return stockDao.obtenerStockPorId(id)
    }

    override suspend fun agregarStock(producto: Producto) {
        return stockDao.agregarStock(producto)
    }

    override suspend fun eliminarStock(producto: Producto) {
        return stockDao.eliminarStock(producto)
    }

    override suspend fun actualizarStock(producto: Producto) {
        return stockDao.actualizarStock(producto)
    }
}
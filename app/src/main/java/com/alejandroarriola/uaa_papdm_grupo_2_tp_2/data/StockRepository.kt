package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data

import kotlinx.coroutines.flow.Flow

interface StockRepository {
    fun obtenerStockTodo(): Flow<List<Producto>>
    fun obtenerStockPorId(id: Int): Flow<Producto?>
    suspend fun agregarStock(producto: Producto)
    suspend fun eliminarStock(producto: Producto)
    suspend fun actualizarStock(producto: Producto)
}
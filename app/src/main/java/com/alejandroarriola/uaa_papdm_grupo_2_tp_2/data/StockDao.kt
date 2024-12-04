package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarStock(producto: Producto)

    @Update
    suspend fun actualizarStock(producto: Producto)

    @Delete
    suspend fun eliminarStock(producto: Producto)

    @Query("SELECT * from productos WHERE id = :id")
    fun obtenerStockPorId(id: Int): Flow<Producto>

    @Query("SELECT * from productos ORDER BY nombre ASC")
    fun obtenerStockTodo(): Flow<List<Producto>>
}
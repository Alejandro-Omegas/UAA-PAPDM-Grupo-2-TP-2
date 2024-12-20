package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class Producto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val precio: Double,
    val cantidad: Int,
    val detalle: String?
)
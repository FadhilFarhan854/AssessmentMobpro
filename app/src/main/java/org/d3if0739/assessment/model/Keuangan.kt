package org.d3if0739.assessment.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "keuangan")
data class Keuangan(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val tanggal : String,
    val jenis: String,
    val jumlah : String
)

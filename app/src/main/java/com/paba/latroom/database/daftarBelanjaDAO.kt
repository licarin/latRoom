package com.paba.latroom.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface daftarBelanjaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(daftar: daftarbelanja)

    @Query("UPDATE daftarbelanja SET tanggal=:isi_tanggal, item=:isi_item, jumlah=:isi_jumlah WHERE id=:pilihid")
    fun update(isi_tanggal: String, isi_item: String, isi_jumlah: String, isi_status: Int, pilihid: Int)

    @Delete
    fun delete(daftar: daftarbelanja)

    @Query("SELECT * FROM daftarbelanja ORDER BY id ASC")
    fun selectAll(): MutableList<daftarbelanja>
}
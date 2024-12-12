package com.paba.latroom

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paba.latroom.database.daftarbelanja

class adapterDaftar (private val daftarBelanja : MutableList<daftarbelanja>):
    RecyclerView.Adapter<adapterDaftar.ListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): adapterDaftar.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list, parent,
            false)
        return ListViewHolder(view)
    }
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
            fun delData(dtBelanja: daftarbelanja)
            fun done(dtBelanja: daftarbelanja)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun getItemCount(): Int {
        return daftarBelanja.size
    }

    override fun onBindViewHolder(holder: adapterDaftar.ListViewHolder, position: Int) {
        var daftar = daftarBelanja[position]

        holder._tvTanggal.setText(daftar.tanggal)
        holder._tvItemBarang.setText(daftar.item)
        holder._tvJumlahBarang.setText(daftar.jumlah)

        holder._tvBtnEdit.setOnClickListener{
            val intent = Intent(it.context, TambahDaftar::class.java)
            intent.putExtra("id", daftar.id)
            intent.putExtra("addEdit", 1)
            it.context.startActivity(intent)
        }

        holder._tvBtnDelete.setOnClickListener {
            onItemClickCallback.delData(daftar)
        }

        holder._tvBtnDone.setOnClickListener {
            onItemClickCallback.done(daftar)
        }

    }

    inner class ListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var _tvItemBarang = itemView.findViewById<TextView>(R.id.itemBarang)
        var _tvJumlahBarang = itemView.findViewById<TextView>(R.id.jumlahText)
        var _tvTanggal = itemView.findViewById<TextView>(R.id.tanggalTv)

        var _tvBtnEdit = itemView.findViewById<ImageButton>(R.id.editBtn)
        var _tvBtnDelete = itemView.findViewById<ImageButton>(R.id.deleteBtn)
        var _tvBtnDone  = itemView.findViewById<ImageButton>(R.id.doneBtn)
 }

    fun isiData(daftar: List<daftarbelanja>){
        daftarBelanja.clear()
        daftarBelanja.addAll(daftar)
        notifyDataSetChanged()
    }

}
package com.morbis.spe.view.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.morbis.spe.R
import com.morbis.spe.data.remote.baseImage
import com.morbis.spe.model.JadwalDokterItem
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_dokter.view.*
import retrofit2.adapter.rxjava2.Result.response
import java.util.*


class JadwalDokterAdapter() : RecyclerView.Adapter<JadwalDokterAdapter.IViewHolder>(), Filterable {

    private val onClick = PublishSubject.create<JadwalDokterItem>()
    private var models: MutableList<JadwalDokterItem> = ArrayList()
    var modelList: MutableList<JadwalDokterItem>

    init {
        modelList = models
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IViewHolder {
        return IViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_dokter,
                parent,
                false
            )
        )
    }

    override fun getFilter(): Filter {

        return object : Filter() {
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                modelList = p1!!.values as MutableList<JadwalDokterItem>
                notifyDataSetChanged()
            }

            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charString: String = p0.toString()
                modelList = if (charString.isEmpty()) {
                    models
                } else {
                    val filteredList: MutableList<JadwalDokterItem> = mutableListOf()
                    for (s: JadwalDokterItem in models) {
                        when {
                            s.dokter!!.toLowerCase().contains(charString.toLowerCase()) -> filteredList.add(s)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = modelList
                return filterResults
            }
        }
    }

    override fun getItemCount(): Int = modelList.size
    override fun getItemViewType(position: Int): Int = super.getItemViewType(position)

    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        if (modelList.size > 0) {
            val item = modelList[position]
            holder.bindModel(item)
        }
    }

    fun setData(data: MutableList<JadwalDokterItem>) {
        if (models.size >= 0) {
            models.clear()
        }
        models.addAll(data)
        notifyDataSetChanged()
    }

    val clickEvent: Observable<JadwalDokterItem> = onClick

    inner class IViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onClick.onNext(modelList[layoutPosition])
            }
        }

        fun bindModel(model: JadwalDokterItem) {
            itemView.textNamaDokter.text = model.dokter
            itemView.textItemJam.text = "Jam ${model.jadwalPraktek}"
            itemView.textItemKehadiran.text = model.kehadiran
            itemView.textItemPoli.text = model.poli
            Glide.with(itemView.context).load("$baseImage${model.foto}").asBitmap().centerCrop()
                .placeholder(R.drawable.ic_dokter_praktik)
                .into(object : BitmapImageViewTarget(itemView.imgFotoDokter) {
                    override fun setResource(resource: Bitmap?) {
                        super.setResource(resource)
                        val circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(itemView.context.getResources(), resource)
                        circularBitmapDrawable.isCircular = true
                        circularBitmapDrawable.cornerRadius = 21f
                        itemView.imgFotoDokter.setImageDrawable(circularBitmapDrawable)
                    }
                })
        }
    }
}
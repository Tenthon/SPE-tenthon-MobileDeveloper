package com.morbis.spe.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ResponseJadwalDokter(

    @field:SerializedName("data")
    val data: List<JadwalDokterItem>? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: Boolean? = null
)

data class JadwalDokterItem(

    @field:SerializedName("no")
    val no: Int? = null,

    @field:SerializedName("dokter")
    val dokter: String? = null,

    @field:SerializedName("foto")
    val foto: String? = null,

    @field:SerializedName("kehadiran")
    val kehadiran: String? = null,

    @field:SerializedName("jadwal_praktek")
    val jadwalPraktek: String? = null,

    @field:SerializedName("poli")
    val poli: String? = null,

    @field:SerializedName("userid_zoom")
    val userid_zoom: String? = null,

    @field:SerializedName("biaya")
    val biaya: String? = null,

    @field:SerializedName("id_jadwal")
    val idJadwal: String? = null,


    @field:SerializedName("id_meeting")
    val id_meeting: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(no)
        parcel.writeString(dokter)
        parcel.writeString(kehadiran)
        parcel.writeString(jadwalPraktek)
        parcel.writeString(poli)
        parcel.writeString(userid_zoom)
        parcel.writeString(id_meeting)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<JadwalDokterItem> {
        override fun createFromParcel(parcel: Parcel): JadwalDokterItem {
            return JadwalDokterItem(parcel)
        }

        override fun newArray(size: Int): Array<JadwalDokterItem?> {
            return arrayOfNulls(size)
        }
    }
}
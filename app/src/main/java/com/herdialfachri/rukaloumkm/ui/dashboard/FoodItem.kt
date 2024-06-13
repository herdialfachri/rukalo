package com.herdialfachri.rukaloumkm.ui.dashboard

import android.os.Parcel
import android.os.Parcelable

data class FoodItem(
    var dataImage: Int,
    var dataTitle: String,
    var dataDesc: String,
    var dataWaktu: String,
    var dataRating: String,
    var dataBahan: String,
    var dataResep: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(dataImage)
        parcel.writeString(dataTitle)
        parcel.writeString(dataDesc)
        parcel.writeString(dataWaktu)
        parcel.writeString(dataRating)
        parcel.writeString(dataBahan)
        parcel.writeString(dataResep)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FoodItem> {
        override fun createFromParcel(parcel: Parcel): FoodItem {
            return FoodItem(parcel)
        }

        override fun newArray(size: Int): Array<FoodItem?> {
            return arrayOfNulls(size)
        }
    }
}
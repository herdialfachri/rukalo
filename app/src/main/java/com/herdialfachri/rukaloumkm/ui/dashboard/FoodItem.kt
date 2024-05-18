package com.herdialfachri.rukaloumkm.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodItem(
    val name: String,
    val description: String,
    val imageUrl: String
) : Parcelable

package com.jose.demanadashadow.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DemandaShadowModel(
    val id:String?,
    val email:String?,
    val name:String?
):Parcelable
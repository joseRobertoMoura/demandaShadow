package com.jose.demanadashadow.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class DemandaShadowModel(
    @SerializedName("id") val id:String?,
    @SerializedName("email") val email:String?,
    @SerializedName("name") val name:String?
):Parcelable
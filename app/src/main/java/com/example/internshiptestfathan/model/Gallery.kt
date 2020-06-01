package com.example.internshiptestfathan.model

import android.os.Parcel
import android.os.Parcelable

data class Gallery (
    var caption : String? = null,
    var tumbnail : String? = null,
    var image : String? = null ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(caption)
        parcel.writeString(tumbnail)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Gallery> {
        override fun createFromParcel(parcel: Parcel): Gallery {
            return Gallery(parcel)
        }

        override fun newArray(size: Int): Array<Gallery?> {
            return arrayOfNulls(size)
        }
    }
}
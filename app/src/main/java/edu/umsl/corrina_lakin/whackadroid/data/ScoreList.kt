package edu.umsl.corrina_lakin.whackadroid.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_list")
data class ScoreList(
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0,
    var username: String,
    var score: Long,
    var misses: Long
): Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(username)
        parcel.writeLong(score)
        parcel.writeLong(misses)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ScoreList> {
        override fun createFromParcel(parcel: Parcel): ScoreList {
            return ScoreList(parcel)
        }

        override fun newArray(size: Int): Array<ScoreList?> {
            return arrayOfNulls(size)
        }
    }
}

data class ScoreListInfo (
    @Embedded
    var scoreList: ScoreList
)

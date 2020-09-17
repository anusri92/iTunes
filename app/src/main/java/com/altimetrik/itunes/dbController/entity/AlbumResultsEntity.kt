package com.altimetrik.itunes.dbController.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "results",indices = [Index(value = ["trackId"], unique = true)])
data class AlbumResultsEntity(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo
        var uniqueIdentityKey: Long? = null,

        @ColumnInfo
        @SerializedName("artworkUrl100") var artworkUrl100: String? = null,
        @ColumnInfo
        @SerializedName("trackTimeMillis") var trackTimeMillis: Long? = null,
        @ColumnInfo
        @SerializedName("longDescription") var longDescription: String? = null,
        @ColumnInfo
        @SerializedName("trackHdRentalPrice") var trackHdRentalPrice: Double? = null,
        @ColumnInfo
        @SerializedName("country") var country: String? = null,
        @ColumnInfo
        @SerializedName("previewUrl") var previewUrl: String? = null,
        @ColumnInfo
        @SerializedName("collectionArtistId") var collectionArtistId: Long? = null,
        @ColumnInfo
        @SerializedName("collectionHdPrice") var collectionHdPrice: Double? = null,
        @ColumnInfo
        @SerializedName("hasITunesExtras") var hasITunesExtras: Boolean? = null,
        @ColumnInfo
        @SerializedName("trackName") var trackName: String? = null,
        @ColumnInfo
        @SerializedName("collectionArtistViewUrl") var collectionArtistViewUrl: String? = null,
        @ColumnInfo
        @SerializedName("collectionName") var collectionName: String? = null,
        @ColumnInfo
        @SerializedName("discNumber") var discNumber: Long? = null,
        @ColumnInfo
        @SerializedName("trackCount") var trackCount: Long? = null,
        @ColumnInfo
        @SerializedName("artworkUrl30") var artworkUrl30: String? = null,
        @ColumnInfo
        @SerializedName("wrapperType") var wrapperType: String? = null,
        @ColumnInfo
        @SerializedName("currency") var currency: String? = null,
        @ColumnInfo
        @SerializedName("collectionId") var collectionId: Long? = null,
        @ColumnInfo
        @SerializedName("trackExplicitness") var trackExplicitness: String? = null,
        @ColumnInfo
        @SerializedName("collectionViewUrl") var collectionViewUrl: String? = null,
        @ColumnInfo
        @SerializedName("trackHdPrice") var trackHdPrice: Double? = null,
        @ColumnInfo
        @SerializedName("contentAdvisoryRating") var contentAdvisoryRating: String? = null,
        @ColumnInfo
        @SerializedName("trackNumber") var trackNumber: Long? = null,
        @ColumnInfo
        @SerializedName("releaseDate") var releaseDate: String? = null,
        @ColumnInfo
        @SerializedName("kind") var kind: String? = null,
        @ColumnInfo
        @SerializedName("trackId") var trackId: Long? = null,
        @ColumnInfo
        @SerializedName("trackRentalPrice") var trackRentalPrice: Double? = null,
        @ColumnInfo
        @SerializedName("collectionPrice") var collectionPrice: Double? = null,
        @ColumnInfo
        @SerializedName("shortDescription") var shortDescription: String? = null,
        @ColumnInfo
        @SerializedName("discCount") var discCount: Long? = null,
        @ColumnInfo
        @SerializedName("primaryGenreName") var primaryGenreName: String? = null,
        @ColumnInfo
        @SerializedName("trackPrice") var trackPrice: Double? = null,
        @ColumnInfo
        @SerializedName("collectionExplicitness") var collectionExplicitness: String? = null,
        @ColumnInfo
        @SerializedName("trackViewUrl") var trackViewUrl: String? = null,
        @ColumnInfo
        @SerializedName("artworkUrl60") var artworkUrl60: String? = null,
        @ColumnInfo
        @SerializedName("trackCensoredName") var trackCensoredName: String? = null,
        @ColumnInfo
        @SerializedName("artistName") var artistName: String? = null,
        @ColumnInfo
        @SerializedName("collectionCensoredName") var collectionCensoredName: String? = null,
        @ColumnInfo
        @SerializedName("myFav") var myFav: Boolean = false,

) {

}










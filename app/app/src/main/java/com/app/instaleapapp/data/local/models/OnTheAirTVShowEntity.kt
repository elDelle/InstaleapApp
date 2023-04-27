package com.app.instaleapapp.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.instaleapapp.domain.model.TVShow

@Entity
class OnTheAirTVShowEntity {
    @PrimaryKey
    var id: Int? = null

    @ColumnInfo("title")
    var title: String? = null

    @ColumnInfo("poster")
    var poster: String? = null
}

fun OnTheAirTVShowEntity.toDomain(): TVShow {
    return TVShow(
        this.id,
        this.title,
        this.poster
    )
}


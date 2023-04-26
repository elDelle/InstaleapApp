package com.app.instaleapapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.instaleapapp.domain.model.TVShow

@Entity
class PopularTVShowEntity {
    @PrimaryKey
    var id: Int? = null

    @ColumnInfo("title")
    var title: String? = null

    @ColumnInfo("poster")
    var poster: String? = null
}

fun PopularTVShowEntity.toDomain(): TVShow {
    return TVShow(
        this.id,
        this.title,
        this.poster
    )
}


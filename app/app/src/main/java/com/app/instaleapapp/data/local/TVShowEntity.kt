package com.app.instaleapapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.instaleapapp.domain.model.TVShow

@Entity
class TVShowEntity {
    @PrimaryKey
    var id: Int? = null

    @ColumnInfo("title")
    var title: String? = null

    @ColumnInfo("poster")
    var poster: String? = null

    @ColumnInfo("idCategory")
    var idCategory: Int? = null
}

fun TVShowEntity.toDomain(): TVShow {
    return TVShow(
        this.id,
        this.title,
        this.poster
    )
}


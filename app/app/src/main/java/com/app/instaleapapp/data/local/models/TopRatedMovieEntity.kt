package com.app.instaleapapp.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.instaleapapp.domain.model.Movie

@Entity
class TopRatedMovieEntity {
    @PrimaryKey
    var id: Int? = null

    @ColumnInfo("title")
    var title: String? = null

    @ColumnInfo("poster")
    var poster: String? = null
}

fun TopRatedMovieEntity.toDomain(): Movie {
    return Movie(
        this.id,
        this.title,
        this.poster
    )
}


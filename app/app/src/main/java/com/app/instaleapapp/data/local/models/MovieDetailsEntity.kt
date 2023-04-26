package com.app.instaleapapp.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.instaleapapp.domain.model.MovieDetails

@Entity
class MovieDetailsEntity {
    @PrimaryKey
    var id: Int? = null

    @ColumnInfo("title")
    var title: String? = null

    @ColumnInfo("overview")
    var overview: String? = null

    @ColumnInfo("poster")
    var poster: String? = null

    @ColumnInfo("idMovie")
    var idMovie: Int? = null
}

fun MovieDetailsEntity.toDomain(): MovieDetails {
    return MovieDetails(
        this.id,
        this.title,
        this.overview,
        this.poster
    )
}


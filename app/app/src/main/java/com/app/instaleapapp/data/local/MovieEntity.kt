package com.app.instaleapapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.instaleapapp.domain.model.Movie

@Entity
class MovieEntity {
    @PrimaryKey
    var id: Int? = null

    @ColumnInfo("name")
    var title: String? = null

    @ColumnInfo("phone")
    var poster: String? = null

    @ColumnInfo("idCategory")
    var idCategory: Int? = null
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        this.id,
        this.title,
        this.poster
    )
}


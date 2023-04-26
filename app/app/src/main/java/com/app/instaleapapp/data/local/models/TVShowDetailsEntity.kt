package com.app.instaleapapp.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.instaleapapp.domain.model.TVShowDetails

@Entity
class TVShowDetailsEntity {
    @PrimaryKey
    var id: Int? = null

    @ColumnInfo("originalName")
    var originalName: String? = null

    @ColumnInfo("overview")
    var overview: String? = null

    @ColumnInfo("poster")
    var poster: String? = null

    @ColumnInfo("idTVShow")
    var idTVShow: Int? = null
}

fun TVShowDetailsEntity.toDomain(): TVShowDetails {
    return TVShowDetails(
        this.id,
        this.originalName,
        this.overview,
        this.poster
    )
}


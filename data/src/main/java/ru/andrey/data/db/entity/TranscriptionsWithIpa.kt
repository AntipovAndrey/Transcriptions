package ru.andrey.data.db.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

data class TranscriptionsWithIpa(

    @Embedded
    var transcriptionData: TranscriptionData,
    @Relation(
        parentColumn = "id",
        entity = IpaData::class,
        entityColumn = "transcriptionId"
    )
    var ipas: List<IpaData>
)
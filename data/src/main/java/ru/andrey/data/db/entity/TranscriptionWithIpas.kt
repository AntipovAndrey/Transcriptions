package ru.andrey.data.db.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

class TranscriptionWithIpas {

    @Embedded
    lateinit var transcriptionData: TranscriptionData

    @Relation(
        parentColumn = "id",
        entity = IpaData::class,
        entityColumn = "transcriptionId"
    )
    lateinit var ipas: List<IpaData>
}
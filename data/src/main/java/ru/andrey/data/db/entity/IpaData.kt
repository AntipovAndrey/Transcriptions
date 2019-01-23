package ru.andrey.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(
    tableName = "ipa",
    foreignKeys = [ForeignKey(
        entity = TranscriptionData::class,
        parentColumns = ["id"],
        childColumns = ["transcriptionId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class IpaData(

    @PrimaryKey
    var ipa: String,
    var transcriptionId: Int
)

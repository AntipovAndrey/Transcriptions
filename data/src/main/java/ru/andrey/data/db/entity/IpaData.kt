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
class IpaData(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var ipa: String,
    var order: Int = 0,
    var transcriptionId: Int = 0
)

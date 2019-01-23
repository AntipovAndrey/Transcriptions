package ru.andrey.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "transcriptions")
class TranscriptionData(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var word: String,
    var variant: Int
)

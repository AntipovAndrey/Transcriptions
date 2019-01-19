package ru.andrey.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "transcriptions")
data class TranscriptionData(

    @PrimaryKey
    var word: String,
    var priority: Int,
    var ipa: String,
    var weak: Int,
    var pos: String, // ??
    var context: String?,
    var flexible: Boolean
)



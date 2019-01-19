package ru.andrey.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.ColumnInfo.NOCASE
import android.arch.persistence.room.Entity

@Entity(tableName = "transcriptions", primaryKeys = ["word", "priority"])
data class TranscriptionData(

    @ColumnInfo(collate = NOCASE)
    var word: String,
    var priority: Int,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var ipa: ByteArray,
    var weak: Int?,
    @ColumnInfo(collate = NOCASE)
    var pos: String?, // ??
    @ColumnInfo(collate = NOCASE)
    var context: String?,
    var flexible: Boolean
)
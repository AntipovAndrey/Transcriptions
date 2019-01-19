package ru.andrey.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity


@Entity(tableName = "transcriptions_us", primaryKeys = ["word", "priority"])
data class TranscriptionUsData(

    @ColumnInfo(collate = ColumnInfo.NOCASE)
    var word: String,
    var priority: Int,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var ipa: ByteArray,
    var weak: Int?,
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    var pos: String?, // ??
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    var context: String?,
    var flexible: Boolean
)

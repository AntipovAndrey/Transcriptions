package ru.andrey.data.db.converter

import android.arch.persistence.room.TypeConverter

class TranscriptionDataConverter {

    @TypeConverter
    fun toBlob(string: String) = string.toByteArray()

    @TypeConverter
    fun fromBlob(blob: Array<Byte>) = blob.toString()
}
package ru.andrey.data.db.copier

import android.content.Context
import java.io.FileOutputStream


class DatabaseCopier constructor(val context: Context) {

    fun copy() {
        copyAttachedDatabase(context, "transcription.db")
    }

    private fun copyAttachedDatabase(context: Context, databaseName: String) {
        val dbPath = context.getDatabasePath(databaseName)

        if (dbPath.exists()) {
            return
        }

        dbPath.parentFile.mkdirs()

        val inputStream = context.assets.open("database/$databaseName")
        val output = FileOutputStream(dbPath)

        inputStream.copyTo(output, 8192)

        output.flush()
        output.close()
        inputStream.close()
    }
}
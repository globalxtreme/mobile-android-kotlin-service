package com.globalxtreme.gxtechnician.helper.base

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.FileUtils
import androidx.annotation.RequiresApi
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class FileDescriptorHelper(context: Context) {
    val contentResolver = context.contentResolver

    fun getBitmapFromUri(uri: Uri): Bitmap {
        val parcelFileDescriptor = contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor = parcelFileDescriptor?.fileDescriptor
        val image: Bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor?.close()
        return image
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun getFileFromUri(uri: Uri): File {
        val file: File = File.createTempFile("temp", "file")
        val parcelFileDescriptor = contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor = parcelFileDescriptor?.fileDescriptor
        val tes = FileInputStream(fileDescriptor)
        val out = FileOutputStream(file)
        FileUtils.copy(tes, out)

        return file
    }
}
package com.globalxtreme.gxtechnician.helper.base

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.provider.MediaStore
import java.io.ByteArrayOutputStream

class ImageHelper {
    fun preventRotate(mCurrentPhotoPath: String, bitmap: Bitmap): Bitmap {
        val ei = ExifInterface(mCurrentPhotoPath)
        val orientation: Int = ei.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_UNDEFINED
        )

        val rotatedBitmap: Bitmap? = when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmap, 90F)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitmap, 180F)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitmap, 270F)
            ExifInterface.ORIENTATION_NORMAL -> bitmap
            else -> bitmap
        }

        return rotatedBitmap!!
    }

    private fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(
            source, 0, 0, source.width, source.height,
            matrix, true
        )
    }

    private fun getFileDataFromDrawable(bitmap: Bitmap): ByteArray? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
        return byteArrayOutputStream.toByteArray()
    }

    //=== Function for resized Image ===//
    private fun getResizedBitmap(image: Bitmap?, maxSize: Int): Bitmap {
        var width = image!!.width
        var height = image.height

        return if (width <= maxSize && height <= maxSize) {
            Bitmap.createScaledBitmap(image, width, height, true)
        } else {
            val bitmapRatio = width.toFloat() / height.toFloat()
            if (bitmapRatio > 1) {
                width = maxSize
                height = (width / bitmapRatio).toInt()
            } else {
                height = maxSize
                width = (height * bitmapRatio).toInt()
            }
            Bitmap.createScaledBitmap(image, width, height, true)
        }
    }

    fun getRealPathFromURI(context: Context, contentUri: Uri?): String? {
        var path: String? = null
        val proj = arrayOf(MediaStore.MediaColumns.DATA)
        val cursor: Cursor? = context.contentResolver.query(contentUri!!, proj, null, null, null)
        if (cursor?.moveToFirst() == true) {
            val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            path = cursor.getString(column_index)
        }
        cursor?.close()
        return path
    }

    fun getDataFromUri(context: Context, uri: String): ByteArray? {
        val imageStream = context.contentResolver.openInputStream(Uri.parse(uri))
        val selectedImage = BitmapFactory.decodeStream(imageStream)
        val resImage = ImageHelper().getResizedBitmap(selectedImage, 1000)

        return resImage.let { ImageHelper().getFileDataFromDrawable(it) }
    }
}
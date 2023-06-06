package com.caps.rempasi.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

object ImageHelper {
    const val FILENAME_FORMAT = "dd-MMM-yyyy"

    private const val MAXIMAL_SIZE = 1000000

    private val timeStamp: String = SimpleDateFormat(
        FILENAME_FORMAT,
        Locale.US
    ).format(System.currentTimeMillis())

    fun createCustomTempFile(context: Context): File {
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(timeStamp, ".jpg", storageDir)
    }

    fun Uri.toFile(context: Context): File {
        val contentResolver = context.contentResolver
        val myFile = createCustomTempFile(context)

        val inputStream = contentResolver.openInputStream(this) as InputStream
        val outputStream = FileOutputStream(myFile)
        val buf = ByteArray(1024)
        var len: Int
        while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
        outputStream.close()
        inputStream.close()

        return myFile
    }

    fun resizeImage(context: Context, uri: Uri): File? {
        try {
            // Load the original image from the Uri
            val inputStream = context.contentResolver.openInputStream(uri)
            val originalBitmap = BitmapFactory.decodeStream(inputStream)

            // Calculate the target dimensions for the resized image
            val targetWidth = originalBitmap.height * 3 / 4
            val targetHeight = originalBitmap.height

            // Create a new Bitmap with the target dimensions
            val resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, targetWidth, targetHeight, false)

            // Create a new File to save the resized image
            val resizedFile = File(context.cacheDir, "resized_image.jpg")
            val outputStream = FileOutputStream(resizedFile)

            // Compress the resized Bitmap into the FileOutputStream
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)

            // Close the streams
            inputStream?.close()
            outputStream.close()

            return resizedFile
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun reduceFileImage(file: File): File {
        val bitmap = BitmapFactory.decodeFile(file.path)
        var compressQuality = 100
        var streamLength: Int
        do {
            val bmpStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
            val bmpPicByteArray = bmpStream.toByteArray()
            streamLength = bmpPicByteArray.size
            compressQuality -= 5
        } while (streamLength > MAXIMAL_SIZE)
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(file))
        return file
    }
}
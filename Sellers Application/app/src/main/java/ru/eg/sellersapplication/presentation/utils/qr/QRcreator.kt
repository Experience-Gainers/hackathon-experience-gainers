package ru.eg.sellersapplication.presentation.utils.qr

import android.graphics.Bitmap
import android.util.Log
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import ru.eg.sellersapplication.presentation.utils.constants.QR_CREATE_TAG
import ru.eg.sellersapplication.presentation.utils.model.BitmapResult

fun qrCreator(content: String, width: Int, height: Int): BitmapResult {
    return try {
        val barcodeEncoder = BarcodeEncoder()

        val bitmap = barcodeEncoder.encodeBitmap(
            content,
            BarcodeFormat.QR_CODE,
            width,
            height
        )

        BitmapResult(bitmap = bitmap, isCreated = true)
    } catch (e: Throwable) {
        Log.d(QR_CREATE_TAG, e.localizedMessage!!)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        BitmapResult(bitmap = bitmap, isCreated = false)
    }
}
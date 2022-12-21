package com.asmlnk.android.asmlnk.worktpp_7

import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.io.File

private const val ARG_PHOTO = "photo"

class PhotoDialogDefect: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val photoFile = arguments?.getSerializable(ARG_PHOTO) as File
        val bitmap = BitmapFactory.decodeFile(photoFile.path)
        val matrix = Matrix()

        if (bitmap.height < bitmap.width) {
            matrix.postRotate(90F)
        } else {
            matrix.postRotate(0F)
        }

        val b = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        val dialog = AlertDialog.Builder(requireActivity()).create()
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.defect_photo, null)
        dialog.apply {
            setView(dialogLayout)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCanceledOnTouchOutside(true)
        }

        val photo: ImageView = dialogLayout.findViewById(R.id.photo_defect_dialog) as ImageView
        photo.setImageBitmap(b)

        return dialog
    }

    companion object {
        fun newInstance(photo: File): PhotoDialogDefect {
            val args = Bundle().apply {
                putSerializable(ARG_PHOTO, photo)
            }
            return PhotoDialogDefect().apply {
                arguments = args
            }
        }
    }
}
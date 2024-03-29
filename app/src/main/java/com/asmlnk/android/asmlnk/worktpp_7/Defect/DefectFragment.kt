package com.asmlnk.android.asmlnk.worktpp_7.Defect

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.asmlnk.android.asmlnk.worktpp_7.R
import com.asmlnk.android.asmlnk.worktpp_7.getScaledBitmap
import java.io.File
import java.util.*

private const val ARG_DEFECT_ID = "defect_id"
private const val REQUEST_PHOTO = 2

@Suppress("DEPRECATION")
class DefectFragment: Fragment() {

    private lateinit var defect: Defect
    private lateinit var photoFile: File
    private lateinit var photoUri: Uri
    private lateinit var photoDefect: ImageView
    private lateinit var selectCamera: ImageButton
    private lateinit var defectTitle: EditText
    private lateinit var defectDetails: EditText
    private  lateinit var loggingCheckBox: CheckBox
    private lateinit var fixedDefectCheckBox: CheckBox
    private var width = 0
    private var height = 0

    private val defectDetailViewModel : DefectDetailViewModel by lazy {
        ViewModelProvider (this) [DefectDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_defect, container, false)

        photoDefect = view.findViewById(R.id.photo_defect) as ImageView
        selectCamera = view.findViewById(R.id.select_camera) as ImageButton
        defectTitle = view.findViewById(R.id.defect_title) as EditText
        defectDetails = view.findViewById(R.id.defect_details) as EditText
        loggingCheckBox = view.findViewById(R.id.check_box_logging) as CheckBox
        fixedDefectCheckBox = view.findViewById(R.id.check_box_defect_fixed) as CheckBox

        val observer = photoDefect.viewTreeObserver
        observer.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                photoDefect.viewTreeObserver.removeOnGlobalLayoutListener(this)

                width = photoDefect.width
                height = photoDefect.measuredHeight
                //updateSize(width, height)
                Log.d("My", "width = $width heidht = $height")
            }
        })

        loggingCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                defect.logging = isChecked
            }
        }
        fixedDefectCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                defect.defectFixed = isChecked
            }
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        defect = Defect()
        val defectId: UUID = arguments?.getSerializable(ARG_DEFECT_ID) as UUID
        defectDetailViewModel.loadDefect(defectId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        defectDetailViewModel.defectLiveData
            .observe(viewLifecycleOwner, Observer { defect ->     // Нужен import androidx.lifecycle.Observer
                defect?.let {
                    this.defect = defect   //наблюдаем за DefectDetailViewModel за свойством defectLiveData как только
                                            // там появиться значение с дефектом он добавиться сюда
                    photoFile = defectDetailViewModel.getPhotoFile(defect)
                    photoUri = FileProvider.getUriForFile(requireActivity(),
                        "com.asmlnk.android.asmlnk.worktpp_7.fileprovider",
                        photoFile)

                    updateUI()
                }
            })
    }

    override fun onStart() {
        super.onStart()

        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                defect.title = s.toString()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        }
        val detailsWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                defect.details = s.toString()

            }
            override fun afterTextChanged(s: Editable?) {
            }
        }

        defectTitle.addTextChangedListener(titleWatcher)
        defectDetails.addTextChangedListener(detailsWatcher)

        selectCamera.apply {
            val packageManager: PackageManager = requireActivity().packageManager
            val captureImage = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val resolvedActivity: ResolveInfo? = packageManager
                .resolveActivity(captureImage, PackageManager.MATCH_DEFAULT_ONLY)

            if (resolvedActivity == null) {
                isEnabled = false
            }

            setOnClickListener {
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                val cameraActivities: List<ResolveInfo> = packageManager
                    .queryIntentActivities(captureImage, PackageManager.MATCH_DEFAULT_ONLY)
                for (cameraActivity in cameraActivities) {
                    requireActivity().grantUriPermission(
                        cameraActivity.activityInfo.packageName,
                        photoUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                }
                startActivityForResult(captureImage, REQUEST_PHOTO)
            }
        }
        photoDefect.apply {
            setOnClickListener {
                if (photoFile.exists()) {
                    PhotoDialogDefect.newInstance(photoFile).apply {
                        show(this@DefectFragment.requireFragmentManager(), "dialog")
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        defectDetailViewModel.saveDefect(defect)
    }

    override fun onDetach() {
        super.onDetach()
        requireActivity().revokeUriPermission(photoUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
    }

    private fun updateUI() {
        defectTitle.setText(defect.title)
        defectDetails.setText(defect.details)
        loggingCheckBox.isChecked = defect.logging
        fixedDefectCheckBox.isChecked = defect.defectFixed
        updatePhotoDefect()
    }

    private fun updatePhotoDefect() {
        if (photoFile.exists()) {
            if (width > 0 && height > 0 ) {
                val bitmap = getScaledBitmap(photoFile.path, width, height )
                val matrix = Matrix()
                if (bitmap.height < bitmap.width) {
                    matrix.postRotate(90F)
                } else {
                    matrix.postRotate(0F)
                }
                val b = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
                photoDefect.setImageBitmap(b)
            } else {
                val bitmap = getScaledBitmap(photoFile.path, requireActivity() ) /*requireActivity()*/
                val matrix = Matrix()
                if (bitmap.height < bitmap.width) {
                    matrix.postRotate(90F)
                } else {
                    matrix.postRotate(0F)
                }
                val b = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
                photoDefect.setImageBitmap(b)
            }
        } else {
            photoDefect.setImageDrawable(null)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       when {
           requestCode == REQUEST_PHOTO -> {
               requireActivity().revokeUriPermission(photoUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
               updatePhotoDefect()
           }
       }
    }

    companion object {
        fun newInstance(defectId: UUID): DefectFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DEFECT_ID, defectId)
            }
            return DefectFragment().apply {
                arguments = args
            }
        }
    }

}


package com.asmlnk.android.asmlnk.worktpp_7

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
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
    }

    override fun onStop() {
        super.onStop()
        defectDetailViewModel.saveDefect(defect)
    }

    private fun updateUI() {
        defectTitle.setText(defect.title)
        defectDetails.setText(defect.details)
        loggingCheckBox.isChecked = defect.logging
        fixedDefectCheckBox.isChecked = defect.defectFixed
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


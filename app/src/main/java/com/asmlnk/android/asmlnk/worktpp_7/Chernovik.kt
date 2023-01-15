package com.asmlnk.android.asmlnk.worktpp_7


class Chernovik {



    /*class MainActivity : AppCompatActivity() {

        private val remoteDB = FirebaseFirestore.getInstance()
        private lateinit var nameTextView: TextView
        private lateinit var shemaTextView: TextView
        private lateinit var categoryTextView: TextView
        private lateinit var allTextView: TextView
        private lateinit var checkBox: CheckBox
        var listEM: List<Electric_motor> = emptyList()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            nameTextView = findViewById(R.id.name)
            shemaTextView = findViewById(R.id.shema)
            categoryTextView = findViewById(R.id.category)
            allTextView = findViewById(R.id.all)
            checkBox = findViewById(R.id.checkbox)

            val ld = MutableLiveData<List<Electric_motor>>()

            val listDoc = remoteDB.collection("Electro")

            listDoc.get().addOnSuccessListener { doc ->
                val r1: MutableList<Electric_motor> = mutableListOf()
                for (it in doc) {
                    r1.add(it.toObject<Electric_motor>().apply { id = it.id })
                }
                ld.value = r1
            }

            ld.observe(this)  { r1 ->
                updateUI(r1)
                listEM = r1
            }

            checkBox.apply {
                setOnCheckedChangeListener { _, isChecked ->
                    if (!listEM.isNullOrEmpty()) {
                        val data = HashMap<String, Any>()
                        data["shema"] = isChecked
                        remoteDB.collection("Electro").document(listEM[0].id)
                            .set(data, SetOptions.merge())
                    }
                }
            }

        }
        fun updateUI(list: List<Electric_motor>) {
            allTextView.text = list[0].name
            categoryTextView.text = list[0].keyCategory
            shemaTextView.text = if (list[0].shema) "true" else "false"
            nameTextView.text = list[0].id
            checkBox.isChecked = list[0].shema

        }

    }*/
}
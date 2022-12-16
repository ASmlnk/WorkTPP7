package com.asmlnk.android.asmlnk.worktpp_7

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class DefectDetailViewModel(): ViewModel() {

    /*ViewModel для DefectFragment*/

    private val defectRepository = DefectRepository.get() //обьект инициализированого репозитория
    private val defectIdLiveData = MutableLiveData<UUID>()  //объект лив даты который хранит значения UUID

    val defectLiveData: LiveData<Defect?> =            //объект лив даты для хранения значения дефектов
        Transformations.switchMap(defectIdLiveData) { defectId ->
            defectRepository.getDefect(defectId)   // fun getDefect(id: UUID): LiveData<Defect?> = defectDao.getDefect(id)
        }

    fun loadDefect(defectId: UUID) {
        defectIdLiveData.value = defectId
    }

    fun saveDefect(defect: Defect) {
        defectRepository.updateDefect(defect)
    }
}

/*Функция loadDefect принимает какоето значение UUID(один дефект который нужно отобразить в DefectFragment)
* принятое значение помещается в изменяемую лив дату в ввиде значения (defectIdLiveData)
* Теперь на нужно получить лив дату с обьектом Defect (defectLiveData) c помощью Transformations
* Transformations принимает два параметра: 1- лив дата которую нужно преобразовать, 2- эта функция в которую нужно преобразовать
* вызвав из репозитория функцию getDefect(UUID), мы приняли из defectIdLiveData значения UUID c помощью его ме достали из БД
* нужный на дефект и упаковали его в лив дата (LiveData<Defect?>)
* */

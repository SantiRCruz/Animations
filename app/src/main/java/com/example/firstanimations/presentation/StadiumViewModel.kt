package com.example.firstanimations.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.firstanimations.core.Result
import com.example.firstanimations.data.local.StadiumDao
import com.example.firstanimations.data.local.TeamDao
import com.example.firstanimations.data.models.StadiumEntity
import com.example.firstanimations.data.models.TeamEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn


class StadiumViewModel(private val dao: StadiumDao) : ViewModel() {

    fun saveMatch(stadiumEntity: StadiumEntity): StateFlow<Result<Long>> = flow {
        kotlin.runCatching {
            dao.saveStadium(stadiumEntity)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Result.Loading()
    )

}

class StadiumViewModelFactory(private val dao: StadiumDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(StadiumDao::class.java).newInstance(dao)
    }
}
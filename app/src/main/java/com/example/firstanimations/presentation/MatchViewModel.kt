package com.example.firstanimations.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.firstanimations.core.Result
import com.example.firstanimations.data.local.MatchDao
import com.example.firstanimations.data.models.MatchEntity
import com.example.firstanimations.data.models.TeamEntity
import com.example.firstanimations.data.models.relations.MatchAndStadium
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class MatchViewModel(private val dao: MatchDao) : ViewModel() {

//    fun fetchMatches(): StateFlow<Result<List<Map<MatchAndStadium,List<TeamEntity>>>>> = flow {
//        kotlin.runCatching {
//            dao.getMatches()
//        }.onSuccess {
//            emit(Result.Success(it))
//        }.onFailure {
//            emit(Result.Failure(Exception(it.message)))
//        }
//    }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000),
//        initialValue = Result.Loading()
//    )
    fun saveMatch(matchEntity: MatchEntity): StateFlow<Result<Long>> = flow {
        kotlin.runCatching {
            dao.saveMatch(matchEntity)
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

class MatchViewModelFactory(private val dao: MatchDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MatchDao::class.java).newInstance(dao)
    }
}
package com.example.firstanimations.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.firstanimations.core.Result
import com.example.firstanimations.data.local.PlayerDao
import com.example.firstanimations.data.models.PlayerEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class PlayerViewModel(private val dao : PlayerDao):ViewModel() {

    fun getPlayersOrderByJersey(id_team:Int):StateFlow<Result<List<PlayerEntity>>> = flow {
        kotlin.runCatching {
            dao.getPlayersOrderByJersey(id_team)
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
    fun savePlayer(playerEntity: PlayerEntity):StateFlow<Result<Long>> = flow {
        kotlin.runCatching {
            dao.savePlayer(playerEntity)
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

class PlayerViewModelFactory(private val dao:PlayerDao):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PlayerDao::class.java).newInstance(dao)
    }

}
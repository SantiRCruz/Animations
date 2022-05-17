package com.example.firstanimations.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.firstanimations.core.Result
import com.example.firstanimations.data.local.MatchDao
import com.example.firstanimations.data.local.TeamDao
import com.example.firstanimations.data.models.MatchEntity
import com.example.firstanimations.data.models.TeamEntity
import com.example.firstanimations.data.models.relations.MatchAndStadium
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn


class TeamViewModel(private val dao: TeamDao) : ViewModel() {

    fun saveMatch(teamEntity: TeamEntity): StateFlow<Result<Long>> = flow {
        kotlin.runCatching {
            dao.saveTeam(teamEntity)
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

class TeamViewModelFactory(private val dao: TeamDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TeamDao::class.java).newInstance(dao)
    }
}
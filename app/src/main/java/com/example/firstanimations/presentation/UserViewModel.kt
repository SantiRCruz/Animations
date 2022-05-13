package com.example.firstanimations.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.firstanimations.data.local.UserDao
import com.example.firstanimations.data.models.UserEntity
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import com.example.firstanimations.core.Result
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class UserViewModel(private val dao: UserDao) : ViewModel() {

    fun fetchUserAll(): StateFlow<Result<List<UserEntity>>> = flow {
        kotlin.runCatching {
            dao.getAllUser()
        }.onSuccess {
            Log.e("fetchUserAll: ",it.toString() )
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Result.Loading()
    )

    fun fetchUser(email: String): StateFlow<Result<List<UserEntity>>> = flow {
        kotlin.runCatching {
            dao.getAllUserByEmail(email)
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

    fun saveUser(userEntity: UserEntity): StateFlow<Result<Long>> = flow {
        kotlin.runCatching {
            dao.saveUser(userEntity)
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
    fun updateUser(userEntity: UserEntity): StateFlow<Result<Int>> = flow {
        kotlin.runCatching {
            dao.updateUser(userEntity)
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

    fun deleteUser(userEntity: UserEntity): StateFlow<Result<Int>> = flow {
        kotlin.runCatching {
            dao.deleteUser(userEntity)
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
class UserViewModelFactory(private val dao: UserDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UserDao::class.java).newInstance(dao)
    }

}

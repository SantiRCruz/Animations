package com.example.firstanimations.data.local

import androidx.room.*
import com.example.firstanimations.data.models.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity WHERE (email = :email OR name = :email) ")
    suspend fun getAllUserByEmail(email:String):List<UserEntity>

    @Query("SELECT * FROM UserEntity  ")
    suspend fun getAllUser():List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveUser(userEntity: UserEntity):Long

    @Update
    suspend fun updateUser(userEntity: UserEntity):Int

    @Delete
    suspend fun deleteUser(userEntity: UserEntity):Int


}
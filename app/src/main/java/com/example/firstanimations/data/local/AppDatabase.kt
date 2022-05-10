package com.example.firstanimations.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firstanimations.data.models.*

@Database(
    entities = arrayOf(
        ImageEntity::class,
        ImagePlayerEntity::class,
        MatchEntity::class,
        PlayerEntity::class,
        StadiumEntity::class,
        TeamEntity::class,
        UserEntity::class
    ),
    version = 1
)
abstract class AppDatabase:RoomDatabase() {
    abstract fun ImageDao():ImageDao
    abstract fun ImagePlayerDao():ImagePlayerDao
    abstract fun MatchDao():MatchDao
    abstract fun PlayerDao():PlayerDao
    abstract fun StadiumDao():StadiumDao
    abstract fun TeamDao():TeamDao
    abstract fun UserDao():UserDao

    companion object{
        private var INSTANCE: AppDatabase?=null

        fun getImageDatabase(context:Context):AppDatabase{
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "image_table"
            ).build()
            return INSTANCE!!
        }
        fun getImagePlayerDatabase(context: Context):AppDatabase{
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "image_player_table"
            ).build()
            return INSTANCE!!
        }
        fun getMatchDatabase(context: Context):AppDatabase{
            INSTANCE = INSTANCE?:Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "match_table"
            ).build()
            return INSTANCE!!
        }
        fun getPlayerDatabase(context: Context):AppDatabase{
            INSTANCE = INSTANCE?:Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "player_table"
            ).build()
            return INSTANCE!!
        }
        fun getStadiumDatabase(context: Context):AppDatabase{
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "stadium_table"
            ).build()
            return INSTANCE!!
        }
        fun getTeamDatabase(context: Context):AppDatabase{
            INSTANCE = INSTANCE?:Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "team_table"
            ).build()
            return INSTANCE!!
        }
        fun getUserDatabase(context:Context):AppDatabase{
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "user_table"
            ).build()
            return INSTANCE!!
        }

    }
}
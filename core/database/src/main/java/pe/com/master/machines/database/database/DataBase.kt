package pe.com.master.machines.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.com.master.machines.database.dao.StoryCharacterEntityDao
import pe.com.master.machines.database.entity.StoryCharacterEntity

@Database(
    entities = [
        StoryCharacterEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {

    abstract fun daoStoryCharacterEntity(): StoryCharacterEntityDao

}
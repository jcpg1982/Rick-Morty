package pe.com.master.machines.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pe.com.master.machines.database.entity.StoryCharacterEntity

@Dao
interface StoryCharacterEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(entities: List<StoryCharacterEntity>)

    @Query("SELECT * FROM StoryCharacterEntity WHERE id = :id")
    suspend fun getSingleCharacter(id: Int): StoryCharacterEntity

    @Query("SELECT * FROM StoryCharacterEntity ORDER BY id ASC LIMIT :limit OFFSET :offset ")
    suspend fun getCharactersByPage(limit: Int, offset: Int): List<StoryCharacterEntity>

    @Query("SELECT * FROM StoryCharacterEntity WHERE name LIKE '%' || :name || '%' AND status = :status " +
            "ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun searchCharacterByName(
        limit: Int, offset: Int, name: String, status: String
    ): List<StoryCharacterEntity>

}
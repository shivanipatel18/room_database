package com.example.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    /**
     * A DAO (data access object) validates your SQL at compile-time and associates it with a method. In your Room DAO,
     * you can also use handy annotations, like @Insert, for the most common database operations! Room uses the DAO to
     * create a clean API for your code.

    The DAO must be an interface or abstract class. By default, all queries must be executed on a separate thread.

    Room has coroutines support. So your queries can be annotated with the suspend modifier and called from a coroutine
    or from another suspension function.

     */

    @Query("SELECT * from user_info")
    fun getAllUsers() : LiveData<List<User>>

    @Query("SELECT COUNT(*) from user_info")
    fun countUsers(): Int

    /**
     * onConflict = OnConflictStrategy.IGNORE: The conflict strategy ignores a new word if it's exactly the same as one already in the list
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("DELETE FROM user_info")
    suspend fun deleteAll()
}
package com.example.roomdatabase

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch

@Database(entities = arrayOf(User::class), version = 1)
public abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(UserDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class UserDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        /**
         * Override the onOpen method to populate the database.
         * For this sample, we clear the database every time it is created or opened.
         */
        override fun onOpen(db: SupportSQLiteDatabase) {
            INSTANCE?.let { database ->
                scope.launch {
                    (Dispatchers.IO){
                        populateDatabase(database.userDao())
                    }
                }
            }

        }


        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(userDao: UserDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            userDao.deleteAll()

            var user = User(1, "Shivani", "Patel")
            userDao.insert(user)
            user = User(2, "Abinash", "Das")
            userDao.insert(user)
        }
    }
}

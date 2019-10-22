package com.example.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Each @Entity class represents a table. Annotate your class declaration to indicate that it's an entity.
 * You can specify the name of the table if you want it to be different from the name of the class.
 */

/**
 *  @PrimaryKey
Every entity needs a primary key. To keep things simple, each word acts as its own primary key.

@ColumnInfo()
Specify the name of the column in the table if you want it to be different from the name of the member variable.
Every field that's stored in the database needs to be either public or have a "getter" method.

 */

@Entity(tableName = "user_info")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "first_name") val first_name: String,
    @ColumnInfo(name = "last_name") val last_name: String
)


# Room Database

Room is an ORM (object relational mapper) for SQLite database in Android. It is part of the Architecture Components released by Google. At its core, all the code that you write related to Room will eventually be converted to SQLite code. Room allows you to create and manipulate database in Android more quickly. See it as an abstraction layer on top of inbuilt SQLite database.

Simplifies database work and serves as an access point to the underlying SQLite database (hides SQLiteOpenHelper). The Room database uses the DAO to issue queries to the SQLite database.

Entity: Annotated class that describes a database table when working with Room.

SQLite database: On device storage. Room persistence library creates and maintains this database for you.

DAO: Data access object. A mapping of SQL queries to functions. When you use a DAO, you call the methods, and Room takes care of the rest.


https://developer.android.com/training/data-storage/room
https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#0
https://medium.com/@ajaysaini.official/building-database-with-room-persistence-library-ecf7d0b8f3e9

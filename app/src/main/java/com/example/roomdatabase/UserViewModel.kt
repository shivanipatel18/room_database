package com.example.roomdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

// Class extends AndroidViewModel and requires application as a parameter.
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()

    // By default all the coroutines launched in this scope should be using the Main dispatcher
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: UserRepository

    // LiveData gives us updated words when they change.
    val allUsers: LiveData<List<User>>

    init {
        // Gets reference to UserDao from AppDatabase to construct
        // the correct UserRepository.
        val userDao = AppDatabase.getDatabase(application, scope).userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsers
    }

    // The implementation of insert() is completely hidden from the UI.
    // We don't want insert to block the main thread, so we're launching a new
    // coroutine. ViewModels have a coroutine scope based on their lifecycle called
    // viewModelScope which we can use here.
    fun insert(user: User) = scope.launch(Dispatchers.IO) {
        repository.insert(user)
    }


    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }


}
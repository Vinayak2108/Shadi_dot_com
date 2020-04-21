package com.shaadi.codechallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaadi.codechallenge.model.localdb.User
import com.shaadi.codechallenge.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListViewModel : ViewModel() {

    private val _userList = MutableLiveData<ArrayList<User>>()
    val userList: LiveData<ArrayList<User>>
        get() = _userList

    private val _notifyChanges = MutableLiveData<Boolean>()
    val notifyChanges:LiveData<Boolean>
    get() = _notifyChanges


    fun loadData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val data = DataRepository.getUserDate()
                withContext(Dispatchers.Main){
                    _userList.value = data as ArrayList<User>?
                }
            }
        }
    }

    fun updateUser(dataItem: User) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                DataRepository.updateUser(user = dataItem)
                withContext(Dispatchers.Main){
                    _notifyChanges.value = true
                }
            }
        }
    }

    fun resetNotify() {
        _notifyChanges.value = false
    }


}

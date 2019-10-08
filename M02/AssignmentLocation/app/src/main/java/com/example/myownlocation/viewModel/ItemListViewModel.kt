package com.example.myownlocation.viewModel

import androidx.lifecycle.ViewModel
import com.example.myownlocation.model.MeModel
import com.example.myownlocation.model.MyName

class ItemListViewModel : ViewModel() {

    private var savedContacts = mutableListOf<MeModel>()

    fun clearSavedContacts() {
        savedContacts.clear()
    }

    fun savedContactsSize(): Int = savedContacts.size

    fun putSavedContacts(contacts: List<MeModel>) {
        clearSavedContacts()
        savedContacts = contacts.toMutableList()
    }

    fun getSavedContacts(): List<MeModel> = savedContacts.toList()
}

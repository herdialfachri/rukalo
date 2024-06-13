package com.herdialfachri.rukaloumkm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import com.herdialfachri.rukaloumkm.data.DataClass

class HomeViewModel : ViewModel() {

    private val _products = MutableLiveData<List<DataClass>>()
    val products: LiveData<List<DataClass>> get() = _products

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Product")
    private val eventListener: ValueEventListener

    init {
        eventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val productList = mutableListOf<DataClass>()
                for (itemSnapshot in snapshot.children) {
                    val dataClass = itemSnapshot.getValue(DataClass::class.java)
                    dataClass?.key = itemSnapshot.key
                    dataClass?.let { productList.add(it) }
                }
                _products.value = productList
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        }
        databaseReference.addValueEventListener(eventListener)
    }

    fun refreshData() {
        databaseReference.addListenerForSingleValueEvent(eventListener)
    }

    override fun onCleared() {
        super.onCleared()
        databaseReference.removeEventListener(eventListener)
    }
}

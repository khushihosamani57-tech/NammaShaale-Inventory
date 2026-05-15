package com.example.nammashaaleinventory.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nammashaaleinventory.data.model.InventoryItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects

class InventoryViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val inventoryCollection = db.collection("inventory")

    private val _items = MutableLiveData<List<InventoryItem>>()
    val items: LiveData<List<InventoryItem>> = _items

    private val _operationStatus = MutableLiveData<Boolean>()
    val operationStatus: LiveData<Boolean> = _operationStatus

    fun addItem(item: InventoryItem) {
        val docRef = inventoryCollection.document()
        val itemWithId = item.copy(id = docRef.id)
        docRef.set(itemWithId)
            .addOnSuccessListener { _operationStatus.postValue(true) }
            .addOnFailureListener { _operationStatus.postValue(false) }
    }

    fun fetchItems() {
        inventoryCollection.addSnapshotListener { snapshot, e ->
            if (e != null) return@addSnapshotListener
            if (snapshot != null) {
                _items.postValue(snapshot.toObjects<InventoryItem>())
            }
        }
    }

    fun issueItem(itemId: String, name: String, quantity: Int) {
        val docRef = inventoryCollection.document(itemId)
        db.runTransaction { transaction ->
            val snapshot = transaction.get(docRef)
            val currentQty = snapshot.getLong("quantity") ?: 0
            if (currentQty >= quantity) {
                transaction.update(docRef, "quantity", currentQty - quantity)
                transaction.update(docRef, "isIssued", true)
                transaction.update(docRef, "issuedTo", name)
            }
        }.addOnSuccessListener { _operationStatus.postValue(true) }
         .addOnFailureListener { _operationStatus.postValue(false) }
    }

    fun returnItem(itemId: String, quantity: Int) {
        val docRef = inventoryCollection.document(itemId)
        db.runTransaction { transaction ->
            val snapshot = transaction.get(docRef)
            val currentQty = snapshot.getLong("quantity") ?: 0
            transaction.update(docRef, "quantity", currentQty + quantity)
            transaction.update(docRef, "isIssued", false)
            transaction.update(docRef, "issuedTo", null)
        }.addOnSuccessListener { _operationStatus.postValue(true) }
         .addOnFailureListener { _operationStatus.postValue(false) }
    }
}
package com.example.nammashaaleinventory.data.model

data class InventoryItem(
    val id: String = "",
    val name: String = "",
    val category: String = "",
    val quantity: Int = 0,
    val description: String = "",
    val issuedTo: String? = null,
    val isIssued: Boolean = false
)
package com.example.tiendavirtualuco.sellerRating.repository

@Entity(tableName = "seller_table")
data class Seller (
    @PrimaryKey val id: Int = 0,
    val username: String,
    val date: String,
    val rating: Float,
    val comment: String)

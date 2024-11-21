package com.example.tiendavirtualuco.sellerRating.repository

@Dao
interface SellerRatingDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeller(seller: Seller)

    @Query("SELECT * FROM seller_table")
    fun getAllSellers(): List<Seller>

    @Query("SELECT AVG(rating) FROM seller_table")
    fun getAverageRating(): Float?

    @Delete
    fun deleteSeller(seller: Seller)
}
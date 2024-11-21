package com.example.tiendavirtualuco.sellerRating.repository

@Database(entities = [Seller::class], version = 1, exportSchema = false)
abstract class SellerDatabase: RoomDatabase() {

    abstract fun sellerDao(): SellerDao

    companion object {
        @Volatile
        private var INSTANCE: SellerDatabase? = null

        fun getDatabase(context: Context): SellerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SellerDatabase::class.java,
                    "seller_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
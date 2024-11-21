package com.example.tiendavirtualuco.sellerRating

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendavirtualuco.R

class SellerRating : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private val commentsList = mutableListOf<Pair<String, Float>>()
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_rating)

        firestore = FirebaseFirestore.getInstance()
        commentAdapter = CommentAdapter(commentsList)

        val commentRecyclerView: RecyclerView = findViewById(R.id.commentRecyclerView)
        commentRecyclerView.layoutManager = LinearLayoutManager(this)
        commentRecyclerView.adapter = commentAdapter

        fetchCommentsFromFirestore()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val comment = data?.getStringExtra("comment")
            val rating = data?.getFloatExtra("rating", 0f)

            if (comment != null && rating != null) {
                val sellerData = hashMapOf(
                    "username" to "Default User",
                    "date" to "Today",
                    "rating" to rating,
                    "comment" to comment
                )

                // Guardar en Firestore
                firestore.collection("sellers")
                    .add(sellerData)
                    .addOnSuccessListener {
                        commentsList.add(Pair(comment, rating))
                        commentAdapter.notifyDataSetChanged()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error al guardar comentario", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun fetchCommentsFromFirestore() {
        firestore.collection("sellers")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val comment = document.getString("comment") ?: ""
                    val rating = document.getDouble("rating")?.toFloat() ?: 0f
                    commentsList.add(Pair(comment, rating))
                }
                commentAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al cargar comentarios", Toast.LENGTH_SHORT).show()
            }
    }


    private fun updateAverageRating() {
        if (commentsList.isNotEmpty()) {
            val totalRating = commentsList.sumByDouble { it.second.toDouble() }
            val averageRating = totalRating / commentsList.size
            findViewById<RatingBar>(R.id.averageRating).rating = averageRating.toFloat()
        }
    }
}

package com.example.chucknorrisjokesapp.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisjokesapp.R


class CategoriesAdapter(
    private val categories: List<String>,
    private val onCategoryClick: (String) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCategoryName: TextView = itemView.findViewById(R.id.tvCategoryName)

        fun bind(category: String) {
            tvCategoryName.text = category
            itemView.setOnClickListener {
                onCategoryClick(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size
}

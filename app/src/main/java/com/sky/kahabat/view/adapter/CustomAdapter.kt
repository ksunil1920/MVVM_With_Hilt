package com.sky.kahabat.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.kahabat.databinding.EachRowItemBinding

import com.sky.kahabat.model.Result

class CustomAdapter :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private  var dataSet: List<Result> = emptyList()
    fun updateItems(items: List<Result>?) {
        dataSet = items ?: emptyList()
        notifyDataSetChanged()
    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val rootView = EachRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(rootView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val result = dataSet[position]
        viewHolder.bind(result)
    }

    override fun getItemCount() = dataSet.size


    class ViewHolder(private val binding: EachRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result) {
            binding.tvAuthor.text = result.author
            binding.tvQuotes.text = result.content
        }
    }

}

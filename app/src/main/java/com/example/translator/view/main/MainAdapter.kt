package com.example.translator.view.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.model.DataModel
import com.example.translator.databinding.ActivityMainRecyclerviewItemBinding

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener,
) : RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    private lateinit var binding: ActivityMainRecyclerviewItemBinding
    private var data: List<DataModel>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        binding = ActivityMainRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return RecyclerItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        data?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerTextviewRecyclerItem.text = data.text
                binding.descriptionTextviewRecyclerItem.text =
                    data.meanings?.get(0)?.translation?.translation
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}
package com.example.translator.view.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.translator.databinding.ActivityMainRecyclerviewItemBinding
import com.example.translator.model.data.DataModel

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
        return RecyclerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        data?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    inner class RecyclerItemViewHolder(
        private val itemBinding: ActivityMainRecyclerviewItemBinding,
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemBinding.headerTextviewRecyclerItem.text = data.text
                itemBinding.descriptionTextviewRecyclerItem.text =
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
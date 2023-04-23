package com.dal.judobeltsf.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dal.judobeltsf.database.judoka.JudokaEntity
import com.dal.judobeltsf.databinding.NamesItemBinding
import java.text.SimpleDateFormat


class Adapter(
    private val onItemClicked: (JudokaEntity) -> Unit
) : ListAdapter<JudokaEntity, Adapter.BusStopViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<JudokaEntity>() {
            override fun areItemsTheSame(oldItem: JudokaEntity, newItem: JudokaEntity): Boolean {
                return oldItem.license == newItem.license
            }

            override fun areContentsTheSame(oldItem: JudokaEntity, newItem: JudokaEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val viewHolder = BusStopViewHolder(
            NamesItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BusStopViewHolder(
        private var binding: NamesItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(j: JudokaEntity) {
            binding.stopNameTextView.text = j.name
            binding.arrivalTimeTextView.text = j.license

        }
    }
}

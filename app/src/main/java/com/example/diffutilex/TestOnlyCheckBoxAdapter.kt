package com.example.diffutilex

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilex.TestAdapter.ViewHolder
import com.example.diffutilex.databinding.ItemCheckListBinding
import com.example.diffutilex.databinding.ItemOnlyCheckBinding

class TestOnlyCheckBoxAdapter : androidx.recyclerview.widget.ListAdapter<Boolean, TestOnlyCheckBoxAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemOnlyCheckBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Boolean) {
            binding.checkbox.isChecked = item
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        return ViewHolder(ItemOnlyCheckBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Boolean>() {
            override fun areItemsTheSame(oldItem: Boolean, newItem: Boolean): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Boolean, newItem: Boolean): Boolean {
                return oldItem == newItem
            }


        }
    }

    override fun onBindViewHolder(holder: TestOnlyCheckBoxAdapter.ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

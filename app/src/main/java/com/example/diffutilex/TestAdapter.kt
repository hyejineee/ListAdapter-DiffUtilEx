package com.example.diffutilex

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilex.TestAdapter.ViewHolder
import com.example.diffutilex.databinding.ItemCheckListBinding

class TestAdapter : androidx.recyclerview.widget.ListAdapter<TestModel, ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemCheckListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TestModel) {
            binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                item.check = isChecked
            }
            binding.checkbox.isChecked = item.check
            binding.titleTextView.text = item.title
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCheckListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TestModel>() {
            override fun areItemsTheSame(oldItem: TestModel, newItem: TestModel): Boolean {
                Log.d("TestAdapter", "areItemsTheSame-oldItem :$oldItem, newItem:$newItem")
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TestModel, newItem: TestModel): Boolean {
                Log.d("TestAdapter", "areContentsTheSame-oldItem :$oldItem, newItem:$newItem")
                return oldItem == newItem
            }

        }
    }
}

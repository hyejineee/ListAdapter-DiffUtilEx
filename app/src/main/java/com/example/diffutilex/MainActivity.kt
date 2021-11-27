package com.example.diffutilex

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutilex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val testAdapter = TestAdapter()
    private val onlyCheckAdapter = TestOnlyCheckBoxAdapter()
    private var allChecked = false

    private var checkList = (1..50).map {
        TestModel(id = it, "title$it")
    }.toMutableList()


    private var checkBoxValueList = List<Boolean>(50) { false }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        initCheckBodRecyclerView()
//        addCheckBodItem()
//        handleAllCheckButtonWhenOnlyCheckList()

        initTestModelRecyclerView()
        addTestModelItem()
        handleAllCheckButtonWhenTestModelList()
        handleRemoveItemButtonClick()

    }

    private fun initTestModelRecyclerView() {
        binding.testRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = testAdapter
        }
    }

    private fun initCheckBoxRecyclerView() {
        binding.testRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = onlyCheckAdapter
        }
    }

    private fun addTestModelItem() {
        testAdapter.submitList(checkList)
    }

    private fun addCheckBoxItem() {
        onlyCheckAdapter.submitList(checkBoxValueList)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun handleAllCheckButtonWhenOnlyCheckList() {
        binding.allCheckButton.setOnClickListener {
            val new = if (allChecked) {
                checkBoxValueList.toMutableList().apply {
                    replaceAll { false }
                }
            } else {
                checkBoxValueList.toMutableList().apply {
                    replaceAll { true }
                }
            }
            onlyCheckAdapter.submitList(new)
            allChecked = allChecked.not()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun handleAllCheckButtonWhenTestModelList() {
        binding.allCheckButton.setOnClickListener {
            val new = if (allChecked) {
                testAdapter.currentList.map {
                    it.copy(check = false)
                }
//                testAdapter.currentList.toMutableList().apply {
//                    this.forEach {
//                        it.check = false
//                    }
//                }
                //주소값이 같아서 갱신이 안되는 경우
//                checkList.apply {
//                    this.forEach {
//                        it.check = true
//                    }
//                }
            } else {
                testAdapter.currentList.map {
                    it.copy(check = true)
                }
//                testAdapter.currentList.toMutableList().apply {
//                    this.forEach {
//                        it.check = true
//                    }
//                }
                //주소값이 같아서 갱신이 안되는 경우
//                checkList.apply {
//                    this.forEach {
//                        it.check = false
//                    }
//                }
            }
            testAdapter.submitList(new)

            allChecked = allChecked.not()
        }
    }

    private fun handleRemoveItemButtonClick() {
        binding.removeItemButton.setOnClickListener {
            val new = testAdapter.currentList.toMutableList().apply { removeFirst() }
            testAdapter.submitList(new)
        }
    }
}

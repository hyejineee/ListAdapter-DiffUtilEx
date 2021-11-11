package com.example.diffutilex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutilex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val testAdapter = TestAdapter()
    private var allChecked = false

    private var checkList = (1..50).map {
        TestModel("title$it")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        addItem()

        binding.allCheckButton.setOnClickListener {
            if(allChecked){
                val new = (1..50).map {
                    TestModel("title$it").apply {
                        check = false
                    }
                }
                testAdapter.submitList(new)
            }else{
                val new = (1..50).map {
                    TestModel("title$it").apply {
                        check = true
                    }
                }
                testAdapter.submitList(new)
            }

            allChecked = allChecked.not()


        }

    }

    private fun initRecyclerView(){
        binding.testRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = testAdapter
        }
    }

    private fun addItem(){
        testAdapter.submitList(checkList)
    }
}
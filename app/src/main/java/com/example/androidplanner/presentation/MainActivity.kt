package com.example.androidplanner.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidplanner.R
import com.example.androidplanner.data.repository.DataPlannerRepository
import com.example.androidplanner.domain.useCase.GetRecordListUseCase
import com.example.androidplanner.presentation.mapper.RecordMapper
import com.example.androidplanner.presentation.viewModels.RecordViewModel
import com.example.androidplanner.presentation.viewModels.RecordViewModelFacory

class MainActivity : AppCompatActivity() {
    private val repository = DataPlannerRepository()
    private val getRecordList = GetRecordListUseCase(repository)
    private val mapper = RecordMapper()
    lateinit var viewModel : ViewModel


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this,
            RecordViewModelFacory(getRecordList, mapper),
        )[RecordViewModel::class.java]

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer,RecordsFragment())
                .commit()
        }
    }
}
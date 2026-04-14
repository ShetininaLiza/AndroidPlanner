package com.example.planner

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.planner.adapter.RecordAdapter
import com.example.planner.model.RecordItemClass
import java.io.FileDescriptor
import java.io.PrintWriter


class RecordsFragment : Fragment(R.layout.fragment_records) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Здесь надо отображать список

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_records, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recordsRecycle = view.findViewById<RecyclerView>(R.id.recordsList)
        val recordAdapter = RecordAdapter();
        recordsRecycle.layoutManager = LinearLayoutManager(view.context)
        var list: MutableList<RecordItemClass> = mutableListOf();
        for(i in 1..5 step 1){
            var data = RecordItemClass("Заметка $i")
            list.add(data)
        }
        Log.v("FRAGMENT", list.size.toString())
        recordAdapter.data= list;

        recordsRecycle.adapter = recordAdapter

        val btnAddRecord = view.findViewById<ImageButton>(R.id.btnAddRecord)

        btnAddRecord.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddRecordFragment())
                .commit()
        }
    }
}
package com.example.notetakerwmenu.fragments

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notetakerwmenu.DatabaseHelper.DataHelper
import com.example.notetakerwmenu.R

import com.example.notetakerwmenu.adapter.RecyclerViewForNotesAdapter
import com.example.notetakerwmenu.models.NotesModel
import kotlinx.android.synthetic.main.fragment_view.view.*

class ViewFragment : Fragment() {

    lateinit var mydb : DataHelper
    lateinit var listdata : MutableList<NotesModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view : View = inflater.inflate(R.layout.fragment_view, container,false)
        mydb= DataHelper(context)
        view.recyclerview.setHasFixedSize(true)
        view.recyclerview.layoutManager = GridLayoutManager(context,2)
        listdata = ArrayList()

        getData()

        view.recyclerview.adapter =RecyclerViewForNotesAdapter(listdata,view.context)

        return view
    }
    fun getData(){
        val res : Cursor =mydb.readAllNote()
        if(res.count==0){
            Toast.makeText(context,"Your Note is Empty!",Toast.LENGTH_SHORT).show()
            return
        }
        while (res.moveToNext()){

            val dataNotes : NotesModel = NotesModel(res.getInt(0),res.getString(1)
                ,res.getString(2),res.getString(3).substring(0,10))
            listdata.add(dataNotes)
        }
    }
}
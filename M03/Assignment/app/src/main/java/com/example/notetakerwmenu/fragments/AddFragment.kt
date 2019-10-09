package com.example.notetakerwmenu.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notetakerwmenu.DatabaseHelper.DataHelper

import com.example.notetakerwmenu.R
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment(), View.OnClickListener {

    lateinit var mydb : DataHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.fragment_add, container,false)
        mydb=DataHelper(context)
        view.btn_Save.setOnClickListener(this)
        return view
    }



    fun saveNote(){
        val title : String = et_Title.text.toString().trim()
        if (TextUtils.isEmpty(title)){
            et_Title.error = "Please, Insert a Title!"
            return
        }
        var note : String = et_Notes.text.toString().trim()
        if(TextUtils.isEmpty(note)){
            et_Notes.error = "Please, Insert a Note!"
            return
        }

        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date : String = sdf.format(Date())

        var isInserted : Boolean = mydb.addNote(title,note,date)
        if(isInserted){
            Toast.makeText(context,"Saved Successfully",Toast.LENGTH_SHORT).show()
            et_Notes.setText("")
            et_Title.setText("")
        }
    }
    override fun onClick(v: View?) {
        saveNote()
    }
}
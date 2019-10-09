package com.example.notetakerwmenu

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.notetakerwmenu.DatabaseHelper.DataHelper
import kotlinx.android.synthetic.main.activity_view.*
import kotlinx.android.synthetic.main.fragment_add.*
import java.text.SimpleDateFormat
import java.util.*

class ViewActivity : AppCompatActivity(),  View.OnClickListener {

    lateinit var mydb : DataHelper
    lateinit var id : String

    override fun onClick(v: View?) {
        if(v == btn_View_Update){
            update()
        }else deleteNote()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        //
        mydb= DataHelper(this)
        id= intent.getStringExtra("id")
        //
        btn_View_Update.setOnClickListener(this)
        btn_View_Delete.setOnClickListener(this)
        //
        readData()
    }
    fun readData(){
        val res : Cursor =mydb.readOneNote(id)
        while(res.moveToNext()){
            et_View_Title.setText(res.getString(1))
            et_View_Notes.setText(res.getString(2))
        }
    }
    fun update(){
        val title : String = et_View_Title.text.toString()
        if (TextUtils.isEmpty(title)){
            et_Title.error = "Please, Insert a Title!"
            return
        }
        val note : String = et_View_Notes.text.toString()
        if(TextUtils.isEmpty(note)){
            et_Notes.error = "Please, Insert a Note!"
            return
        }
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date : String = sdf.format(Date())

        var isUpdated : Boolean = mydb.updateNote(id, title, note, date)

        if(isUpdated){
            Toast.makeText(applicationContext,"Update Successfully!", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        } else
            Toast.makeText(applicationContext,"Update Failed!", Toast.LENGTH_SHORT).show()
    }
    fun deleteNote(){

        var isDeleted:Boolean=mydb.deleteNote(id)

        if(isDeleted){
            Toast.makeText(applicationContext,"Successfully deleted!", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        } else
            Toast.makeText(applicationContext,"Delete Failed!", Toast.LENGTH_SHORT).show()
    }
}
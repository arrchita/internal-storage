package com.example.internalstorage

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internalstorage.R.*
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    lateinit var btn: Button
    lateinit var btn1: Button
    lateinit var textView:TextView
    lateinit var btn2:Button
    lateinit var btn3:Button
    val filename="MyFile.txt"
    lateinit var imgview:ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btn=findViewById(R.id.button)
        btn1=findViewById(R.id.button2)
        textView=findViewById(R.id.editTextText)
        btn2=findViewById(R.id.button3)
        btn3=findViewById(R.id.button4)
        imgview=findViewById(R.id.imageView)


        btn.setOnClickListener{
            savetointernalstorage(textView.text.toString())
        }

        btn1.setOnClickListener{
            loadfrominternalstorage()
        }

        btn2.setOnClickListener{
            val bitmap=BitmapFactory.decodeResource(resources,R.drawable.flowers)
        //first parameter is address of img and the second is by the name we wish to store the image to internal storage
            saveimagetointernalstorage(bitmap,"flowers.jpg")
        }

        btn3.setOnClickListener{
            loadimagefrominternalstorage("flowers.jpg")
        }

    }

    private fun saveimagetointernalstorage(bitmap: Bitmap, imgname: String){
        val fileoutputimage:FileOutputStream=openFileOutput(imgname,Context.MODE_PRIVATE)
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileoutputimage)
        fileoutputimage.close()
        Toast.makeText(this,"Image Saved", Toast.LENGTH_SHORT).show()
    }

    private fun loadimagefrominternalstorage(imgname: String) {
        val file=File(filesDir,imgname)
        val bitmap=BitmapFactory.decodeFile(file.absolutePath)
        imgview.setImageBitmap(bitmap)
        Toast.makeText(this,"Loaded Image",Toast.LENGTH_SHORT).show()
    }

    private fun savetointernalstorage(text:String){
        val outputStream:FileOutputStream=openFileOutput(filename,Context.MODE_PRIVATE)
        outputStream.write(text.toByteArray())
        outputStream.close()
        Toast.makeText(this,"Data Saved",Toast.LENGTH_SHORT).show()
    }

    private fun loadfrominternalstorage(){
        val file=File(filesDir,filename)
        val text=file.readText()
        textView.setText(text)
        Toast.makeText(this,"Data loaded",Toast.LENGTH_SHORT).show()
    }
}
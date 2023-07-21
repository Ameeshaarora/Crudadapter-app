package com.ameesha.crudadapter

import android.app.Dialog
import android.content.AbstractThreadedSyncAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.ameesha.crudadapter.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var array = mutableListOf<String>("Ameesha", "shabnam", "rohan")
    lateinit var adapter: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView (binding.root)
        adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,array)
        binding .listView.adapter=adapter
       binding.listView.setOnItemClickListener { adapter, view, position.id->

           AlertDialog.Builder(this)
               .setTitle("Delete or update")
               .setMessage("Do you want to delete or update")
               .setPositiveButton("Delete") { _, _ ->
                   array.removeAt(position)
                   adapter.notifyDataSetChanged()

               .setNegativeButton("Update", { _, _ ->
                   showUpdateDialog(position)
               })
         .show()
        }

        binding.fab.setOnClickListener{
        var dialog = Dialog(this)
        dialog.setContentView(R.layout.activity_add_item_layout)
        dialog.show()
        var etName = dialog.findViewById<EditText>(R.id.evName)
        var btnAdd = dialog.findViewById<Button>(R.id.btnadd)

        btnAdd.setOnClickListener {
            if (etName.text.toString().isNullOrEmpty()) {
                etName.error = "Enter your name"
            } else {
                array.add(etName.text.toString())
                adapter.notifyDataSetChanged()dialog.dismiss()
            }
        }

    }

        }

    }
}

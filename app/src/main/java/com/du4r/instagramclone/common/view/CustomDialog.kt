package com.du4r.instagramclone.common.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import com.du4r.instagramclone.R

class CustomDialog(context: Context): Dialog(context) {
    private lateinit var dialogLinearLayout: LinearLayout
    private lateinit var txtButtons: Array<TextView>
    private lateinit var txtTitle: TextView
    private var titleId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog)

        dialogLinearLayout = findViewById(R.id.dialog_container)
        txtTitle = findViewById(R.id.dialog_title)
    }

    fun addButton(vararg text: Int,listener: View.OnClickListener){
        txtButtons = Array(text.size){
            TextView(context)
        }
        
        text.forEachIndexed { index, txtId ->
            txtButtons[index].id = txtId
            txtButtons[index].setText(txtId)
            txtButtons[index].setOnClickListener {
                listener.onClick(it)
                dismiss()
            }
        }
    }


    override fun setTitle(titleId: Int) {
        this.titleId = titleId
    }

    override fun show() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.show()

        titleId?.let {
            txtTitle.setText(it)
        }

        for(textView in txtButtons){

            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(30,50,30,50)

            dialogLinearLayout.addView(textView, layoutParams)
        }

    }

}
package com.izmir.izmirli.util

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.izmir.izmirli.R
import com.izmir.izmirli.model.NobetciEczaneResponse
import kotlinx.android.synthetic.main.result_quick.view.*

fun Activity.eczaneDetay(response: NobetciEczaneResponse.NobetciEczaneResponseItem){
    val view: View = this.layoutInflater.inflate(R.layout.result_quick, null)
    val bottomSheetDialog = BottomSheetDialog(this)
    view.rootView.tv_eczane_title.text = getString(R.string.eczane_result_name, response.adi?.lowercase()?.replaceFirstChar { it.uppercase() })
    view.rootView.tv_result.text = response.adres
    bottomSheetDialog.setContentView(view)
    bottomSheetDialog.show()
}
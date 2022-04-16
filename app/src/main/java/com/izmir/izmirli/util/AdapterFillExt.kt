package com.izmir.izmirli.util

import android.content.Context
import android.widget.Adapter
import com.izmir.izmirli.adapter.IzmirAdapter
import com.izmir.izmirli.model.NobetciEczaneResponse
import kotlinx.coroutines.Dispatchers
import java.util.logging.Handler


fun IzmirAdapter.adapterFill(list: ArrayList<NobetciEczaneResponse.NobetciEczaneResponseItem>){

    this.setIzmirData(list)
}
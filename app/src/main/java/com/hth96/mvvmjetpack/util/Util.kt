package com.hth96.mvvmjetpack.util

import android.content.SharedPreferences
import android.widget.Button
import androidx.core.content.edit
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hth96.mvvmjetpack.resource.Resource
import java.text.SimpleDateFormat
import java.util.*

inline fun <reified T> SharedPreferences.saveItem(key: String, item: T?) {
    val json = Gson().toJson(item)
    edit { putString(key, json) }
}

inline fun <reified T> SharedPreferences.getItem(key: String): T? {
    val json = getString(key, "")
    if (!json.isNullOrBlank()) {
        val type = object : TypeToken<T>() {}.type
        return Gson().fromJson(json, type)
    }
    return null
}

inline fun <reified T> SharedPreferences.getList(spListKey: String): ArrayList<T>? {
    val listJson = getString(spListKey, "")
    if (!listJson.isNullOrBlank()) {
        val type = object : TypeToken<ArrayList<T>>() {}.type
        return Gson().fromJson(listJson, type)
    }
    return null
}

fun Date.formatDate() : String? {
    val dateTimeFormat = SimpleDateFormat("EEEE, dd MMMM", Locale.US)
    dateTimeFormat.timeZone = TimeZone.getTimeZone("UTC")
    return dateTimeFormat.format(this)
}

fun Date.formatTime() : String? {
    val dateTimeFormat = SimpleDateFormat("HH:mm:ss", Locale.US)
    dateTimeFormat.timeZone = TimeZone.getTimeZone("UTC")
    return dateTimeFormat.format(this)
}

@BindingAdapter("app:onClick", "app:key")
fun Button.onClick(check: (String?) -> Unit, key: String?) {
    setOnClickListener { check(key) }
}

fun<T: Any?> handleError(
    data: MutableLiveData<Resource<T>>,
    errorText: String) {
    ToastUtils.showShort(errorText)
    data.postValue(Resource.Error(errorText, data.value?.data))
}

fun<T: Any?> LifecycleOwner.handleError(result: MutableLiveData<Resource<T>>) {
    result.observe(this) {
        if (it is Resource.Error) {
            ToastUtils.showShort(it.message)
        }
    }
}
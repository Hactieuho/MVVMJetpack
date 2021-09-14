package com.hth96.mvvmjetpack.util

import android.content.SharedPreferences
import android.widget.Button
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.edit
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hth96.mvvmjetpack.R
import com.hth96.mvvmjetpack.resource.Resource
import java.text.SimpleDateFormat
import java.util.*

fun<T: Any?> handleError(
    data: MutableLiveData<Resource<T>>,
    errorText: String) {
    ToastUtils.showShort(errorText)
    data.postValue(Resource.Error(errorText, data.value?.data))
}

@BindingAdapter("app:userAvatar")
fun AppCompatImageView.userAvatar(url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.loading)
        .into(this)
}
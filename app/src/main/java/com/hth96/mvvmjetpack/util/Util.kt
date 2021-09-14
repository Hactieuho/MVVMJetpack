package com.hth96.mvvmjetpack.util

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.hth96.mvvmjetpack.resource.Resource

fun<T: Any?> handleError(
    data: MutableLiveData<Resource<T>>,
    errorText: String) {
    ToastUtils.showShort(errorText)
    data.postValue(Resource.Error(errorText, data.value?.data))
}
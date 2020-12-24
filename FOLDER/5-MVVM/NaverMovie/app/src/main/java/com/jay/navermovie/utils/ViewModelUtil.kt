package com.jay.navermovie.utils

import androidx.databinding.ObservableField

const val USER_ID = "1234"
const val USER_PW = "1234"
fun ObservableField<String>.getString(): String = get().toString().trim()

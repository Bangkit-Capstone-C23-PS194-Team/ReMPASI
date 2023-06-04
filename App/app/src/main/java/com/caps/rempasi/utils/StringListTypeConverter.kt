package com.caps.rempasi.utils

import androidx.room.TypeConverter

class StringListTypeConverter {
    @TypeConverter
    fun fromStringList(stringList: List<String>): String {
        return stringList.joinToString(";")
    }

    @TypeConverter
    fun toStringList(string: String): List<String> {
        return string.split(";")
    }
}
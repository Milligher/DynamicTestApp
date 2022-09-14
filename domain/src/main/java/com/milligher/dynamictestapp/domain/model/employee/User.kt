package com.milligher.dynamictestapp.domain.model.employee

data class User(
    val fullName: String,
    val position: String,
    val workHoursInMonth: Int,
    val workedOutHours: Int
)
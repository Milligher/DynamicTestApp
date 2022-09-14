package com.milligher.dynamictestapp.domain.model.configuration

data class Text(
    val attribute: String,
    val caption: String,
    val required: Boolean,
    val suggestions: List<String>,
    val type: String
)
package com.example.model

data class BibleVerse(
    val id: String,
    val reference: String,
    val text: String,
    val category: String,
    val context: String = ""
)

data class Prayer(
    val id: String,
    val title: String,
    val category: String,
    val text: String,
    val scripturalRef: String = ""
)

enum class TeachingCategory(val displayName: String) {
    LIFE("Life of Jesus"),
    TEACHINGS("Core Teachings"),
    PARABLES("Parables"),
    MIRACLES("Miracles")
}

data class TeachingItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: TeachingCategory,
    val scriptureRef: String,
    val summary: String,
    val fullContent: String,
    val keyLesson: String
)
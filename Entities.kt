package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_verses")
data class BookmarkedVerse(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val verseText: String,
    val reference: String,
    val category: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "saved_prayers")
data class SavedPrayer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val prayerText: String,
    val category: String,
    val isCustom: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "personal_notes")
data class PersonalNote(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val verseOrPrayerRef: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "plan_progress")
data class UserPlanProgress(
    @PrimaryKey val planId: String,
    val completedDaysCsv: String = "",
    val isCompleted: Boolean = false,
    val lastUpdated: Long = System.currentTimeMillis()
)

@Entity(tableName = "prayer_journal_entries")
data class PrayerJournalEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val prayerText: String,
    val category: String = "Petition",
    val tags: String = "#Supplication",
    val isAnswered: Boolean = false,
    val answeredNote: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val answeredTimestamp: Long = 0L
)
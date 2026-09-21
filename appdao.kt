package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    // Bookmarked Verses
    @Query("SELECT * FROM bookmarked_verses ORDER BY timestamp DESC")
    fun getAllBookmarkedVerses(): Flow<List<BookmarkedVerse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkedVerse(verse: BookmarkedVerse): Long

    @Delete
    suspend fun deleteBookmarkedVerse(verse: BookmarkedVerse)

    @Query("DELETE FROM bookmarked_verses WHERE reference = :reference")
    suspend fun deleteVerseByReference(reference: String)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_verses WHERE reference = :reference)")
    fun isVerseBookmarked(reference: String): Flow<Boolean>

    // Saved Prayers
    @Query("SELECT * FROM saved_prayers ORDER BY timestamp DESC")
    fun getAllSavedPrayers(): Flow<List<SavedPrayer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedPrayer(prayer: SavedPrayer): Long

    @Delete
    suspend fun deleteSavedPrayer(prayer: SavedPrayer)

    @Query("SELECT EXISTS(SELECT 1 FROM saved_prayers WHERE title = :title)")
    fun isPrayerSaved(title: String): Flow<Boolean>

    // Personal Notes
    @Query("SELECT * FROM personal_notes ORDER BY timestamp DESC")
    fun getAllPersonalNotes(): Flow<List<PersonalNote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPersonalNote(note: PersonalNote): Long

    @Update
    suspend fun updatePersonalNote(note: PersonalNote)

    @Delete
    suspend fun deletePersonalNote(note: PersonalNote)

    // Plan Progress
    @Query("SELECT * FROM plan_progress WHERE planId = :planId")
    fun getPlanProgress(planId: String): Flow<UserPlanProgress?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePlanProgress(progress: UserPlanProgress)

    // Prayer Journal
    @Query("SELECT * FROM prayer_journal_entries ORDER BY timestamp DESC")
    fun getAllPrayerJournalEntries(): Flow<List<PrayerJournalEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrayerJournalEntry(entry: PrayerJournalEntry): Long

    @Update
    suspend fun updatePrayerJournalEntry(entry: PrayerJournalEntry)

    @Delete
    suspend fun deletePrayerJournalEntry(entry: PrayerJournalEntry)
}
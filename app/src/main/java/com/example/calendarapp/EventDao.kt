package com.example.calendarapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventDao {

    @Insert
    suspend fun insert(event: Event)

    @Query("SELECT * FROM events WHERE timestamp BETWEEN :startOfDay AND :endOfDay")
    fun getEventsByDate(startOfDay: Long, endOfDay: Long): LiveData<List<Event>>

    @Query("SELECT * FROM events ORDER BY timestamp ASC")
    fun getAllEvents(): LiveData<List<Event>>

    @Query("DELETE FROM events WHERE id = :eventId")
    suspend fun deleteById(eventId: Long)

    // Assuming timestamp is a column in your events table
    @Query("SELECT * FROM events WHERE timestamp BETWEEN :start AND :end")
    fun getEventsBetweenTimestamps(start: Long, end: Long): LiveData<List<Event>>
}
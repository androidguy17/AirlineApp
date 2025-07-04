package com.example.airlineapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeysDao {

    @Query("SELECT * FROM remote_keys WHERE flightId = :flightId")
    suspend fun getRemoteKeysByFlightId(flightId: String): RemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeys: List<RemoteKeys>)

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()

    @Query("SELECT currentPage FROM remote_keys ORDER BY currentPage DESC LIMIT 1")
    suspend fun getLastPage(): Int?

    @Query("SELECT currentPage FROM remote_keys ORDER BY currentPage ASC LIMIT 1")
    suspend fun getFirstPage(): Int?
}
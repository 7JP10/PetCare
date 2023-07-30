package com.zybooks.petcare.repo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.*;
import com.zybooks.petcare.model.Pet;
import java.util.List;

@Dao
public interface PetDAO {
    @Query("SELECT * FROM Pet WHERE id = :id")
    Pet getPet(long id);

    @Query("SELECT * FROM Pet ORDER BY micro_id COLLATE NOCASE")
    List<Pet> getPets();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addPet(Pet pet);

    @Update
    void updatePet(Pet pet);

    @Delete
    void deletePet(Pet pet);
}

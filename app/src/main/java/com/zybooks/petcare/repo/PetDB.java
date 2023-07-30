package com.zybooks.petcare.repo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.zybooks.petcare.model.Pet;

@Database(entities = {Pet.class}, version = 1)
public abstract class PetDB extends RoomDatabase {
    public abstract PetDAO petDao();
}

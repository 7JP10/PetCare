package com.zybooks.petcare.repo;

import android.content.Context;
import androidx.room.Room;
import com.zybooks.petcare.model.Pet;
import java.util.List;

public class PetRepository {

    private static PetRepository mPetRepo;
    private final PetDAO mPetDao;

    public static PetRepository getInstance(Context context) {
        if (mPetRepo == null) {
            mPetRepo = new PetRepository(context);
        }
        return mPetRepo;
    }

    private PetRepository(Context context) {
        PetDB database = Room.databaseBuilder(context, PetDB.class, "petReg.db")
                .allowMainThreadQueries()
                .build();

        mPetDao = database.petDao();
    }

    public void addPet(Pet pet) {
        long subjectId = mPetDao.addPet(pet);
        pet.setId(subjectId);
    }

    public Pet getPet(long petId) {
        return mPetDao.getPet(petId);
    }

    public List<Pet> getPets() {
        return mPetDao.getPets();
    }

    public void deleteSubject(Pet pet) {
        mPetDao.deletePet(pet);
    }
}

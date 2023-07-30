package com.zybooks.petcare.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

@Entity
public class Pet {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;

    @ColumnInfo(name = "micro_id")
    private String mMicroId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "gender")
    private String mGender;

   @ColumnInfo(name = "email")
    private String mEmail;

    @ColumnInfo(name = "access_code")
    private String mCode;

    @ColumnInfo(name = "breed")
    private String mBreed;

    @ColumnInfo(name = "neutered")
    private String mNeutered;


    public Pet(@NonNull String mMicroId, String mName, String mGender, String mEmail, String mCode, String mBreed, String mNeutered) {
        this.mMicroId = mMicroId;
        this.mName = mName;
        this.mGender = mGender;
        this.mEmail = mEmail;
        this.mCode = mCode;
        this.mBreed = mBreed;
        this.mNeutered = mNeutered;
    }
    //**********DEFINITION*************

    public long getId() {
        return mId;
    }
    public void setId(long id) {
        mId = id;
    }
    /*******/
    public void setMicroId(String id) {
        mMicroId = id;
    }
    public String getMicroId() {
        return mMicroId;
    }
    /*******/
    public void setName(String name){
        mName = name;
    }
    public String getName(){
        return mName;
    }
    /*******/
    public void setGender(String gender){
        mGender = gender;
    }
    public String getGender(){
        return mGender;
    }
    /*******/
    public void setEmail(String email){
        mEmail = email;
    }
    public String getEmail(){
        return mEmail;
    }
    /*******/
    public void setCode(String code){
        mCode = code;
    }
    public String getCode(){
        return mCode;
    }
    /*******/
    public void setBreed(String breed){
        mBreed = breed;
    }
    public String getBreed(){
        return mBreed;
    }
    /*******/
    public void setNeutered(String neutered){
        mNeutered = neutered;
    }
    public String getNeutered(){
        return mNeutered;
    }
}

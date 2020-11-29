
package com.example.hello;
/*

import com.google.gson.annotations.SerializedName;

public class Pets {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("species")
    private String species;
    @SerializedName("breed")
    private String breed;
    @SerializedName("gender")
    private int gender;
    @SerializedName("birth")
    private String birth;
    @SerializedName("picture")
    private String picture;
    @SerializedName("love")
    private Boolean love;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getLove() {
        return love;
    }

    public void setLove(Boolean love) {
        this.love = love;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}

 */






public class Pets {
    private String PetName;
    private String PetSpecies;
    private String PetBreeds;
    private String PetBirthDate;           //-------add columns here-----------
    private String PetGender;
    private byte[] image;

    public Pets(String pName, String pSpecies,  String pBreeds, String pBirthDate, String pGender, byte[] image) {           //-------add columns here-----------
        PetName = pName;
        PetSpecies = pSpecies;
        PetBreeds = pBreeds;
        PetBirthDate = pBirthDate;           //-------add columns here-----------
        PetGender = pGender;
        this.image = image;
    }

    public String getPetName() {
        return PetName;
    }

    public void setPetName(String petName) {
        PetName = petName;
    }

    public String getPetSpecies() {
        return PetSpecies;
    }

    public void setPetSpecies(String petSpecies) {
        PetSpecies = petSpecies;
    }

    public String getPetBreeds() {
        return PetBreeds;
    }

    public void setPetBreeds(String petBreeds) {
        PetBreeds = petBreeds;
    }


    public String getPetBirthDate() {
        return PetBirthDate;
    }                                                  //-------add columns here-----------

    public void setPetBirthDate(String petBirthDate) {
        PetBirthDate = petBirthDate;
    }                                       //-------add columns here-----------

    public String getPetGender() {
        return PetGender;
    }                                                  //-------add columns here-----------

    public void setPetGender(String petGender) {
        PetGender = petGender;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}










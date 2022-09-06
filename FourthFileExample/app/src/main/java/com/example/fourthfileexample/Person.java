package com.example.fourthfileexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.Serializable;

public class Person implements Serializable {

    private String firstName;
    private String lastName;
    transient private Bitmap photo;

    public Person(String firstName, String lastName, Bitmap photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException{
        photo.compress(Bitmap.CompressFormat.JPEG,50,out);
        out.defaultWriteObject();
    }
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
        photo = BitmapFactory.decodeStream(in);
        in.defaultReadObject();
    }

}

package duj.app.signomo.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario {
    private String id;
    private String name;
    private String email;
    private String birthDate;
    private String birthTime;
    private String createdAt;
    private String updatedAt;

    public Usuario() {
    }

    public Usuario(String id, String name, String email, String birthDate, String birthTime, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.birthTime = birthTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(String birthTime) {
        this.birthTime = birthTime;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}

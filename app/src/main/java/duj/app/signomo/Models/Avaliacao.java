package duj.app.signomo.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Avaliacao implements Parcelable {
    private String usuario;
    private String descri;
    private String nota;
    private int id;

    public Avaliacao(int id, String usuario, String descri, String nota) {
        this.usuario = usuario;
        this.descri = descri;
        this.nota = nota;
        this.id=id;
    }



    protected Avaliacao(Parcel in) {
        usuario = in.readString();
        descri = in.readString();
        nota = in.readString();
        id = in.readInt();
    }

    public static final Creator<Avaliacao> CREATOR = new Creator<Avaliacao>() {
        @Override
        public Avaliacao createFromParcel(Parcel in) {
            return new Avaliacao(in);
        }

        @Override
        public Avaliacao[] newArray(int size) {
            return new Avaliacao[size];
        }
    };

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(usuario);
        parcel.writeString(descri);
        parcel.writeString(nota);
        parcel.writeInt(id);
    }
}

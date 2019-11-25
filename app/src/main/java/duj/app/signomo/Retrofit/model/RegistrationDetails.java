package duj.app.signomo.Retrofit.model;

public class RegistrationDetails {

    public RegistrationDetails() {
    }

    public RegistrationDetails(String name, String email, String password, String birthDate, String birthTime) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.birthTime = birthTime;
    }

    private String name;
    private String email;
    private String password;
    private String birthDate;
    private String birthTime;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}

package at.htlkaindorf.studentmanagementfinal;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable {
    private String name;
    private String schoolClass;
    private LocalDate birthday;

    // To exclude it from being serialized
    private transient Bitmap icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }
}

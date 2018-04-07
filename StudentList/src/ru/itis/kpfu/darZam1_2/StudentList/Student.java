/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.itis.kpfu.darZam1_2.StudentList;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.itis.kpfu.darZam1_2.StudentList.UI.JframeWindow;

/**
 *
 * @author danis_zam
 */
public class Student {

    private String name;
    private String gender;
    private int birthday;
    private double averageBall;

    public Student(String name, String gender, int age, double averageBall) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
        Pattern p = Pattern.compile("(f)");
        Matcher m = p.matcher(gender);
        Pattern p1 = Pattern.compile("[мm]");
        Matcher m1 = p1.matcher(gender);
        if (!m1.find() || m.find()) {
            this.gender = "female";
        } else {
            this.gender = "male";
        }
        if (age > 150) {
            this.birthday = 150;
        } else {
            this.birthday = age;
        }
        if (averageBall > 100) {
            this.averageBall = 100;
        } else {
            this.averageBall = averageBall;

        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + Objects.hashCode(this.gender);
        hash = 73 * hash + this.birthday;
        hash = (int) (73 * hash + this.averageBall);
        return hash;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", gender=" + gender + ", birthday=" + birthday + ", averageBall=" + averageBall + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.birthday != other.birthday) {
            return false;
        }
        if (this.averageBall != other.averageBall) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.gender, other.gender)) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getBirthday() {
        return birthday;
    }

    public double getAverageBall() {
        return averageBall;
    }

    public static void main(String[] args) {
        //Student s = new Student("lstya", "ежеж", 34, 34);
     //   ListOfStudent los = new ListOfStudent();
      //  los.addStudent(s);
       // los.saveListOfStudent();
       // los.readListOfStudent();
       JframeWindow jfr = new JframeWindow();
       jfr.setVisible(true);
    }

}

package com.muditasoft.part02;

import com.muditasoft.part01.model.LecturerCourseRecord;
import com.muditasoft.part01.model.StudentCourseRecord;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Student {
    private final String id;
    private final String name;
    private final String surname;
    private LocalDate birthDate;
    private Set<StudentCourseRecord> studentCourseRecords = new HashSet<>();

    public Student(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Student(String id, String name, String surname, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void addCourse(LecturerCourseRecord lecturerCourseRecord) {

        if (lecturerCourseRecord == null) {
            throw new IllegalArgumentException("Can't add course with null lecturer course record");
        }

        final StudentCourseRecord studentCourseRecord = new StudentCourseRecord(lecturerCourseRecord);
        studentCourseRecords.add(studentCourseRecord);
    }

    public Set<StudentCourseRecord> getStudentCourseRecords() {
        return studentCourseRecords;
    }

    public void setStudentCourseRecords(Set<StudentCourseRecord> studentCourseRecords) {
        this.studentCourseRecords = studentCourseRecords;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}

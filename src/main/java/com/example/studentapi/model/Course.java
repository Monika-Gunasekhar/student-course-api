package com.example.studentapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
    public class Course {

        @Id

        @Column(name = "course_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;

        private String description;

        // Constructors
        public Course() {}

        public Course(String title, String description) {
            this.title = title;
            this.description = description;
        }

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


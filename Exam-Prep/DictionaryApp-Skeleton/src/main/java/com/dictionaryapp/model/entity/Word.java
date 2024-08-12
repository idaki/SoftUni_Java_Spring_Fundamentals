package com.dictionaryapp.model.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String term;

    @Column(nullable = false, length = 80)
    private String translation;

    @Column(length = 200)
    private String example;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date inputDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User addedBy;


    public Long getId() {
        return id;
    }

    public Word setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTerm() {
        return term;
    }

    public Word setTerm(String term) {
        this.term = term;
        return this;
    }

    public String getTranslation() {
        return translation;
    }

    public Word setTranslation(String translation) {
        this.translation = translation;
        return this;
    }

    public String getExample() {
        return example;
    }

    public Word setExample(String example) {
        this.example = example;
        return this;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public Word setInputDate(Date inputDate) {
        this.inputDate = inputDate;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public Word setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public Word setAddedBy(User addedBy) {
        this.addedBy = addedBy;
        return this;
    }
}

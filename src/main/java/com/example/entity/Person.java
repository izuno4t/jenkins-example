package com.example.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 個人を表すエンティティです。
 */
@Entity
@Table(name = "people")
@Data
public class Person {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    private int id;

    /**
     * 名前
     */
    @Column(name = "name")
    private String name;

    /**
     * 性別
     */
    @Column(name = "gender")
    private String gender;

}

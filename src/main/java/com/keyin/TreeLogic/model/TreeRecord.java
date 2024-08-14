package com.keyin.TreeLogic.model;

import jakarta.persistence.*;

@Entity
public class TreeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private int[] inputNumbers;

    @Lob
    private String treeJson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int[] getInputNumbers() {
        return inputNumbers;
    }

    public void setInputNumbers(int[] inputNumbers) {
        this.inputNumbers = inputNumbers;
    }

    public String getTreeJson() {
        return treeJson;
    }

    public void setTreeJson(String treeJson) {
        this.treeJson = treeJson;
    }
}

package com.lilei.boot.pojo;

import lombok.Data;

@Data
public class Record {
    private String name;

    public Record(String name) {
        this.name = name;
    }
}

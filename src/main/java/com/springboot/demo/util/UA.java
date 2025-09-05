package com.springboot.demo.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UA {
    private int id;
    private String vv;

    public UA(int i) {
        this.id = i;
    }
}

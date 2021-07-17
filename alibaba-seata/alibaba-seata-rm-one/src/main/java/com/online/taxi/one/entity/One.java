package com.online.taxi.one.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class One implements Serializable {
    private static final long serialVersionUID = -7337602401908085773L;

    private Integer id;

    private String name;
}

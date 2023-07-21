package com.example.lamp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bible {
    private int idx;
    private int cate;
    private int book;
    private int chapter;
    private int paragraph;
    private String sentence;
    private String testament;
    private String long_label;
    private String short_label;
}

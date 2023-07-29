package com.example.lamp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paper {
    int idx;
    int prev_idx;
    Date date;
    String pdf;
    String npray;
    String nresoponsive;
    String noffering;
}

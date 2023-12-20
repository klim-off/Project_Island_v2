package com.klimov.entities;

import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Getter
@Setter
public abstract class Entity {
    private double weight;
    private int maxOnCage;
    private String unicode;
}

package com.klimov.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EntityType {
    WOLF (""),
    SNAKE (""),
    FOX(""),
    HORSE(""),
    DEAR(""),
    SHEEP(""),
    PLANT("");
    private final String unicodeSymbol;

}

package com.example.rodem.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Room {
    private Long id;
    private String name;

    @Builder
    public Room(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

package com.app.fuzzy.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "demo_list")
public class fuzzySearch {

    @Id
    private int id;
    private String name;
}

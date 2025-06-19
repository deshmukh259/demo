package com.springboot.demo.controller;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.springboot.demo.serializer.CamelToSnakeCaseDeserializer;
import com.springboot.demo.serializer.SnakeToCamelCaseSerializer;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // or AUTO
    private Long id;

    @Column
    private String itemName;


    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    @JsonSerialize(using = SnakeToCamelCaseSerializer.class)
    @JsonDeserialize(using = CamelToSnakeCaseDeserializer.class)
    private Map<String, Object> config;

    @Version
    private Integer version;
}

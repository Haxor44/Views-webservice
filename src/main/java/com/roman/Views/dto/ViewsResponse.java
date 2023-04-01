package com.roman.Views.dto;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "maid")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewsResponse {
    private int id;
    private String name;
    private String email;
    private String loe;
    private String phone;
}

package com.example.InventoryManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long productId;
    private String productName;
    private String productDescription;
    private String productPrice;
    private LocalDate productBuyDate;
    private LocalDate productExpiryDate;
}

package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.repository.ProductRepository;
import com.example.InventoryManagement.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Map<String,Object>> addProduct(@RequestBody @Valid Product product){
        productService.createProduct(product);
        Map<String,Object> map=new HashMap<>();
        map.put("message","Product created successfully");
        return new ResponseEntity<>(map,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        if (productService.getProductById(id) == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        if (productService.getProductById(id) == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.getProductById(id) == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build(); // 204
    }

}


package com.shop.display.interfaces;

import com.shop.display.application.ProductService;
import com.shop.display.interfaces.model.CUDResponse;
import com.shop.display.interfaces.model.ProductCommand;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final ProductService productService;
    public static final String V1_PREFIX = "/v1";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PutMapping(V1_PREFIX + "/products/new")
    public CUDResponse addProduct(@Valid @RequestBody ProductCommand command) {
        productService.save(command);
        return new CUDResponse("Product added successfully");
    }

    @PostMapping(V1_PREFIX + "/products/{id}")
    public CUDResponse updateProduct(@PathVariable Long id, @Valid @RequestBody ProductCommand command) {
        productService.update(id ,command);
        return new CUDResponse("Product updated successfully");
    }

    @DeleteMapping(V1_PREFIX + "/products/{id}")
    public CUDResponse deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return new CUDResponse("Product deleted successfully");
    }
}

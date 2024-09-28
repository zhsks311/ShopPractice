package com.shop.display.interfaces;

import com.shop.display.application.BrandService;
import com.shop.display.application.LowestPriceService;
import com.shop.display.interfaces.model.BrandCommand;
import com.shop.display.interfaces.model.BrandLowestPriceResponse;
import com.shop.display.interfaces.model.CUDResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrandController {
    private final LowestPriceService lowestPriceService;
    private final BrandService brandService;
    public static final String V1_PREFIX = "/v1";

    public BrandController(LowestPriceService lowestPriceService,
                           BrandService brandService) {
        this.lowestPriceService = lowestPriceService;
        this.brandService = brandService;
    }

    @GetMapping(V1_PREFIX + "/brands/all/lowest")
    public BrandLowestPriceResponse getBrandLowestPrice() {
        return lowestPriceService.findBrandLowestPrice();
    }

    @PostMapping(V1_PREFIX + "/brands/new")
    public CUDResponse addBrand(@Valid @RequestBody BrandCommand command) {
        brandService.save(command);
        return new CUDResponse("Brand added successfully");
    }

    @PostMapping(V1_PREFIX + "/brands/{id}")
    public CUDResponse updateProduct(@Valid @PathVariable @NotNull Long id,
                                     @Valid @RequestBody BrandCommand command) {
        brandService.update(id, command);
        return new CUDResponse("Brand updated successfully");
    }

    @DeleteMapping(V1_PREFIX + "/brands/{id}")
    public CUDResponse deleteProduct(@Valid @PathVariable @NotNull Long id) {
        brandService.delete(id);
        return new CUDResponse("Brand deleted successfully");
    }
}

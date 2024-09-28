package com.shop.display.interfaces.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record ProductCommand(@NotEmpty String brand, @NotEmpty String category, @Min(0) Long price) {
}

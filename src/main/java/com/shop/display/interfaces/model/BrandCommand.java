package com.shop.display.interfaces.model;

import jakarta.validation.constraints.NotEmpty;

public record BrandCommand(@NotEmpty String name) {
}

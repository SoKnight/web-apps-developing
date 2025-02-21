package me.soknight.studying.hosting.model;

import jakarta.validation.constraints.*;

public record ProductDto(
        @NotEmpty String name,
        @NotEmpty String processor,
        @NotEmpty String protection,
        @NotEmpty String vcore,
        @NotEmpty String ram,
        @NotEmpty String storage,
        @NotEmpty String network,
        @NotNull Integer price
) {

}

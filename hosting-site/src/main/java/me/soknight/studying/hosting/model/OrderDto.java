package me.soknight.studying.hosting.model;

import jakarta.validation.constraints.*;

public record OrderDto(
        @NotEmpty @Size(min = 2, max = 64) String name,
        @NotEmpty @Email @Size(min = 5, max = 64) String email,
        @NotEmpty @Size(min = 11, max = 11) String phoneNumber,
        @NotEmpty String country,
        @NotEmpty @Size(min = 6, max = 6) String postIndex,
        @NotEmpty @Size(min = 2, max = 32) String city,
        @NotEmpty @Size(min = 4, max = 64) String cityAddress,
        @NotNull @Min(1) Integer id,
        @NotNull @Min(1) @Max(10) Integer quantity,
        @Size(max = 320) String comment
) {

}

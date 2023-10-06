package com.ase.notification_ms.DTOs;

public record LocationDTO(
   Long id,
   Integer streetNumber,
   String street,
   Integer zip,
   String city,
   String country
) {

}

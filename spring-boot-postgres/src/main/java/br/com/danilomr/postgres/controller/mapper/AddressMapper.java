package br.com.danilomr.postgres.controller.mapper;

import br.com.danilomr.postgres.controller.dto.AddressDTO;
import br.com.danilomr.postgres.entity.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddressMapper {

    public static List<AddressDTO> toDTO(final List<Address> addresses) {
        return addresses.stream()
                .map(AddressMapper::toDTO)
                .collect(Collectors.toList());
    }

    private static AddressDTO toDTO(final Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .state(address.getState())
                .country(address.getCountry())
                .build();
    }

    public static List<Address> toEntity(final List<AddressDTO> addresses) {
        return Optional.ofNullable(addresses)
                .map(list -> list.stream()
                        .map(AddressMapper::toEntity)
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

    private static Address toEntity(final AddressDTO address) {
        return Address.builder()
                .id(address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .state(address.getState())
                .country(address.getCountry())
                .build();
    }
}

package com.senlainc.dto.mapper;

import com.senlainc.dto.currency.CurrencyDto;
import com.senlainc.entity.Currency;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CurrencyDtoMapper {
    public static CurrencyDto convertEntityToDto(Currency source){
        return CurrencyDto.builder()
                .coefficient(source.getCoefficient())
                .build();
    }
}

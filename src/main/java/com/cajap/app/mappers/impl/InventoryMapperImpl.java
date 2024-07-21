package com.cajap.app.mappers.impl;

import com.cajap.app.domain.dto.InventoryDto;
import com.cajap.app.domain.entities.InventoryEntity;
import com.cajap.app.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapperImpl implements Mapper<InventoryEntity, InventoryDto> {

    private ModelMapper modelMapper;


    public InventoryMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public InventoryDto mapTo(InventoryEntity inventoryEntity) {
        return modelMapper.map(inventoryEntity, InventoryDto.class);
    }

    @Override
    public InventoryEntity mapFrom(InventoryDto inventoryDto) {
        return modelMapper.map(inventoryDto, InventoryEntity.class);
    }
}

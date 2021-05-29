package com.project.bootcamp.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;

@Component
public class StockMapper {

	public Stock toEntity(StockDTO dto) {
		Stock stock = new Stock();
		stock.setDate(dto.getData());
		stock.setId(dto.getId());
		stock.setName(dto.getName());
		stock.setPrice(dto.getPrice());
		stock.setVariation(dto.getVariation());
		return stock;
	}

	public StockDTO toDTO(Stock stock) {
		StockDTO dto = new StockDTO();
		dto.setData(stock.getDate());
		dto.setId(stock.getId());
		dto.setName(stock.getName());
		dto.setPrice(stock.getPrice());
		dto.setVariation(stock.getVariation());
		return dto;
	}

	public List<StockDTO> toDTO(List<Stock> listStock) {
		return listStock.stream().map(this::toDTO).collect(Collectors.toList());
	}

}

package com.project.bootcamp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bootcamp.exceptions.BusinessException;
import com.project.bootcamp.exceptions.NotFoundException;
import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import com.project.bootcamp.util.MessageUtils;

@Service
public class StockService {
	@Autowired
	private StockRepository repository;
	@Autowired
	private StockMapper mapper;

	@Transactional
	public StockDTO salvar(StockDTO dto) {

		Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getData());

		if (optionalStock.isPresent()) {
			throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
		}
		Stock stock = mapper.toEntity(dto);
		repository.save(stock);

		return mapper.toDTO(stock);
	}

	@Transactional
	public StockDTO update(@Valid StockDTO dto) {
		Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getId(), dto.getName(), dto.getData());

		if (optionalStock.isPresent()) {
			throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
		}
		Stock stock = mapper.toEntity(dto);
		repository.save(stock);

		return mapper.toDTO(stock);
	}

	@Transactional(readOnly = true)
	public List<StockDTO> findAll() {
		return mapper.toDTO(repository.findAll());
	}
	@Transactional(readOnly = true)
	public StockDTO findById(Long id) {
		return repository.findById(id).map(mapper::toDTO).orElseThrow(NotFoundException::new);
	}

	@Transactional
	public StockDTO delete(Long id) {
		StockDTO dto = this.findById(id);
		repository.deleteById(id);
		return dto;
	}

	@Transactional(readOnly = true)
	public List<StockDTO> findByToday() {
		return repository.findByToday(LocalDate.now()).map(mapper::toDTO).orElseThrow(NotFoundException::new);
	}
}

package com.project.bootcamp.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.bootcamp.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{

	public Optional<Stock> findByNameAndDate(String name, LocalDate data);

	@Query("select stock from Stock stock where stock.id <> ?1 and stock.name = ?2 and stock.date = ?3")
	public Optional<Stock> findByStockUpdate(Long id, String name, LocalDate data);
	
	@Query("select stock from Stock stock where stock.date = ?1")
	public Optional<List<Stock>> findByToday(LocalDate date);

}

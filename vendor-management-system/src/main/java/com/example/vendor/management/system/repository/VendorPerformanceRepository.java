package com.example.vendor.management.system.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.vendor.management.system.model.VendorPerformanceHistory;

public interface VendorPerformanceRepository extends JpaRepository<VendorPerformanceHistory, Long> {

	@Query("select e from VendorPerformanceHistory e where DATE(e.date)>:resultDate AND e.vendor.id=:id")

	List<VendorPerformanceHistory> getPerformanceHistoryOfVendorsByDays(LocalDate resultDate, Long id);

}

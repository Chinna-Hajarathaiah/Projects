package com.example.vendor.management.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vendor.management.system.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>{
	List<PurchaseOrder> findByVendorId(Long vendorId);
    List<PurchaseOrder> findByVendorIdAndStatus(Long vendorId, String status);

}

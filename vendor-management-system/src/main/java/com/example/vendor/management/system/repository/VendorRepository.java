package com.example.vendor.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vendor.management.system.model.Vendor;
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

}

package com.example.vendor.management.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.vendor.management.system.model.Vendor;
import com.example.vendor.management.system.model.VendorPerformanceHistory;
import com.example.vendor.management.system.service.VendorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@PostMapping("/createVendor")
	public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
		Vendor createdVendor = vendorService.saveVendor(vendor);
		return new ResponseEntity<>(createdVendor, HttpStatus.CREATED);
	}

	@GetMapping("/listAllVendors")
	public ResponseEntity<List<Vendor>> listAllVendors() {
		List<Vendor> vendors = vendorService.getAllVendors();
		return new ResponseEntity<>(vendors, HttpStatus.OK);
	}

	@GetMapping("/{vendorId}")
	public ResponseEntity<Vendor> getVendorById(@PathVariable Long vendorId) {
		Optional<Vendor> vendor = vendorService.getVendorById(vendorId);
		if (vendor.isPresent()) {
			return new ResponseEntity<>(vendor.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{vendorId}")
	public ResponseEntity<Vendor> updateVendor(@PathVariable Long vendorId, @RequestBody Vendor vendorDetails) {
		Optional<Vendor> updatedVendor = vendorService.updateVendor(vendorId, vendorDetails);
		if (updatedVendor.isPresent()) {
			return new ResponseEntity<>(updatedVendor.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{vendorId}")
    public ResponseEntity<String> deleteVendor(@PathVariable Long vendorId) {
        boolean isDeleted = vendorService.deleteVendor(vendorId);
        if (isDeleted) {
            return new ResponseEntity<>("vendor deleted succesfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       
        }
	}
	@GetMapping("/{vendorId}/performance")
    public ResponseEntity<Vendor> getVendorPerformance(@PathVariable Long vendorId) {
        try {
            Vendor vendor = vendorService.calculateVendorPerformance(vendorId);
            return new ResponseEntity<>(vendor, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@PostMapping("savePerformaceHistory/{vendorId}")
	public VendorPerformanceHistory savePerformanceHistory(@PathVariable Long vendorId, @RequestBody VendorPerformanceHistory history)
	{
		
		 return vendorService.savePerformanceHistory(history);
	}
	@GetMapping("/getPerformanceHistoryOfVendors/{id}/{days}")
	public List<VendorPerformanceHistory> getPerformanceHistoryOfVendorsByDays(@PathVariable int days, @PathVariable Long id)
	{
		return vendorService.getPerformanceHistoryOfVendorsByDays(days, id);
	}
	
	
	
    
}

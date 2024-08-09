package com.example.vendor.management.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vendor.management.system.model.PurchaseOrder;
import com.example.vendor.management.system.model.Vendor;
import com.example.vendor.management.system.model.VendorPerformanceHistory;
import com.example.vendor.management.system.repository.PurchaseOrderRepository;
import com.example.vendor.management.system.repository.VendorPerformanceRepository;
import com.example.vendor.management.system.repository.VendorRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

	@Autowired
	private VendorRepository vendorRepository;
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	private VendorPerformanceRepository vendorPerformanceRepository;

	public Vendor saveVendor(Vendor vendor) {
		return vendorRepository.save(vendor);
	}

	public List<Vendor> getAllVendors() {
		return vendorRepository.findAll();
	}

	public Optional<Vendor> getVendorById(Long vendorId) {
		return vendorRepository.findById(vendorId);
	}

	public Optional<Vendor> updateVendor(Long vendorId, Vendor vendorDetails) {
		return vendorRepository.findById(vendorId).map(vendor -> {
			vendor.setName(vendorDetails.getName());
			vendor.setContactDetails(vendorDetails.getContactDetails());
			vendor.setAddress(vendorDetails.getAddress());
			vendor.setVendorCode(vendorDetails.getVendorCode());
			vendor.setOnTimeDeliveryRate(vendorDetails.getOnTimeDeliveryRate());
			vendor.setQualityRatingAvg(vendorDetails.getQualityRatingAvg());
			vendor.setAverageResponseTime(vendorDetails.getAverageResponseTime());
			vendor.setFulfillmentRate(vendorDetails.getFulfillmentRate());
			return vendorRepository.save(vendor);
		});
	}

	public boolean deleteVendor(Long vendorId) {
		return vendorRepository.findById(vendorId).map(vendor -> {
			vendorRepository.delete(vendor);
			return true;
		}).orElse(false);
	}

	public void updateOnTimeDeliveryRate(Long vendorId) {
        List<PurchaseOrder> completedOrders = purchaseOrderRepository.findByVendorIdAndStatus(vendorId, "completed");
        long totalCompleted = completedOrders.size();
        long onTimeCompleted = completedOrders.stream()
                .filter(po -> po.getDeliveryDate() != null && !po.getDeliveryDate().isAfter(po.getOrderDate()))
                .count();
        float rate = totalCompleted > 0 ? (float) onTimeCompleted / totalCompleted * 100 : 0;
        vendorRepository.findById(vendorId).ifPresent(vendor -> {
            vendor.setOnTimeDeliveryRate(rate);
            vendorRepository.save(vendor);
        });
    }

    public void updateQualityRatingAvg(Long vendorId) {
        List<PurchaseOrder> completedOrders = purchaseOrderRepository.findByVendorIdAndStatus(vendorId, "completed");
        double avgQualityRating = completedOrders.stream()
                .filter(po -> po.getQualityRating() != null)
                .mapToDouble(PurchaseOrder::getQualityRating)
                .average()
                .orElse(0);
        vendorRepository.findById(vendorId).ifPresent(vendor -> {
            vendor.setQualityRatingAvg((float) avgQualityRating);
            vendorRepository.save(vendor);
        });
    }

    public void updateAverageResponseTime(Long vendorId) {
        List<PurchaseOrder> allOrders = purchaseOrderRepository.findByVendorId(vendorId);
        double avgResponseTime = allOrders.stream()
                .filter(po -> po.getAcknowledgmentDate() != null)
                .mapToDouble(po -> java.time.Duration.between(po.getIssueDate(), po.getAcknowledgmentDate()).toHours())
                .average()
                .orElse(0);
        vendorRepository.findById(vendorId).ifPresent(vendor -> {
            vendor.setAverageResponseTime((float) avgResponseTime);
            vendorRepository.save(vendor);
        });
    }

    public void updateFulfillmentRate(Long vendorId) {
        List<PurchaseOrder> allOrders = purchaseOrderRepository.findByVendorId(vendorId);
        long totalOrders = allOrders.size();
        long completedOrders = allOrders.stream().filter(po -> "completed".equals(po.getStatus())).count();
        float rate = totalOrders > 0 ? (float) completedOrders / totalOrders * 100 : 0;
        vendorRepository.findById(vendorId).ifPresent(vendor -> {
            vendor.setFulfillmentRate(rate);
            vendorRepository.save(vendor);
        });
    }

    public Vendor calculateVendorPerformance(Long vendorId) {
        updateOnTimeDeliveryRate(vendorId);
        updateQualityRatingAvg(vendorId);
        updateAverageResponseTime(vendorId);
        updateFulfillmentRate(vendorId);
        return vendorRepository.findById(vendorId).orElseThrow(() -> new RuntimeException("Vendor not found"));
    }
    
    public VendorPerformanceHistory savePerformanceHistory(VendorPerformanceHistory history) {
    	    	 return vendorPerformanceRepository.save(history);
		
    }

	public List<VendorPerformanceHistory> getPerformanceHistoryOfVendorsByDays(int days, Long id) {
		 LocalDate resultDate = convertDaysToDate(days);
		return vendorPerformanceRepository.getPerformanceHistoryOfVendorsByDays(resultDate, id);
		
	}
	private LocalDate convertDaysToDate(int days) {
//		Calendar calendar = Calendar.getInstance();
		return LocalDate.now().minusDays(days);
	}

    
    
   
}

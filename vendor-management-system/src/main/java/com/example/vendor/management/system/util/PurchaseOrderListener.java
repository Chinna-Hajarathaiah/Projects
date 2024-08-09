package com.example.vendor.management.system.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.vendor.management.system.model.PurchaseOrder;
import com.example.vendor.management.system.model.Vendor;
import com.example.vendor.management.system.service.VendorService;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;

@Component
public class PurchaseOrderListener {

    @Autowired
    private VendorService vendorService;

    @PostPersist
    @PostUpdate
    public void afterSaveOrUpdate(PurchaseOrder purchaseOrder) {
        Vendor vendor = purchaseOrder.getVendor();
        vendorService.updateOnTimeDeliveryRate(vendor.getId());
        vendorService.updateQualityRatingAvg(vendor.getId());
        vendorService.updateAverageResponseTime(vendor.getId());
        vendorService.updateFulfillmentRate(vendor.getId());
    	
    }
}

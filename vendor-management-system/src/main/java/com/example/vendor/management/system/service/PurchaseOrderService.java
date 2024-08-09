package com.example.vendor.management.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vendor.management.system.model.PurchaseOrder;
import com.example.vendor.management.system.repository.PurchaseOrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    public List<PurchaseOrder> getPurchaseOrdersByVendor(Long vendorId) {
        return purchaseOrderRepository.findByVendorId(vendorId);
    }

    public Optional<PurchaseOrder> getPurchaseOrderById(Long poId) {
        return purchaseOrderRepository.findById(poId);
    }

    public Optional<PurchaseOrder> updatePurchaseOrder(Long poId, PurchaseOrder purchaseOrderDetails) {
        return purchaseOrderRepository.findById(poId).map(purchaseOrder -> {
            purchaseOrder.setPoNumber(purchaseOrderDetails.getPoNumber());
            purchaseOrder.setVendor(purchaseOrderDetails.getVendor());
            purchaseOrder.setOrderDate(purchaseOrderDetails.getOrderDate());
            purchaseOrder.setDeliveryDate(purchaseOrderDetails.getDeliveryDate());
            purchaseOrder.setItems(purchaseOrderDetails.getItems());
            purchaseOrder.setQuantity(purchaseOrderDetails.getQuantity());
            purchaseOrder.setStatus(purchaseOrderDetails.getStatus());
            purchaseOrder.setQualityRating(purchaseOrderDetails.getQualityRating());
            purchaseOrder.setIssueDate(purchaseOrderDetails.getIssueDate());
            purchaseOrder.setAcknowledgmentDate(purchaseOrderDetails.getAcknowledgmentDate());
            return purchaseOrderRepository.save(purchaseOrder);
        });
    }

    public boolean deletePurchaseOrder(Long poId) {
        return purchaseOrderRepository.findById(poId).map(purchaseOrder -> {
            purchaseOrderRepository.delete(purchaseOrder);
            return true;
        }).orElse(false);
    }
}

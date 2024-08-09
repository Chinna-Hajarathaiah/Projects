package com.example.vendor.management.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.vendor.management.system.model.PurchaseOrder;
import com.example.vendor.management.system.service.PurchaseOrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/purchase_orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping("/createPurchaseOrder")
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        PurchaseOrder createdPurchaseOrder = purchaseOrderService.savePurchaseOrder(purchaseOrder);
        return new ResponseEntity<>(createdPurchaseOrder, HttpStatus.CREATED);
    }

    @GetMapping("/getAllPurchaseOrders")
    public ResponseEntity<List<PurchaseOrder>> listAllPurchaseOrders(@RequestParam(required = false) Long vendorId) {
        List<PurchaseOrder> purchaseOrders;
        if (vendorId != null) {
            purchaseOrders = purchaseOrderService.getPurchaseOrdersByVendor(vendorId);
        } else {
            purchaseOrders = purchaseOrderService.getAllPurchaseOrders();
        }
        return new ResponseEntity<>(purchaseOrders, HttpStatus.OK);
    }

    @GetMapping("/{poId}")
    public ResponseEntity<PurchaseOrder> getPurchaseOrderById(@PathVariable Long poId) {
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderService.getPurchaseOrderById(poId);
        return purchaseOrder.map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{poId}")
    public ResponseEntity<PurchaseOrder> updatePurchaseOrder(@PathVariable Long poId, @RequestBody PurchaseOrder purchaseOrderDetails) {
        Optional<PurchaseOrder> updatedPurchaseOrder = purchaseOrderService.updatePurchaseOrder(poId, purchaseOrderDetails);
        return updatedPurchaseOrder.map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{poId}")
    public ResponseEntity<Void> deletePurchaseOrder(@PathVariable Long poId) {
        boolean isDeleted = purchaseOrderService.deletePurchaseOrder(poId);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

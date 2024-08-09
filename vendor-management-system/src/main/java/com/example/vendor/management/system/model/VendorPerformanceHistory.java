package com.example.vendor.management.system.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendor_performance_history")
public class VendorPerformanceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "on_time_delivery_rate", nullable = false)
    private float onTimeDeliveryRate;

    @Column(name = "quality_rating_avg", nullable = false)
    private float qualityRatingAvg;

    @Column(name = "average_response_time", nullable = false)
    private float averageResponseTime;

    @Column(name = "fulfillment_rate", nullable = false)
    private float fulfillmentRate;

    // Getters and Setters
    
    public VendorPerformanceHistory() {}

    public VendorPerformanceHistory(Vendor vendor, LocalDateTime date, float onTimeDeliveryRate, float qualityRatingAvg, float averageResponseTime, float fulfillmentRate) {
        this.vendor = vendor;
        this.date = date;
        this.onTimeDeliveryRate = onTimeDeliveryRate;
        this.qualityRatingAvg = qualityRatingAvg;
        this.averageResponseTime = averageResponseTime;
        this.fulfillmentRate = fulfillmentRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public float getOnTimeDeliveryRate() {
        return onTimeDeliveryRate;
    }

    public void setOnTimeDeliveryRate(float onTimeDeliveryRate) {
        this.onTimeDeliveryRate = onTimeDeliveryRate;
    }

    public float getQualityRatingAvg() {
        return qualityRatingAvg;
    }

    public void setQualityRatingAvg(float qualityRatingAvg) {
        this.qualityRatingAvg = qualityRatingAvg;
    }

    public float getAverageResponseTime() {
        return averageResponseTime;
    }

    public void setAverageResponseTime(float averageResponseTime) {
        this.averageResponseTime = averageResponseTime;
    }

    public float getFulfillmentRate() {
        return fulfillmentRate;
    }

    public void setFulfillmentRate(float fulfillmentRate) {
        this.fulfillmentRate = fulfillmentRate;
    }

	
}


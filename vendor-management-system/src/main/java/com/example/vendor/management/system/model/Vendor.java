package com.example.vendor.management.system.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendors")
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "contact_details", columnDefinition = "TEXT")
	private String contactDetails;

	@Column(name = "address", columnDefinition = "TEXT")
	private String address;

	@Column(name = "vendor_code", unique = true, nullable = false)
	private String vendorCode;

	@Column(name = "on_time_delivery_rate", nullable = false)
	private float onTimeDeliveryRate;

	@Column(name = "quality_rating_avg", nullable = false)
	private float qualityRatingAvg;

	@Column(name = "average_response_time", nullable = false)
	private float averageResponseTime;

	@Column(name = "fulfillment_rate", nullable = false)
	private float fulfillmentRate;

	// Getters and Setters

//	@OneToMany(mappedBy = "vendor")
//	private List<PurchaseOrder> purchaseOrders;
	
	 public Vendor() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public List<PurchaseOrder> getPurchaseOrders() {
//		return purchaseOrders;
//	}
//
//	public void setPurchaseOrders(List<PurchaseOrder> purchaseOrders) {
//		this.purchaseOrders = purchaseOrders;
//	}
//	@OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<VendorPerformanceHistory> performanceHistory;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
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

//	public List<VendorPerformanceHistory> getPerformanceHistory() {
//		return performanceHistory;
//	}
//
//	public void setPerformanceHistory(List<VendorPerformanceHistory> performanceHistory) {
//		this.performanceHistory = performanceHistory;
//	}
}

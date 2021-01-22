package com.eagleshipperapi.bean;

public class Bid {
	private String bidId;
	private String transporterId;
	private String leadId;
	private String transporterName;
	private int amount;
	private String remark;
	private String estimatedDate;
	private String materialType;
	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public Bid() {
	}

	public Bid(String bidId, String transporterId, String leadId, String transporterName, int amount, String remark,
			String estimatedDate) {
		super();
		this.bidId = bidId;
		this.transporterId = transporterId;
		this.leadId = leadId;
		this.transporterName = transporterName;
		this.amount = amount;
		this.remark = remark;
		this.estimatedDate = estimatedDate;
	}

	public String getBidId() {
		return bidId;
	}

	public void setBidId(String bidId) {
		this.bidId = bidId;
	}

	public String getTransporterId() {
		return transporterId;
	}

	public void setTransporterId(String transporterId) {
		this.transporterId = transporterId;
	}

	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getTransporterName() {
		return transporterName;
	}

	public void setTransporterName(String transporterName) {
		this.transporterName = transporterName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEstimatedDate() {
		return estimatedDate;
	}

	public void setEstimatedDate(String estimatedDate) {
		this.estimatedDate = estimatedDate;
	}

}

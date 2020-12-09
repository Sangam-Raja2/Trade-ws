package com.sangam.trading.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * @author Sangam
 */
public class TradeModel {
	private Long tradeId;
	private String type;
	private String symbol;
	private String shares;
	private double price;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date timestamp;
	private UserModel user;

	public TradeModel(long tradeId, String type, String symbol, String shares, double price, UserModel user) {

		this.tradeId = tradeId;
		this.type = type;
		this.symbol = symbol;
		this.shares = shares;
		this.price = price;
		this.user = user;
	}

	public TradeModel() {

	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getShares() {
		return shares;
	}

	public void setShares(String shares) {
		this.shares = shares;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

}

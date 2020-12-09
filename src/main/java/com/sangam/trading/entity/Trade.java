
package com.sangam.trading.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * @author Sangam
 */
@Entity
@Table(name = "trade")
@XmlRootElement
public class Trade implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "trade_id")
	private Long tradeId;
	@Basic(optional = false)
	@Column(name = "type")
	private String type;
	@Column(name = "symbol")
	private String symbol;
	@Column(name = "shares")
	private String shares;
	@Column(name = "price")
	private Double price;
	@Column(name = "timestamp")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date timestamp;
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@JsonBackReference
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REMOVE }, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User userId;

	public Trade() {
	}

	public Trade(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Trade(Long tradeId, String type) {
		this.tradeId = tradeId;
		this.type = type;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tradeId != null ? tradeId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Trade)) {
			return false;
		}
		Trade other = (Trade) object;
		if ((this.tradeId == null && other.tradeId != null)
				|| (this.tradeId != null && !this.tradeId.equals(other.tradeId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.cinema.ws.app.entity.Trade[ tradeId=" + tradeId + " ]";
	}

}

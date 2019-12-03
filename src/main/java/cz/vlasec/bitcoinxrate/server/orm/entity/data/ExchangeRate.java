package cz.vlasec.bitcoinxrate.server.orm.entity.data;

import cz.vlasec.bitcoinxrate.server.orm.entity.config.ExchangeInformation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * This entity stores exchange rate retrieved from an external source.
 */
@Entity
@Table(name = "dta_exchange_rate")
public class ExchangeRate {
	private Long id;
	private ExchangeInformation exchange;
	private BigDecimal rate;
	private Instant timestamp;

	/** Numeric identifier of the entity. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	public Long getId() {
		return id;
	}
	/** Numeric identifier of the entity. */
	public void setId(Long id) {
		this.id = id;
	}

	/** The exchange this rate is for. */
	@JoinColumn(name = "exchange__cfg_exchange_info", nullable = false, updatable = false, referencedColumnName = "id")
	@ManyToOne(cascade = CascadeType.ALL)
	public ExchangeInformation getExchange() {
		return exchange;
	}
	/** The exchange this rate is for. */
	public void setExchange(ExchangeInformation exchange) {
		this.exchange = exchange;
	}

	/** The exchange rate. */
	@Column(nullable = false, updatable = false)
	public BigDecimal getRate() {
		return rate;
	}
	/** The exchange rate. */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	/** The timestamp as provided by external source. */
	@Column( nullable = false, updatable = false)
	public Instant getTimestamp() {
		return timestamp;
	}

	/** The timestamp as provided by external source. */
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
}

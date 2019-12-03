package cz.vlasec.bitcoinxrate.server.orm.entity.config;

import cz.vlasec.bitcoinxrate.server.atom.ExchangeRateSource;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Information about the exchange - contains the currency context for exchange rates, most importantly.
 * There could be also information about data source for the course - but it is currently hardcoded.
 */
@Entity
//@Cacheable
@Table(name = "cfg_exchange_info"
)
public class ExchangeInformation {
	private Long id;
	private String fromCurrency;
	private String toCurrency;
	private ExchangeRateSource source;
	private String sourceUrl;

	/** Numeric identifier of the entity. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	public Long getId() {
		return id;
	}
	/** Numeric identifier of the entity. */
	public void setId(Long id) {
		this.id = id;
	}

	/** Currency being exchanged from. */
	@Column(name = "from_currency", nullable = false, updatable = false, length = 3)
	public String getFromCurrency() {
		return fromCurrency;
	}
	/** Currency being exchanged from. */
	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	/** Currency being exchanged to. */
	@Column(name = "to_currency", nullable = false, updatable = false, length = 3)
	public String getToCurrency() {
		return toCurrency;
	}
	/** Currency being exchanged to. */
	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public ExchangeRateSource getSource() {
		return source;
	}

	public void setSource(ExchangeRateSource source) {
		this.source = source;
	}

	@Column(name = "source_url", nullable = false)
	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
}

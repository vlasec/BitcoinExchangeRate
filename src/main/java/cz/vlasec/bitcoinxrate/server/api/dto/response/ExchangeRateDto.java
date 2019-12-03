package cz.vlasec.bitcoinxrate.server.api.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonPropertyOrder({"fromCurrency", "toCurrency", "rate", "timestamp"})
public class ExchangeRateDto {
	private CurrencyDto fromCurrency;
	private CurrencyDto toCurrency;
	private BigDecimal rate;
	private LocalDateTime timestamp;

	public ExchangeRateDto() {
	}

	public ExchangeRateDto(CurrencyDto fromCurrency, CurrencyDto toCurrency, BigDecimal rate, LocalDateTime timestamp) {
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.rate = rate;
		this.timestamp = timestamp;
	}

	/** Input currency with localized naming. */
	public CurrencyDto getFromCurrency() {
		return fromCurrency;
	}

	/** Output currency with localized naming. */
	public CurrencyDto getToCurrency() {
		return toCurrency;
	}

	/** Exchange rate of said currencies. */
	public BigDecimal getRate() {
		return rate;
	}

	/** Timestamp in local timezone. */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setFromCurrency(CurrencyDto fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public void setToCurrency(CurrencyDto toCurrency) {
		this.toCurrency = toCurrency;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}

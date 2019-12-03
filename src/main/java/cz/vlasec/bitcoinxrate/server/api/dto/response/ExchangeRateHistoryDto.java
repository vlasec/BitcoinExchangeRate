package cz.vlasec.bitcoinxrate.server.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * A collection of multiple historic values of an exchange rate between two currencies.
 */
public class ExchangeRateHistoryDto {
	private CurrencyDto fromCurrency;
	private CurrencyDto toCurrency;
	private List<EntryDto> entries;

	public ExchangeRateHistoryDto() {
	}

	public ExchangeRateHistoryDto(CurrencyDto fromCurrency, CurrencyDto toCurrency, List<EntryDto> entries) {
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.entries = entries;
	}

	/** Input currency with localized naming. */
	public CurrencyDto getFromCurrency() {
		return fromCurrency;
	}

	/** Output currency with localized naming. */
	public CurrencyDto getToCurrency() {
		return toCurrency;
	}

	/** History of entries. Contains selected entries as provided by service. */
	public List<EntryDto> getEntries() {
		return entries;
	}

	public void setFromCurrency(CurrencyDto fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public void setToCurrency(CurrencyDto toCurrency) {
		this.toCurrency = toCurrency;
	}

	public void setEntries(List<EntryDto> entries) {
		this.entries = entries;
	}

	/**
	 * A container for exchange rate and its timestamp.
	 * A minimalistic variant of {@link ExchangeRateDto} meant for historic data collections with common header.
	 */
	public static class EntryDto {
		private BigDecimal rate;
		private LocalDateTime timestamp;

		public EntryDto() {
		}

		public EntryDto(BigDecimal rate, LocalDateTime timestamp) {
			this.rate = rate;
			this.timestamp = timestamp;
		}

		/** Exchange rate of said currencies. */
		public BigDecimal getRate() {
			return rate;
		}

		/** Timestamp in local timezone. */
		public LocalDateTime getTimestamp() {
			return timestamp;
		}

		public void setRate(BigDecimal rate) {
			this.rate = rate;
		}

		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}
	}
}

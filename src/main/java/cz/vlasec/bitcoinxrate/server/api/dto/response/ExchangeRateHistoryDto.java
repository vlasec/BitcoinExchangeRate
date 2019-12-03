package cz.vlasec.bitcoinxrate.server.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * A collection of multiple historic values of an exchange rate between two currencies.
 */
public class ExchangeRateHistoryDto {
	private final CurrencyDto fromCurrency;
	private final CurrencyDto toCurrency;
	private final List<EntryDto> entries;

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

	/**
	 * A container for exchange rate and its timestamp.
	 * A minimalistic variant of {@link ExchangeRateDto} meant for historic data collections with common header.
	 */
	public static class EntryDto {
		private final BigDecimal rate;
		private final LocalDateTime timestamp;

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
	}
}

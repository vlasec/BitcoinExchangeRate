package cz.vlasec.bitcoinxrate.server.api.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request for historical data of given currency pair.
 * The locale could alteratively be passed in a SessionScoped (or RequestScoped) bean.
 */
public class ExchangeRateHistoryRequestDto {
	private String fromCurrency;
	private String toCurrency;
	private LocaleSpecificationDto locale;

	@JsonCreator
	public ExchangeRateHistoryRequestDto(
			@JsonProperty("fromCurrency") String fromCurrency,
			@JsonProperty("toCurrency") String toCurrency,
			@JsonProperty("locale") LocaleSpecificationDto locale) {
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.locale = locale;
	}

	/** Input currency code. */
	public String getFromCurrency() {
		return fromCurrency;
	}

	/** Output currency code. */
	public String getToCurrency() {
		return toCurrency;
	}

	/** Information on locale specifics of user. */
	public LocaleSpecificationDto getLocale() {
		return locale;
	}
}

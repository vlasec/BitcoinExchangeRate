package cz.vlasec.bitcoinxrate.server.api.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Currency in presentation form.
 */
@JsonPropertyOrder({"code", "shortName", "fullName"})
public class CurrencyDto {
	private final String code;
	private final String shortName;
	private final String fullName;

	public CurrencyDto(String code, String shortName, String fullName) {
		this.code = code;
		this.shortName = shortName;
		this.fullName = fullName;
	}

	public String getCode() {
		return code;
	}

	public String getShortName() {
		return shortName;
	}

	public String getFullName() {
		return fullName;
	}
}

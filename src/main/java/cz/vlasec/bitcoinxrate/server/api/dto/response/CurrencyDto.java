package cz.vlasec.bitcoinxrate.server.api.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Currency in presentation form.
 */
@JsonPropertyOrder({"code", "shortName", "fullName"})
public class CurrencyDto {
	private String code;
	private String shortName;
	private String fullName;

	public CurrencyDto() {
	}

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

	public void setCode(String code) {
		this.code = code;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}

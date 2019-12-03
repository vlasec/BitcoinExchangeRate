package cz.vlasec.bitcoinxrate.server.api.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.web.context.annotation.RequestScope;

/**
 * A specification of locale to use for data presentation.
 */
@RequestScope
public class LocaleSpecificationDto {
	private final String language;
	private final String timeZoneName;

	@JsonCreator
	public LocaleSpecificationDto(
			@JsonProperty("locale") String language,
			@JsonProperty("timeZoneName") String timeZoneName
	) {
		this.language = language;
		this.timeZoneName = timeZoneName;
	}

	/** Language to be used for data presentation. */
	public String getLanguage() {
		return language;
	}

	/** Time zone to convert time information to. */
	public String getTimeZoneName() {
		return timeZoneName;
	}
}

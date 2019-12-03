package cz.vlasec.bitcoinxrate.server.api.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.web.context.annotation.RequestScope;

import java.util.Locale;
import java.util.TimeZone;

/**
 * A specification of locale to use for data presentation.
 */
@RequestScope
public class LocaleSpecificationDto {
	private final Locale locale;
	private final TimeZone timeZone;

	@JsonCreator
	public LocaleSpecificationDto(
			@JsonProperty("locale") Locale locale,
			@JsonProperty("timeZone") TimeZone timeZone
	) {
		this.locale = locale;
		this.timeZone = timeZone;
	}

	/** Language to be used for data presentation. */
	public Locale getLocale() {
		return locale;
	}

	/** Time zone to convert time information to. */
	public TimeZone getTimeZone() {
		return timeZone;
	}
}

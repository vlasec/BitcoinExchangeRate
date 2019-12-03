package cz.vlasec.bitcoinxrate.server.service.impl;

import cz.vlasec.bitcoinxrate.server.api.dto.request.LocaleSpecificationDto;
import cz.vlasec.bitcoinxrate.server.api.dto.response.CurrencyDto;
import cz.vlasec.bitcoinxrate.server.orm.dao.config.CurrencyDescriptionRepository;
import cz.vlasec.bitcoinxrate.server.orm.entity.config.CurrencyDescription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class CurrencyDataConvertor {
	public static final String DEFAULT_LOCALE = Locale.ENGLISH.toLanguageTag();

	private final CurrencyDescriptionRepository currencyDescriptionRepository;

	@Autowired public CurrencyDataConvertor(CurrencyDescriptionRepository currencyDescriptionRepository) {
		this.currencyDescriptionRepository = currencyDescriptionRepository;
	}

	public CurrencyDto toDto(String code, LocaleSpecificationDto locale) {
		CurrencyDescription descr = currencyDescriptionRepository.findByCodeAndLocale(code, locale.getLanguage());
		if (descr == null) {
			currencyDescriptionRepository.findByCodeAndLocale(code, DEFAULT_LOCALE);
		}
		if (descr == null) {
			// Fallback to code when database contains no information on given currency
			return new CurrencyDto(code, code, code);
		} else {
			return new CurrencyDto(code, descr.getShortName(), descr.getFullName());
		}
	}


}

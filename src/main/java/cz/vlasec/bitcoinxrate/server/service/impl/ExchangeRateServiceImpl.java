package cz.vlasec.bitcoinxrate.server.service.impl;

import cz.vlasec.bitcoinxrate.server.api.dto.request.ExchangeRateHistoryRequestDto;
import cz.vlasec.bitcoinxrate.server.api.dto.request.LocaleSpecificationDto;
import cz.vlasec.bitcoinxrate.server.api.dto.response.ExchangeRateHistoryDto;
import cz.vlasec.bitcoinxrate.server.api.service.ExchangeRateService;
import cz.vlasec.bitcoinxrate.server.orm.dao.data.ExchangeInformationRepository;
import cz.vlasec.bitcoinxrate.server.orm.dao.data.ExchangeRateRepository;
import cz.vlasec.bitcoinxrate.server.orm.entity.config.ExchangeInformation;
import cz.vlasec.bitcoinxrate.server.orm.entity.data.ExchangeRate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;

import static java.time.Duration.ofDays;
import static java.time.Duration.ofHours;

@Service
@Transactional
public class ExchangeRateServiceImpl implements ExchangeRateService {
	private static final List<Duration> INTERVALS = Collections.unmodifiableList(Arrays.asList(
			ofHours(3), ofHours(8), ofDays(1), ofDays(3), ofDays(7)
	));

	private final ExchangeInformationRepository exchangeInformationRepository;
	private final ExchangeRateRepository exchangeRateRepository;
	private final CurrencyDataConvertor currencyDataConvertor;

	@Autowired
	public ExchangeRateServiceImpl(ExchangeInformationRepository exchangeInformationRepository, ExchangeRateRepository exchangeRateRepository, CurrencyDataConvertor currencyDataConvertor) {
		this.exchangeInformationRepository = exchangeInformationRepository;
		this.exchangeRateRepository = exchangeRateRepository;
		this.currencyDataConvertor = currencyDataConvertor;

	}

	@Override
	public ExchangeRateHistoryDto getHistoricalResults(ExchangeRateHistoryRequestDto request) {
		ExchangeInformation info = exchangeInformationRepository.findByFromCurrencyAndToCurrency(request.getFromCurrency(), request.getToCurrency());
		if (info == null) {
			throw new NoSuchElementException("No information found for currencies " + request.getFromCurrency() + " -> " + request.getToCurrency());
		}
		List<ExchangeRate> rates = new ArrayList<>(10 + INTERVALS.size());
		Instant now = Instant.now();
		rates.addAll(exchangeRateRepository.findFirst10ByExchangeOrderByTimestampDesc(info));
		// Hard to optimize. Finding all results, provided that there is e.g. per minute, would mean thousands of records.
		// On the other hand, multiple short requests certainly create some overhead. Perhaps only a DB procedure would really help.
		for (Duration interval : INTERVALS) {
			exchangeRateRepository.findFirstByExchangeAndTimestampBeforeOrderByTimestampDesc(info, now.minus(interval))
					.ifPresent(rates::add);
		}
		return toDto(request.getLocale(), info, rates);
	}

	private ExchangeRateHistoryDto toDto(LocaleSpecificationDto locale, ExchangeInformation info, List<ExchangeRate> rates) {
		return new ExchangeRateHistoryDto(
				currencyDataConvertor.toDto(info.getFromCurrency(), locale),
				currencyDataConvertor.toDto(info.getToCurrency(), locale),
				rates.stream().map(e -> convertEntry(locale, e)).collect(Collectors.toList())
		);
	}

	private ExchangeRateHistoryDto.EntryDto convertEntry(LocaleSpecificationDto locale, ExchangeRate rate) {
		return new ExchangeRateHistoryDto.EntryDto(
				rate.getRate(),
				ZonedDateTime.ofInstant(rate.getTimestamp(), TimeZone.getTimeZone(locale.getTimeZoneName()).toZoneId()).toLocalDateTime()
		);
	}
}

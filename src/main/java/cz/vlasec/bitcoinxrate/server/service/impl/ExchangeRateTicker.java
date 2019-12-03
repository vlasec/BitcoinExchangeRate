package cz.vlasec.bitcoinxrate.server.service.impl;

import cz.vlasec.bitcoinxrate.server.atom.ExchangeRateSource;
import cz.vlasec.bitcoinxrate.server.orm.dao.config.ExchangeInformationRepository;
import cz.vlasec.bitcoinxrate.server.orm.dao.data.ExchangeRateRepository;
import cz.vlasec.bitcoinxrate.server.orm.entity.config.ExchangeInformation;
import cz.vlasec.bitcoinxrate.server.orm.entity.data.ExchangeRate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * So far, BTC_CZK is hardcoded. Multiple currencies could be supported, but for an universal solution,
 * it is possible that multiple sources would need to be used. (e.g. coinmate.io doesn't have USD)
 */
@Component
public class ExchangeRateTicker {
	private final ExchangeRateRepository repository;
	private final ExchangeInformationRepository infoRepository;

	@Autowired public ExchangeRateTicker(ExchangeRateRepository repository, ExchangeInformationRepository infoRepository) {
		this.repository = repository;
		this.infoRepository = infoRepository;
	}

	@Scheduled(cron = "${bitcoin.ticker.cron}")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void exchangeRateTick() {
		RestTemplate restTemplate = new RestTemplate();
		for (ExchangeInformation info : infoRepository.findBySource(ExchangeRateSource.COINMATE_IO)) {
			String url = info.getSourceUrl();
			CoinmateOutputDto output = restTemplate
					.getForObject(url, CoinmateOutputDto.class);
			if (output == null || output.isError()) {
				// Error log not included in demo version.
				continue;
			}
			ExchangeRate rate = new ExchangeRate();
			rate.setExchange(info);
			rate.setRate(output.getData().getLast());
			rate.setTimestamp(output.getData().getTimestamp());
			repository.save(rate);
		}
	}
}

@JsonIgnoreProperties(ignoreUnknown = true)
class CoinmateOutputDto {
	private final boolean error;
	private final CoinmateDataDto data;

	@JsonCreator
	public CoinmateOutputDto(@JsonProperty("error") boolean error, @JsonProperty("data") CoinmateDataDto data) {
		this.error = error;
		this.data = data;
	}

	public boolean isError() {
		return error;
	}

	public CoinmateDataDto getData() {
		return data;
	}
}

@JsonIgnoreProperties(ignoreUnknown = true)
class CoinmateDataDto {
	private final BigDecimal last;
	private final Instant timestamp;

	@JsonCreator
	public CoinmateDataDto(@JsonProperty("last") BigDecimal last, @JsonProperty("timestamp") Instant timestamp) {
		this.last = last;
		this.timestamp = timestamp;
	}

	public BigDecimal getLast() {
		return last;
	}

	public Instant getTimestamp() {
		return timestamp;
	}
}

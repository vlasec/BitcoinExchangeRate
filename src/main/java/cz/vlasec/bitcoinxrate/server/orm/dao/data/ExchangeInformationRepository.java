package cz.vlasec.bitcoinxrate.server.orm.dao.data;

import cz.vlasec.bitcoinxrate.server.atom.ExchangeRateSource;
import cz.vlasec.bitcoinxrate.server.orm.entity.config.ExchangeInformation;

import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface ExchangeInformationRepository extends Repository<ExchangeInformation, Long> {
	ExchangeInformation findByFromCurrencyAndToCurrency(String fromCurrencyCode, String toCurrencyCode);

	Iterable<ExchangeInformation> findBySource(ExchangeRateSource source);
}

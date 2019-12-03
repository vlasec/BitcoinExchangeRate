package cz.vlasec.bitcoinxrate.server.orm.dao.data;

import cz.vlasec.bitcoinxrate.server.orm.entity.config.ExchangeInformation;
import cz.vlasec.bitcoinxrate.server.orm.entity.data.ExchangeRate;

import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Long> {
	/** Finds ten latest  */
	List<ExchangeRate> findFirst10ByExchangeOrderByTimestampDesc(ExchangeInformation exchangeInformation);

	Optional<ExchangeRate> findFirstByExchangeAndTimestampBeforeOrderByTimestampDesc(ExchangeInformation exchangeInformation, Instant before);
}

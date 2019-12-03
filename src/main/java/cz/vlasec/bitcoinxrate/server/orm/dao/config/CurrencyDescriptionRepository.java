package cz.vlasec.bitcoinxrate.server.orm.dao.config;

import cz.vlasec.bitcoinxrate.server.orm.entity.config.CurrencyDescription;

import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface CurrencyDescriptionRepository extends Repository<CurrencyDescription, Long> {
	CurrencyDescription findByCodeAndLocale(String code, String locale);
}

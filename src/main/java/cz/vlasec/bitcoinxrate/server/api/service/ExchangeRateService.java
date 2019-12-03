package cz.vlasec.bitcoinxrate.server.api.service;

import cz.vlasec.bitcoinxrate.server.api.dto.request.ExchangeRateHistoryRequestDto;
import cz.vlasec.bitcoinxrate.server.api.dto.response.ExchangeRateHistoryDto;

public interface ExchangeRateService {
	ExchangeRateHistoryDto getHistoricalResults(ExchangeRateHistoryRequestDto request);
}

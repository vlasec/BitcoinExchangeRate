package cz.vlasec.bitcoinxrate.client.controller;

import cz.vlasec.bitcoinxrate.server.api.dto.request.ExchangeRateHistoryRequestDto;
import cz.vlasec.bitcoinxrate.server.api.dto.request.LocaleSpecificationDto;
import cz.vlasec.bitcoinxrate.server.api.dto.response.ExchangeRateHistoryDto;
import cz.vlasec.bitcoinxrate.server.api.service.ExchangeRateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

@Controller
public class ExchangeRateHistoryController {
	private final ExchangeRateService exchangeRateService;

	@Autowired public ExchangeRateHistoryController(ExchangeRateService exchangeRateService) {
		this.exchangeRateService = exchangeRateService;
	}

	@GetMapping("/")
	public String homePage(Model model) {
		Locale locale = LocaleContextHolder.getLocale();
		TimeZone timezone = LocaleContextHolder.getTimeZone();
		ExchangeRateHistoryDto historyDto = exchangeRateService.getHistoricalResults(new ExchangeRateHistoryRequestDto(
				"BTC",
				"CZK",
				new LocaleSpecificationDto(locale, timezone)
		));
		Iterator<ExchangeRateHistoryDto.EntryDto> iter = historyDto.getEntries().iterator();
		ExchangeRateHistoryDto.EntryDto latest = iter.next();
		ExchangeRateHistoryDto.EntryDto previous = iter.next();
		BigDecimal difference = latest.getRate().subtract(previous.getRate());
		model.addAttribute("fromCurrency", historyDto.getFromCurrency());
		model.addAttribute("toCurrency", historyDto.getToCurrency());
		model.addAttribute("entries",  historyDto.getEntries());
		model.addAttribute("lastChange", difference);
		return "home";
	}
}

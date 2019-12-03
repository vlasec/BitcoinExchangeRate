package cz.vlasec.bitcoinxrate.client.controller;

import cz.vlasec.bitcoinxrate.server.api.dto.request.ExchangeRateHistoryRequestDto;
import cz.vlasec.bitcoinxrate.server.api.dto.request.LocaleSpecificationDto;
import cz.vlasec.bitcoinxrate.server.api.dto.response.ExchangeRateHistoryDto;
import cz.vlasec.bitcoinxrate.server.api.service.ExchangeRateService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

@Controller
public class ExchangeRateHistoryController {
	private final ExchangeRateService exchangeRateService;
	ObjectMapper mapper = new ObjectMapper();

	@Autowired public ExchangeRateHistoryController(ExchangeRateService exchangeRateService) {
		this.exchangeRateService = exchangeRateService;
	}

	@GetMapping("/")
	public String homePage(Model model) {
		ExchangeRateHistoryDto historyDto = exchangeRateService.getHistoricalResults(new ExchangeRateHistoryRequestDto(
				"BTC",
				"CZK",
				new LocaleSpecificationDto(
						"cs",
						"Europe/Prague"
				)
		));
		Iterator<ExchangeRateHistoryDto.EntryDto> iter = historyDto.getEntries().iterator();
		ExchangeRateHistoryDto.EntryDto latest = iter.next();
		ExchangeRateHistoryDto.EntryDto previous = iter.next();
		BigDecimal difference = latest.getRate().subtract(previous.getRate());
		model.addAttribute("fromCurrency", mapper.convertValue(historyDto.getFromCurrency(), Map.class));
		model.addAttribute("toCurrency", mapper.convertValue(historyDto.getToCurrency(), Map.class));
		model.addAttribute("entries",  mapper.convertValue(historyDto.getEntries(), Map.class));
		model.addAttribute("lastChange", difference);
		return "home";
	}
}

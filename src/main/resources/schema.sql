CREATE TABLE IF NOT EXISTS cfg_currency_descr (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  locale VARCHAR(50),
  code CHAR(3),
  short_name VARCHAR(10),
  full_name VARCHAR(50)
);

CREATE UNIQUE INDEX IF NOT EXISTS ux_cfg_currency_descr__locale_code ON cfg_currency_descr(locale, code);

CREATE TABLE IF NOT EXISTS cfg_exchange_info (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  from_currency CHAR(3),
  to_currency CHAR(3),
  source VARCHAR(16),
  source_url VARCHAR(255),
);

CREATE UNIQUE INDEX IF NOT EXISTS ux_cfg_exchange_info__currency_from_to ON cfg_exchange_info(from_currency, to_currency);

CREATE TABLE IF NOT EXISTS dta_exchange_rate (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  exchange__cfg_exchange_info BIGINT,
  rate DECIMAL(20,2),
  `timestamp` TIMESTAMP WITH TIME ZONE,
  FOREIGN KEY (exchange__cfg_exchange_info) REFERENCES cfg_exchange_info(id)
);

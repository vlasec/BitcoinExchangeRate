INSERT INTO cfg_currency_descr (locale, code, short_name, full_name)
  SELECT * FROM(
    SELECT 'cs', 'BTC', '₿', 'Bitcoin' UNION
    SELECT 'en', 'BTC', '₿', 'Bitcoin' UNION
    SELECT 'cs', 'CZK', 'Kč', 'Koruna česká' UNION
    SELECT 'en', 'CZK', 'CZK', 'Czech crowns' UNION
    SELECT 'cs', 'EUR', '€', 'Euro' UNION
    SELECT 'en', 'EUR', '€', 'Euro'
  ) x WHERE NOT exists(SELECT * FROM cfg_currency_descr);


INSERT INTO cfg_exchange_info (from_currency, to_currency, source, source_url)
  SELECT * FROM (
    SELECT 'BTC', 'CZK', 'COINMATE_IO', 'https://coinmate.io/api/ticker?currencyPair=BTC_CZK' UNION
    SELECT 'BTC', 'EUR', 'COINMATE_IO', 'https://coinmate.io/api/ticker?currencyPair=BTC_EUR'
  ) x WHERE NOT exists(SELECT * FROM cfg_exchange_info);

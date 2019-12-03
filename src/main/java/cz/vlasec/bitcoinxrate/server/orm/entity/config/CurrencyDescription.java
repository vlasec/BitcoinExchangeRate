package cz.vlasec.bitcoinxrate.server.orm.entity.config;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** This entity stores information */
@Entity
//@Cacheable
@Table(name = "cfg_currency_descr"
)

public class CurrencyDescription {
	private Long id;
	private String locale;
	private String code;
	private String shortName;
	private String fullName;

	/** Numeric identifier of the entity. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	public Long getId() {
		return id;
	}
	/** Numeric identifier of the entity. */
	public void setId(Long id) {
		this.id = id;
	}

	/** Locale. All the naming inside is for said locale. */
	@Column(nullable = false, updatable = false)
	public String getLocale() {
		return locale;
	}
	/** Locale. All the naming inside is for said locale. */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/** Currency code that is being described here. */
	@Column(nullable = false, updatable = false, length = 3)
	public String getCode() {
		return code;
	}
	/** Currency code that is being described here. */
	public void setCode(String code) {
		this.code = code;
	}

	/** Short description of the currency. */
	@Column(name = "short_name", nullable = false, length = 10)
	public String getShortName() {
		return shortName;
	}
	/** Short description of the currency. */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/** Full description of the currency. */
	@Column(name = "full_name", nullable = false, length = 50)
	public String getFullName() {
		return fullName;
	}
	/** Full description of the currency. */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}

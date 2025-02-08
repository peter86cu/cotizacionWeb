package com.shopping.dashboard.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the wp_wsm_logUniqueVisit database table.
 * 
 */
@Entity
@NamedQuery(name="Wp_wsm_logUniqueVisit.findAll", query="SELECT w FROM Wp_wsm_logUniqueVisit w")
public class Wp_wsm_logUniqueVisit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private int browserId;

	private String browserLang;

	private String city;

	private String configId;

	private byte cookie;

	private int countryId;

	private Time currentLocalTime;

	private int daysSinceFirstVisit;

	private int daysSinceLastVisit;

	private String deviceType;

	private byte director;

	@Temporal(TemporalType.TIMESTAMP)
	private Date firstActionVisitTime;

	private byte flash;

	private byte gears;

	private String ipAddress;

	private byte java;

	private BigDecimal latitude;

	private BigDecimal longitude;

	private int oSystemId;

	private byte pdf;

	private byte quicktime;

	private byte realplayer;

	private int refererUrlId;

	private byte regionId;

	private int resolutionId;

	private byte returningVisitor;

	private byte silverlight;

	private int siteId;

	private int totalTimeVisit;

	private String userId;

	private int visitCount;

	private int visitEntryURLId;

	private int visitExitURLId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date visitLastActionTime;

	private String visitorId;

	private int visitTotalActions;

	private byte windowsmedia;

	public Wp_wsm_logUniqueVisit() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getBrowserId() {
		return this.browserId;
	}

	public void setBrowserId(int browserId) {
		this.browserId = browserId;
	}

	public String getBrowserLang() {
		return this.browserLang;
	}

	public void setBrowserLang(String browserLang) {
		this.browserLang = browserLang;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getConfigId() {
		return this.configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public byte getCookie() {
		return this.cookie;
	}

	public void setCookie(byte cookie) {
		this.cookie = cookie;
	}

	public int getCountryId() {
		return this.countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public Time getCurrentLocalTime() {
		return this.currentLocalTime;
	}

	public void setCurrentLocalTime(Time currentLocalTime) {
		this.currentLocalTime = currentLocalTime;
	}

	public int getDaysSinceFirstVisit() {
		return this.daysSinceFirstVisit;
	}

	public void setDaysSinceFirstVisit(int daysSinceFirstVisit) {
		this.daysSinceFirstVisit = daysSinceFirstVisit;
	}

	public int getDaysSinceLastVisit() {
		return this.daysSinceLastVisit;
	}

	public void setDaysSinceLastVisit(int daysSinceLastVisit) {
		this.daysSinceLastVisit = daysSinceLastVisit;
	}

	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public byte getDirector() {
		return this.director;
	}

	public void setDirector(byte director) {
		this.director = director;
	}

	public Date getFirstActionVisitTime() {
		return this.firstActionVisitTime;
	}

	public void setFirstActionVisitTime(Date firstActionVisitTime) {
		this.firstActionVisitTime = firstActionVisitTime;
	}

	public byte getFlash() {
		return this.flash;
	}

	public void setFlash(byte flash) {
		this.flash = flash;
	}

	public byte getGears() {
		return this.gears;
	}

	public void setGears(byte gears) {
		this.gears = gears;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public byte getJava() {
		return this.java;
	}

	public void setJava(byte java) {
		this.java = java;
	}

	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public int getOSystemId() {
		return this.oSystemId;
	}

	public void setOSystemId(int oSystemId) {
		this.oSystemId = oSystemId;
	}

	public byte getPdf() {
		return this.pdf;
	}

	public void setPdf(byte pdf) {
		this.pdf = pdf;
	}

	public byte getQuicktime() {
		return this.quicktime;
	}

	public void setQuicktime(byte quicktime) {
		this.quicktime = quicktime;
	}

	public byte getRealplayer() {
		return this.realplayer;
	}

	public void setRealplayer(byte realplayer) {
		this.realplayer = realplayer;
	}

	public int getRefererUrlId() {
		return this.refererUrlId;
	}

	public void setRefererUrlId(int refererUrlId) {
		this.refererUrlId = refererUrlId;
	}

	public byte getRegionId() {
		return this.regionId;
	}

	public void setRegionId(byte regionId) {
		this.regionId = regionId;
	}

	public int getResolutionId() {
		return this.resolutionId;
	}

	public void setResolutionId(int resolutionId) {
		this.resolutionId = resolutionId;
	}

	public byte getReturningVisitor() {
		return this.returningVisitor;
	}

	public void setReturningVisitor(byte returningVisitor) {
		this.returningVisitor = returningVisitor;
	}

	public byte getSilverlight() {
		return this.silverlight;
	}

	public void setSilverlight(byte silverlight) {
		this.silverlight = silverlight;
	}

	public int getSiteId() {
		return this.siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getTotalTimeVisit() {
		return this.totalTimeVisit;
	}

	public void setTotalTimeVisit(int totalTimeVisit) {
		this.totalTimeVisit = totalTimeVisit;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getVisitCount() {
		return this.visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public int getVisitEntryURLId() {
		return this.visitEntryURLId;
	}

	public void setVisitEntryURLId(int visitEntryURLId) {
		this.visitEntryURLId = visitEntryURLId;
	}

	public int getVisitExitURLId() {
		return this.visitExitURLId;
	}

	public void setVisitExitURLId(int visitExitURLId) {
		this.visitExitURLId = visitExitURLId;
	}

	public Date getVisitLastActionTime() {
		return this.visitLastActionTime;
	}

	public void setVisitLastActionTime(Date visitLastActionTime) {
		this.visitLastActionTime = visitLastActionTime;
	}

	public String getVisitorId() {
		return this.visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}

	public int getVisitTotalActions() {
		return this.visitTotalActions;
	}

	public void setVisitTotalActions(int visitTotalActions) {
		this.visitTotalActions = visitTotalActions;
	}

	public byte getWindowsmedia() {
		return this.windowsmedia;
	}

	public void setWindowsmedia(byte windowsmedia) {
		this.windowsmedia = windowsmedia;
	}

}
package com.surrussent.coins.Modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Coins {

    // This is Tertiary (Main) Data access object //

    // Initialize object by API object name
    @SerializedName("id")
    private int id;
    @SerializedName("uuid")
    private String uuid;
    @SerializedName("slug")
    private String slug;
    @SerializedName("symbol")
    private String symbol;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("color")
    private String color;
    @SerializedName("iconType")
    private String iconType;
    @SerializedName("iconUrl")
    private String iconUrl;
    @SerializedName("websiteUrl")
    private String websiteUrl;
    @SerializedName("socials")
    private List socials;
    @SerializedName("links")
    private List links;
    @SerializedName("confirmedSupply")
    private boolean  confirmedSupply;
    @SerializedName("numberOfMarkets")
    private int numberOfMarkets;
    @SerializedName("numberOfExchanges")
    private int numberOfExchanges;
    @SerializedName("type")
    private String type;
    @SerializedName("volume")
    private double volume;
    @SerializedName("marketCap")
    private double marketCap;
    @SerializedName("price")
    private float price;
    @SerializedName("circulatingSupply")
    private double circulatingSupply;
    @SerializedName("totalSupply")
    private double totalSupply;
    @SerializedName("approvedSupply")
    private boolean approvedSupply;
    @SerializedName("firstSeen")
    private double firstSeen;
    @SerializedName("listedAt")
    private int listedAt;
    @SerializedName("change")
    private float change;
    @SerializedName("rank")
    private int rank;
    @SerializedName("history")
    private List history;
    @SerializedName("allTimeHigh")
    private AllTimeHigh allTimeHigh;
    @SerializedName("penalty")
    private boolean penalty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public List getSocials() {
        return socials;
    }

    public void setSocials(List socials) {
        this.socials = socials;
    }

    public List getLinks() {
        return links;
    }

    public void setLinks(List links) {
        this.links = links;
    }

    public boolean isConfirmedSupply() {
        return confirmedSupply;
    }

    public void setConfirmedSupply(boolean confirmedSupply) {
        this.confirmedSupply = confirmedSupply;
    }

    public int getNumberOfMarkets() {
        return numberOfMarkets;
    }

    public void setNumberOfMarkets(int numberOfMarkets) {
        this.numberOfMarkets = numberOfMarkets;
    }

    public int getNumberOfExchanges() {
        return numberOfExchanges;
    }

    public void setNumberOfExchanges(int numberOfExchanges) {
        this.numberOfExchanges = numberOfExchanges;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public double getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(double circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public boolean isApprovedSupply() {
        return approvedSupply;
    }

    public void setApprovedSupply(boolean approvedSupply) {
        this.approvedSupply = approvedSupply;
    }

    public double getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(double firstSeen) {
        this.firstSeen = firstSeen;
    }

    public int getListedAt() {
        return listedAt;
    }

    public void setListedAt(int listedAt) {
        this.listedAt = listedAt;
    }

    public float getChange() {
        return change;
    }

    public void setChange(float change) {
        this.change = change;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public List getHistory() {
        return history;
    }

    public void setHistory(List history) {
        this.history = history;
    }

    public AllTimeHigh getAllTimeHigh() {
        return allTimeHigh;
    }

    public void setAllTimeHigh(AllTimeHigh allTimeHigh) {
        this.allTimeHigh = allTimeHigh;
    }

    public boolean isPenalty() {
        return penalty;
    }

    public void setPenalty(boolean penalty) {
        this.penalty = penalty;
    }
}
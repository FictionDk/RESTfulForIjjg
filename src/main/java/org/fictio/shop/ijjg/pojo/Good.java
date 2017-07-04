package org.fictio.shop.ijjg.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Good {
    private Integer id;

    private String name;

    private String goods_no;

    private Integer model_id;

    private BigDecimal sell_price;

    private BigDecimal market_price;

    private BigDecimal cost_price;

    private Timestamp up_time;

    private Timestamp down_time;

    private Timestamp create_time;

    private Integer store_nums;

    private String img;

    private String ad_img;

    private Boolean is_del;

    private String keywords;

    private String description;

    private String search_words;

    private BigDecimal weight;

    private Integer point;

    private String unit;

    private Integer brand_id;

    private Integer visit;

    private Integer favorite;

    private Short sort;

    private Integer exp;

    private Integer comments;

    private Integer sale;

    private Integer grade;

    private Integer seller_id;

    private Boolean is_share;

    private Integer ordered_nums;

    private String content;

    private String spec_array;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGoods_no() {
        return goods_no;
    }

    public void setGoods_no(String goods_no) {
        this.goods_no = goods_no == null ? null : goods_no.trim();
    }

    public Integer getModel_id() {
        return model_id;
    }

    public void setModel_id(Integer model_id) {
        this.model_id = model_id;
    }

    public BigDecimal getSell_price() {
        return sell_price;
    }

    public void setSell_price(BigDecimal sell_price) {
        this.sell_price = sell_price;
    }

    public BigDecimal getMarket_price() {
        return market_price;
    }

    public void setMarket_price(BigDecimal market_price) {
        this.market_price = market_price;
    }

    public BigDecimal getCost_price() {
        return cost_price;
    }

    public void setCost_price(BigDecimal cost_price) {
        this.cost_price = cost_price;
    }

    public Timestamp getUp_time() {
        return up_time;
    }

    public void setUp_time(Timestamp up_time) {
        this.up_time = up_time;
    }

    public Timestamp getDown_time() {
        return down_time;
    }

    public void setDown_time(Timestamp down_time) {
        this.down_time = down_time;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Integer getStore_nums() {
        return store_nums;
    }

    public void setStore_nums(Integer store_nums) {
        this.store_nums = store_nums;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getAd_img() {
        return ad_img;
    }

    public void setAd_img(String ad_img) {
        this.ad_img = ad_img == null ? null : ad_img.trim();
    }

    public Boolean getIs_del() {
        return is_del;
    }

    public void setIs_del(Boolean is_del) {
        this.is_del = is_del;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getSearch_words() {
        return search_words;
    }

    public void setSearch_words(String search_words) {
        this.search_words = search_words == null ? null : search_words.trim();
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public Integer getVisit() {
        return visit;
    }

    public void setVisit(Integer visit) {
        this.visit = visit;
    }

    public Integer getFavorite() {
        return favorite;
    }

    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Integer seller_id) {
        this.seller_id = seller_id;
    }

    public Boolean getIs_share() {
        return is_share;
    }

    public void setIs_share(Boolean is_share) {
        this.is_share = is_share;
    }

    public Integer getOrdered_nums() {
        return ordered_nums;
    }

    public void setOrdered_nums(Integer ordered_nums) {
        this.ordered_nums = ordered_nums;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getSpec_array() {
        return spec_array;
    }

    public void setSpec_array(String spec_array) {
        this.spec_array = spec_array == null ? null : spec_array.trim();
    }

	@Override
	public String toString() {
		return "Good [id=" + id + ", name=" + name + ", goods_no=" + goods_no + ", model_id=" + model_id
				+ ", sell_price=" + sell_price + ", market_price=" + market_price + ", cost_price=" + cost_price
				+ ", up_time=" + up_time + ", down_time=" + down_time + ", create_time=" + create_time + ", store_nums="
				+ store_nums + ", img=" + img + ", ad_img=" + ad_img + ", is_del=" + is_del + ", keywords=" + keywords
				+ ", description=" + description + ", search_words=" + search_words + ", weight=" + weight + ", point="
				+ point + ", unit=" + unit + ", brand_id=" + brand_id + ", visit=" + visit + ", favorite=" + favorite
				+ ", sort=" + sort + ", exp=" + exp + ", comments=" + comments + ", sale=" + sale + ", grade=" + grade
				+ ", seller_id=" + seller_id + ", is_share=" + is_share + ", ordered_nums=" + ordered_nums
				+ ", content=" + content + ", spec_array=" + spec_array + "]";
	}
    
}
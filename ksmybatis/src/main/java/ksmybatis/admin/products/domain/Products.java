package ksmybatis.admin.products.domain;

import lombok.Data;

@Data
public class Products {
	private String productCode;
	private String vendorCode;
	private String productName;
	private int productUniPrice;
	private String productRegDate;
}

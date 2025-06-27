package ksmybatis.admin.products.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmybatis.admin.common.domain.CommonCode;
import ksmybatis.admin.products.domain.Products;
import ksmybatis.admin.products.service.ProductsService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductsController {
	
	private final ProductsService productsService;
	
	/*
	 * 상품 목록 조회
	 * @param Model
	 * @return productListView
	 */
	@GetMapping("/productList")
	public String productListView(Model model) {
		
		List<Products> productList = productsService.getProductList();
		
		model.addAttribute("title", "상품 목록 조회");
		model.addAttribute("productList", productList);
			
		return "/admin/products/productListView";
	}
}
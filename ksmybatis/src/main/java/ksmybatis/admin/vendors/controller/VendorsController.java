package ksmybatis.admin.vendors.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmybatis.admin.vendors.domain.Vendors;
import ksmybatis.admin.vendors.service.VendorsService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/vendors")
@RequiredArgsConstructor
public class VendorsController {
	
	private final VendorsService vendorsService;
	
	
	/*
	 * 거래처 목록 조회
	 * @param Model
	 * @return vendorsListView
	 */
	@GetMapping("/vendorsList")
	public String vendorsListView(Model model) {
		
		List<Vendors> vendorsList = vendorsService.getVendorsList();
		
		model.addAttribute("title", "거래처 목록 조회");
		model.addAttribute("vendorsList", vendorsList);
		
		return "/admin/vendors/vendorsListView";
	}
	

}

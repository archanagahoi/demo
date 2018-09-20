package com.example.movemedicalhierarchy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.movemedicalhierarchy.model.MoveMedicalProduct;
import com.example.movemedicalhierarchy.repository.MoveMedicalProductRepository;

@Component
@RestController
public class DemoController {

	@Autowired
	MoveMedicalProductRepository moveMedicalProductRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String retrieveAllProducts() {
		return "Hello: Welcome to the  Services!";
	}

	// list record for given parent node
	@RequestMapping(value = "/listrecordforgivenparentnode/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<MoveMedicalProduct> listRecordForGivenParentNode(@PathVariable(value = "id") Long parentId) {
		return moveMedicalProductRepository.findByParentId(parentId);
	}

	// Create N records for any given level of the hierarchy,
	@RequestMapping(value = "/createMoveMedicalProducts", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createProduct(@Valid @RequestBody List<MoveMedicalProduct> moveMedicalProduct,
			MoveMedicalProduct parentNode) {
		moveMedicalProductRepository.saveNodes(moveMedicalProduct, parentNode);
		return ResponseEntity.ok().build();
	}

	// Create
	@RequestMapping(value = "/createMoveMedicalProduct", method = RequestMethod.POST)
	@ResponseBody
	public MoveMedicalProduct createProduct(@Valid @RequestBody MoveMedicalProduct product) {
		return moveMedicalProductRepository.save(product);
	}

	// delete given node and children
	@RequestMapping(value = "/deletenodeandchildren/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> deleteNodeAndChildren(@PathVariable(value = "id") Long id) {
		moveMedicalProductRepository.deleteNodeAndChildren(id);
		return ResponseEntity.ok().build();
	}
}

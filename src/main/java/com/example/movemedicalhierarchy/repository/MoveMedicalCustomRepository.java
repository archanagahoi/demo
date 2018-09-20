package com.example.movemedicalhierarchy.repository;

import java.util.List;

import com.example.movemedicalhierarchy.model.MoveMedicalProduct;

public interface MoveMedicalCustomRepository {
	 List<MoveMedicalProduct> findByParentId(Long id);
	void  deleteNodeAndChildren(Long id);
	void  saveNodes(List<MoveMedicalProduct> moveMedicalProductList, MoveMedicalProduct parentNode);
}
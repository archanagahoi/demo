package com.example.movemedicalhierarchy.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import com.example.movemedicalhierarchy.model.MoveMedicalProduct;

@Repository
public class MoveMedicalProductRepositoryImpl implements MoveMedicalCustomRepository {
	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<MoveMedicalProduct> findByParentId(Long id) {
		Query q = entityManager.createNativeQuery(
				"SELECT em.* FROM products_app.movemedicalproduct as em WHERE em.parent = ?", MoveMedicalProduct.class);
		q.setParameter(1, id);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void deleteNodeAndChildren(Long id) {
		Query q = entityManager.createNativeQuery(
				"SELECT em.* FROM products_app.movemedicalproduct as em WHERE em.parent = ?", MoveMedicalProduct.class);
		q.setParameter(1, id);
		List<MoveMedicalProduct> childIds = q.getResultList();
		// remove all children
		for (MoveMedicalProduct childId : childIds) {
			entityManager.remove(childId);

		}
		// remove parent
		q = entityManager.createNativeQuery("SELECT em.* FROM products_app.movemedicalproduct as em WHERE em.id = ?",
				MoveMedicalProduct.class);
		q.setParameter(1, id);
		Object parent = q.getSingleResult();
		entityManager.remove(parent);
	}

	@Override
	@Transactional
	public void saveNodes(List<MoveMedicalProduct> moveMedicalProductList, MoveMedicalProduct parentNode) {
		String query = "insert into movemedicalproduct values(?,?,?)";
		for (MoveMedicalProduct moveMedicalProduct : moveMedicalProductList) {
			entityManager.createNativeQuery(query).setParameter(1, moveMedicalProduct.getId())
					.setParameter(2, parentNode.getId()).setParameter(3, moveMedicalProduct.getTitle()).executeUpdate();
		}
	}
}

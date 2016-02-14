package com.wandrell.example.mule.swss.data.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.wandrell.example.mule.swss.data.model.sample.JPASample;

public final class JPASampleRepository {

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	public JPASampleRepository() {
		super();
	}

	public final JPASample findByCoordinates(final Float cod1, final Float cod2) {
		final Query builtQuery;
		final String query;
		JPASample entity;

		query = "SELECT sample FROM Sample sample WHERE sample.cod1 = :cod1 AND sample.cod2 = :cod2";

		builtQuery = getEntityManager().createQuery(query);
		builtQuery.setParameter("cod1", cod1);
		builtQuery.setParameter("cod2", cod2);

		// Tries to acquire the entity
		try {
			entity = (JPASample) builtQuery.getSingleResult();
		} catch (final NoResultException exception) {
			entity = new JPASample();
		}

		return entity;
	}

	private final EntityManager getEntityManager() {
		return entityManager;
	}

	public final void setEntityManager(final EntityManager em) {
		entityManager = em;
	}

}

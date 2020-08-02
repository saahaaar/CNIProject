package cni.tn.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cni.tn.entities.Person;

public class ExampleMain {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");
	private static EntityManagerFactory entityManagerFactory1 = Persistence
			.createEntityManagerFactory("persistenceUnitName");

	@PersistenceContext(unitName = "example-unit")
	private static EntityManager em = entityManagerFactory.createEntityManager();
	@PersistenceContext(unitName = "persistenceUnitName")
	private static EntityManager entityManager = entityManagerFactory1.createEntityManager();

	public static void main(String[] args) {
		try {

			List<Person> lst = findAllEmployeeEntities();
			for (Person person : lst) {
				System.out.println("looped");
				insertEmployeePostgreSql(person);

			}
			entityManagerFactory1.close();
		} finally {
			entityManagerFactory.close();

		}
	}

	private static List<Person> findAllEmployeeEntities() {
		System.out.println("-- finding Person entities --");

		TypedQuery<Person> query = em.createQuery("SELECT p from Person p", Person.class);
		List<Person> resultList = query.getResultList();
		for (Person person : resultList) {
			System.out.println(person.getFirstName());
		}
		entityManagerFactory.close();
		em.close();
		return resultList;
	}

	private static void insertEmployeePostgreSql(Person p) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		// Date now = new Date(10, 10, 2020);
		// Person person = new Person("sahar", "mansouri", "Tunis", now);
		entityManager.merge(p);
		entityManager.getTransaction().commit();
		// entityManager.close();
	}
}
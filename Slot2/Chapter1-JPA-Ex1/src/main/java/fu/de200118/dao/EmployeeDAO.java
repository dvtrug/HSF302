package fu.de200118.dao;

import fu.de200118.pojo.Employee;
import jakarta.persistence.*;

import java.util.List;

public class EmployeeDAO {
    private final EntityManagerFactory emf;

    public EmployeeDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Employee employee) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(employee);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            ex.printStackTrace();
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public Employee findByID(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Employee.class, id);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Employee> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "select e from Employee e";
            TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
            return query.getResultList();
        }  finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}

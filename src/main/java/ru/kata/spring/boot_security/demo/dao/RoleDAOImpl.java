package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository

public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r").getResultList();
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role findById(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Set<Role> findByIdRoles(List<Long> roles) {
        TypedQuery<Role> q = entityManager.createQuery("select r from Role r where r.id in :role", Role.class);
        q.setParameter("role", roles);
        return new HashSet<>(q.getResultList());
    }
}
package com.igormeshalkin.testtask.videowebserver.dao;

import com.igormeshalkin.testtask.videowebserver.entity.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FileDAO {
    SessionFactory sessionFactory;

    public FileDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<File> findAllByCurrentUser() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from File", File.class).getResultList()
                .stream()
                .filter(ut -> ut.getUserName().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(File file) {
        Session session = sessionFactory.getCurrentSession();
        session.save(file);
    }
}

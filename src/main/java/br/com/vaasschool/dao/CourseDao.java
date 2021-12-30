package br.com.vaasschool.dao;

import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.CourseVisibility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CourseDao {

    private EntityManager entityManager;

    public CourseDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Course course) {
        if (course.getId() == null) {
            this.entityManager.persist(course);
            System.out.println("Curso Id " + course.getId() + " inserido com sucesso!");
        } else {
            this.entityManager.merge(course);
            System.out.println("Curso Id " + course.getId() + " alterado com sucesso!");
        }
    }

    public void delete(String code) {
        Query jpqlDelete = entityManager.createQuery("delete from Course course where course.code = :code");
        jpqlDelete.setParameter("code", code);
        jpqlDelete.executeUpdate();
    }

    public void updateAllForPublicVisibility() {
        Query jpqlUpdate = entityManager.createQuery("update Course course set course.visibility = :public where course.visibility = :private");
        jpqlUpdate.setParameter("public", CourseVisibility.PUBLIC);
        jpqlUpdate.setParameter("private", CourseVisibility.PRIVATE);
        jpqlUpdate.executeUpdate();
    }

    public List<Course> searchAllPublic() {
        Query jpqlSearch = entityManager.createQuery("""
                            select course
                            from Course course                            
                            where course.visibility = :visibility                           
                """);

        jpqlSearch.setParameter("visibility", CourseVisibility.PUBLIC);

        return jpqlSearch.getResultList();
    }

    public List<Course> findAll() {
        return entityManager.createQuery("select course from Course course", Course.class).getResultList();
    }
}

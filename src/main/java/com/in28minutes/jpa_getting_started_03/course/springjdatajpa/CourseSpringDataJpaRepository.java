package com.in28minutes.jpa_getting_started_03.course.springjdatajpa;
public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long>{
	
	List<Course> findByAuthor(String author);
	List<Course> findByName(String name);

}
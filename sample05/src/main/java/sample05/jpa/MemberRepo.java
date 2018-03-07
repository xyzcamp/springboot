package sample05.jpa;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// ref: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
public interface MemberRepo extends JpaRepository<MemberEntity, Long> {

	List<MemberEntity> findByIdAndName(Long id, String name);
	List<MemberEntity> findByIdLessThan(Long id, Sort sort);
	Page<MemberEntity> findByIdGreaterThan(Long id, Pageable pageable);

	@Query(value = "select t1 from MemberEntity t1 where id > :x_id")
	public List<MemberEntity> query1(@Param("x_id") Long id, Pageable pageable);

	@Query(value = "select * from MemberTest t1 where m_id > :x_id", nativeQuery = true)
	public List<MemberEntity> query2(@Param("x_id") Long id);

}
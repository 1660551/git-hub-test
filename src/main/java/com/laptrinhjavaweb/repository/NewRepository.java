package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laptrinhjavaweb.entity.NewEntity;


public interface NewRepository extends JpaRepository<NewEntity, Long> {
	@Query(value =  "SELECT * FROM new u WHERE u.category_id = 3", nativeQuery = true)
	List<NewEntity> findAllNewCategoryChinhTri();
	
	@Query(value =  "SELECT * FROM new u ORDER BY category_id DESC \n-- #pageable\n"
			,countQuery = "select count(*) from new"
			, nativeQuery = true)
	Page<NewEntity> findAllNewWithPagination(Pageable pageable);
	
	@Query(
			  value = "SELECT * FROM new u "
			  		+ "inner join category c on u.category_id = c.id "
			  		+ "WHERE c.code = ?", 
			  nativeQuery = true)
	List<NewEntity> findNewByCategoryCodeNative(String code);
	
	@Query(value = "SELECT * FROM new u WHERE u.category_id = :category_id and u.thumbnail = :thumbnail"
			,nativeQuery = true)
	List<NewEntity> findUserByUserStatusAndUserName(@Param("category_id") Long category_id, 
	  @Param("thumbnail") String thumbnail);
	
	List<NewEntity> findAllByThumbnail(String thumbnail);
	
	@Query(value = "SELECT * FROM new u WHERE u.category_id IN :category_ids", nativeQuery = true)
	List<NewEntity> findNewByCategoryId(@Param("category_ids") List<Long> names);
	
	@Modifying
	@Query(value = "update new u set u.category_id = ? where u.id = ?", 
	  nativeQuery = true)
	int updateUserSetStatusForNameNative(Long category_id, Long Id);
	
}

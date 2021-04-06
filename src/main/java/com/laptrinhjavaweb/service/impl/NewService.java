package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.NewConverter;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.INewService;

//@Component("NewService")
@Service
public class NewService implements INewService {
	
	@Autowired
	private NewRepository newRepository;

	@Autowired
	private NewConverter newConverter;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> models = new ArrayList<>();
//		List<NewEntity> entities = newRepository.findAll(pageable).getContent();
//		List<NewEntity> entities = newRepository.findAllNewCategoryChinhTri();
//		List<NewEntity> entities = newRepository.findAllNewWithPagination(pageable).getContent();
//		List<NewEntity> entities = newRepository.findNewByCategoryCodeNative("the-thao");
//		List<NewEntity> entities = newRepository.findUserByUserStatusAndUserName(3L,"abc");
//		List<NewEntity> entities = newRepository.findAllByThumbnail("abc");
		List<Long> a = new ArrayList<>();
		a.add(1L);
		a.add(2L);
		List<NewEntity> entities = newRepository.findNewByCategoryId(a);
		
		for(NewEntity item : entities) {
			NewDTO newDTO = newConverter.toDto(item);
			models.add(newDTO);
		}
		return models;
		
	}


	@Override
	public Integer getTotalItem() {
		return (int) newRepository.count();
	}


	@Override
	public NewDTO findById(Long id) {
		NewEntity entity = newRepository.findOne(id);
		return newConverter.toDto(entity);
	}



	@Override
	public NewDTO save(NewDTO dto) {
		CategoryEntity category = categoryRepository.findOnEntityByCode(dto.getCategoryCode());
		NewEntity newEntity = new NewEntity();
		if(dto.getId() != null) {
			NewEntity oldNew = newRepository.findOne(dto.getId());
			oldNew.setCategory(category);
			newEntity = newConverter.toEntity(oldNew,dto);
		}else {
			newEntity = newConverter.toEntity(dto);
			newEntity.setCategory(category);
		}
		
		return newConverter.toDto(newRepository.save(newEntity));
	}

	
	

	@Override
	public void delete(long[] ids) {
		for(long id : ids) {
			newRepository.delete(id);
		}
	}


	@Override
	public NewDTO updateTest(NewDTO dto) {
		CategoryEntity category = categoryRepository.findOnEntityByCode(dto.getCategoryCode());
		NewEntity newEntity = new NewEntity();
		
		NewEntity oldNew = newRepository.findOne(dto.getId());
		oldNew.setCategory(category);
		newEntity = newConverter.toEntity(oldNew,dto);
		
		Integer idNew = (Integer) newRepository.updateUserSetStatusForNameNative(newEntity.getCategory().getId(),newEntity.getId());
		
		return newConverter.toDto(newRepository.findOne( 3L));
	}

}

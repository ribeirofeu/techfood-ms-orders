package com.fiap.techfood.infrastructure.repository;

import com.fiap.techfood.application.interfaces.gateways.CategoryRepository;
import com.fiap.techfood.domain.commons.HttpStatusCodes;
import com.fiap.techfood.domain.commons.exception.BusinessException;
import com.fiap.techfood.domain.products.Category;
import com.fiap.techfood.domain.products.Product;
import com.fiap.techfood.infrastructure.repository.entity.CategoryEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoryBdRepository implements CategoryRepository {

  private final SpringCategoryRepository repo;

  public CategoryBdRepository(SpringCategoryRepository repo) {
    this.repo = repo;
  }

  @Override
  public Optional<Category> findById(Long id) {
    Optional<CategoryEntity> entity = repo.findById(id);

      return entity.map(CategoryEntity::toCategory);
  }

  @Override
  public void save(Category category) {
    CategoryEntity entity = repo.save(new CategoryEntity(category));
    category.setId(entity.getId());
  }

  @Override
  public List<Category> findAll() {
    List<CategoryEntity> categoryEntities = repo.findAll();
    return categoryEntities.stream().map(CategoryEntity::toCategory).collect(Collectors.toList());
  }

  @Override
  public List<Product> findProductByCategory(Long id) {
    Optional<CategoryEntity> entity = repo.findById(id);

    if (entity.isPresent()) {
      return entity.get().getProducts();
    } else {
      return Collections.emptyList();
    }
  }

  @Override
  public void deleteCategory(Long id) {
      repo.deleteById(id);
  }
}


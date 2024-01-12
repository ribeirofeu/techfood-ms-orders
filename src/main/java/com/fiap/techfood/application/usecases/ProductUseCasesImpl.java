package com.fiap.techfood.application.usecases;

import com.fiap.techfood.domain.commons.HttpStatusCodes;
import com.fiap.techfood.domain.products.Category;
import com.fiap.techfood.domain.products.Product;
import com.fiap.techfood.application.dto.request.ProductRequestDTO;
import com.fiap.techfood.domain.commons.exception.BusinessException;
import com.fiap.techfood.application.interfaces.gateways.CategoryRepository;
import com.fiap.techfood.application.interfaces.gateways.ProductRepository;
import com.fiap.techfood.application.interfaces.usecases.ProductUseCases;

import java.util.List;

public class ProductUseCasesImpl implements ProductUseCases {

  private final ProductRepository productRepository;

  private final CategoryRepository categoryRepository;

  public ProductUseCasesImpl(final ProductRepository productRepository, CategoryRepository categoryRepository) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public Long createProduct(ProductRequestDTO dto) {
    Product product = Product.fromProductDTO(dto);

    Category category =
        categoryRepository
            .findById(dto.getCategoryId())
            .orElseThrow(
                () -> new BusinessException("Invalid Category ID", HttpStatusCodes.BAD_REQUEST));
    product.setCategory(category);

    productRepository.save(product);
    return product.getId();
  }

  @Override
  public Product findProductById(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new BusinessException("Product ID not found", HttpStatusCodes.NOT_FOUND));
  }

  @Override
  public List<Product> findAllProducts() {
    return productRepository.findAll();
  }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }

  @Override
  public void updateProduct(Long id, ProductRequestDTO dto) {
    Product product = Product.fromProductDTO(dto);
    product.setId(id);

    Category category =
        categoryRepository
            .findById(dto.getCategoryId())
            .orElseThrow(
                () -> new BusinessException("Invalid Category ID", HttpStatusCodes.BAD_REQUEST));
    product.setCategory(category);
      productRepository.updateProduct(product);
  }
}

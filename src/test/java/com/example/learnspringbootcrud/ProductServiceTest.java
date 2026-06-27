package com.example.learnspringbootcrud;

import com.example.learnspringbootcrud.entity.Product;
import com.example.learnspringbootcrud.exception.ResourceNotFoundException;
import com.example.learnspringbootcrud.repository.ProductRepository;
import com.example.learnspringbootcrud.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("Lightweight laptop");
        product.setPrice(BigDecimal.valueOf(999.99));
    }

    @Test
    void shouldGetProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product found = productService.getById(1L);

        assertThat(found.getName()).isEqualTo("Laptop");
        verify(productRepository).findById(1L);
    }

    @Test
    void shouldThrowWhenProductNotFound() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.getById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void shouldUpdateProduct() {
        Product updated = new Product();
        updated.setName("Updated Laptop");
        updated.setDescription("Updated description");
        updated.setPrice(BigDecimal.valueOf(899.99));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product result = productService.update(1L, updated);

        assertThat(result.getName()).isEqualTo("Updated Laptop");
        assertThat(result.getPrice()).isEqualByComparingTo("899.99");
        verify(productRepository).save(product);
    }
}

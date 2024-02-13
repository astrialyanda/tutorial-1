package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productService.create(product);
        assertEquals(product, createdProduct);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testDeleteProductById() {
        String productId = "123";
        productService.deleteById(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void testFindProductById() {
        String productId = "123";
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(product);
        Product foundProduct = productService.findById(productId);
        assertEquals(product, foundProduct);
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        productService.update(product);
        verify(productRepository, times(1)).update(product);
    }

    @Test
    void testFindAllProducts() {
        List<Product> productList = Arrays.asList(new Product(), new Product());
        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);
        List<Product> foundProducts = productService.findAll();
        assertEquals(productList.size(), foundProducts.size());
        verify(productRepository, times(1)).findAll();
    }
}





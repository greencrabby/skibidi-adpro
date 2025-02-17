package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Low Taper Fade");
        product.setProductQuantity(100);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals(product.getProductId(), createdProduct.getProductId());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> allProducts = productService.findAll();

        assertNotNull(allProducts);
        assertEquals(1, allProducts.size());
        assertEquals(product.getProductId(), allProducts.getFirst().getProductId());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdProductExists() {
        when(productRepository.findById(product.getProductId())).thenReturn(product);

        Product foundProduct = productService.findById(product.getProductId());

        assertNotNull(foundProduct);
        assertEquals(product.getProductId(), foundProduct.getProductId());
        verify(productRepository, times(1)).findById(product.getProductId());
    }

    @Test
    void testFindByIdProductDoesNotExist() {
        when(productRepository.findById("skibidi-id")).thenReturn(null);

        Product foundProduct = productService.findById("skibidi-id");

        assertNull(foundProduct);
        verify(productRepository, times(1)).findById("skibidi-id");
    }

    @Test
    void testUpdateProduct() {
        doNothing().when(productRepository).update(product);

        productService.update(product);

        verify(productRepository, times(1)).update(product);
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).delete(product.getProductId());

        productService.delete(product.getProductId());

        verify(productRepository, times(1)).delete(product.getProductId());
    }
}

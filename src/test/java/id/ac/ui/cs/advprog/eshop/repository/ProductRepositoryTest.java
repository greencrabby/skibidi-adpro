package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Low Taper Fade");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct () {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Low Taper Fade");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Massive");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct1 = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct1.getProductId());
        Product savedProduct2 = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct2.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product originalProduct = new Product();
        originalProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        originalProduct.setProductName("Low Taper Fade");
        originalProduct.setProductQuantity(100);
        productRepository.create(originalProduct);

        originalProduct.setProductName("Massive");
        originalProduct.setProductQuantity(50);
        productRepository.update(originalProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product updatedProduct = productIterator.next();

        assertEquals(originalProduct.getProductId(), updatedProduct.getProductId());
        assertEquals(originalProduct.getProductName(), updatedProduct.getProductName());
        assertEquals(originalProduct.getProductQuantity(), updatedProduct.getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        Product productToDelete = new Product();
        productToDelete.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productToDelete.setProductName("Egypt Property");
        productToDelete.setProductQuantity(100);
        productRepository.create(productToDelete);

        productRepository.delete(productToDelete.getProductId());
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
}

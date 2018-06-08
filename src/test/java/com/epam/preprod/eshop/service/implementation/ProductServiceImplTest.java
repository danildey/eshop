package com.epam.preprod.eshop.service.implementation;

import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.entity.TextBook;
import com.epam.preprod.eshop.repository.ProductRepository;
import com.epam.preprod.eshop.service.ProductService;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {
    private ProductService productService;
    private ProductRepository mockedRepository;
    private TextBook product1;
    private TextBook product2;
    private TextBook product3;

    @Before
    public void setUp() {
        mockedRepository = mock(ProductRepository.class);
        product1 = new TextBook(1, 100, "name1", "desc1", "Mathematica1", "c1");
        product2 = new TextBook(2, 200, "name2", "desc2", "Mathematica2", "c2");
        product3 = new TextBook(3, 300, "name3", "desc3", "Mathematica3", "c3");

        Map<Product, Integer> products = new HashMap<>();
        products.put(product1, 1);
        products.put(product2, 1);

        when(mockedRepository.getAllProducts()).thenReturn(products);
        when(mockedRepository.getProduct(1)).thenReturn(product1);
        when(mockedRepository.getProduct(2)).thenReturn(product2);
        when(mockedRepository.addProduct(product1)).thenReturn(1).thenReturn(2);
        when(mockedRepository.addProduct(product2, 1)).thenReturn(2);
        when(mockedRepository.addProduct(product3)).thenReturn(null);
        when(mockedRepository.removeProduct(product1)).thenReturn(1);
        productService = new ProductServiceImpl(mockedRepository);
    }


    @Test
    public void shouldReturnAllProductFromRepository() throws Exception {
        Map<Product, Integer> allProducts = mockedRepository.getAllProducts();
        assertEquals(2, allProducts.size());
        TextBook product3 = (TextBook) allProducts.keySet().iterator().next();
        assertEquals(product1.getId(), product3.getId());
        assertEquals(product1.getPrice(), product3.getPrice());
        assertEquals(product1.getDescription(), product3.getDescription());
        assertEquals(product1.getSubjectArea(), product3.getSubjectArea());
        assertEquals(product1.getClassification(), product3.getClassification());
    }

    @Test
    public void shouldReturnIdOfRemovedProduct() throws Exception {
        int actually = productService.removeProduct(product1);
        assertEquals(1, actually);
    }

    @Test
    public void shouldReturnProductById() throws Exception {
        assertEquals(product1, productService.getProduct(1));
    }

    @Test
    public void shouldAddProductWithQuantityAndReturnModifiedProductId() throws Exception {
        int actually = productService.addProduct(product2, 1);
        assertEquals(2, actually);
        assertTrue(productService.getAllProducts().containsKey(product2));
    }

    @Test
    public void shouldAddProductAndReturnModifiedProductId() throws Exception {
        productService.addProduct(product1);
        int actually = productService.addProduct(product1);
        assertEquals(2, actually);
    }

    @Test
    public void shouldAddProductAndReturnNull() throws Exception {
        Integer actually = productService.addProduct(product3);
        assertNull(actually);
    }
}

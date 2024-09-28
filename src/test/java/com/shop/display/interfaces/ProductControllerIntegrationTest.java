package com.shop.display.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.display.domain.Product;
import com.shop.display.infrastructure.jpa.ProductJPARepository;
import com.shop.display.interfaces.model.ProductCommand;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.shop.display.domain.Category.TOPS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("integrationTest")
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductJPARepository productJPARepository;


    private final String V1_PREFIX = "/v1";
    private ProductCommand productCommand;

    @BeforeEach
    public void setUp() {
        productCommand = new ProductCommand("A", "TOPS", 10000L);
    }

    @Test
    public void addProduct() throws Exception {
        // given
        productCommand = new ProductCommand("A", "TOPS", 10000L);

        // when, then
        mockMvc.perform(put(V1_PREFIX + "/products/new")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productCommand)))
               .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void updateProduct() throws Exception {
        // given
        productCommand = new ProductCommand("B", "TOPS", 15000L);
        long productId = 1L;

        // when, then
        mockMvc.perform(post(V1_PREFIX + "/products/" + productId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productCommand)))
               .andExpect(status().isOk());
        Product result = productJPARepository.findById(1L)
                                              .orElse(null);
        assertThat(result).isNotNull();
        assertThat(result.getBrandName()).isEqualTo("B");
        assertThat(result.getCategory()).isEqualTo(TOPS);
        assertThat(result.getPrice()).isEqualTo(15000L);
    }

    // todo(jaewhi) - 실패 테스트 작성하기(brand not found 등)

    @Test
    @Transactional
    public void deleteProduct() throws Exception {
        // given
        long productId = 1L;

        // when, then
        mockMvc.perform(delete(V1_PREFIX + "/products/" + productId))
               .andExpect(status().isOk());
        Product result = productJPARepository.findById(1L)
                                              .orElse(null);
        assertThat(result).isNull();
    }
}
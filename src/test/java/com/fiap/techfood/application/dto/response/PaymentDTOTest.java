package com.fiap.techfood.application.dto.response;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentDTOTest {

    @Test
    public void testConstructorAndGetter() {
        // Arrange
        Long id = 123L;
        BigDecimal totalValue = BigDecimal.valueOf(100.50);

        // Act
        PaymentDTO paymentDTO = new PaymentDTO(id, totalValue);

        // Assert
        assertEquals(id, paymentDTO.getId());
        assertEquals(totalValue, paymentDTO.getTotalValue());
        assertNull(paymentDTO.getQrCode());
    }
}

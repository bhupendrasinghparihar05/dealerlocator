package com.cdk.dealerlocator.controller;

import com.cdk.dealerlocator.dto.DealerDto;
import com.cdk.dealerlocator.entity.Dealer;
import com.cdk.dealerlocator.exception.ResourceNotFoundException;
import com.cdk.dealerlocator.mapper.DealerMapper;
import com.cdk.dealerlocator.service.DealerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//import static org.mockito.Mockito.when;

public class DealerControllerTest {

    @Mock
    private DealerService dealerService;

    @InjectMocks
    private DealerController dealerController;

    public DealerControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDealerById_ResourceFound() {
        // Arrange
        Long dealerId = 1L;
        Dealer dealer = new Dealer(1L, "Metro Auto", "123 Main St", "Los Angeles", "CA", "90001", 34.052235, -118.243683);
        when(dealerService.getDealerById(dealerId)).thenReturn(DealerMapper.mapToDealerDto(dealer));

        // Act
        ResponseEntity<DealerDto> response = dealerController.getDealerById(dealerId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(dealerId, response.getBody().getId());
        assertEquals("Metro Auto", response.getBody().getName());
    }

    @Test
    public void testGetDealerById_ResourceNotFound() {
        // Arrange
        Long dealerId = 1L;
        when(dealerService.getDealerById(dealerId)).thenThrow(new ResourceNotFoundException("Dealer does not exist with given id : " + dealerId));

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            dealerController.getDealerById(dealerId);
        });

        // Assert
        assertEquals("Dealer does not exist with given id : " + dealerId, exception.getMessage());
    }

    @Test
    public void testGetDealersByZipcodeWithoutZipCode() {
        // Arrange
        Dealer dealer1 = new Dealer(1L, "Metro Auto", "123 Main St", "Los Angeles", "CA", "90001", 34.052235, -118.243683);
        Dealer dealer2 = new Dealer(2L, "City Motors", "456 Sunset Blvd", "Los Angeles", "CA", "90028", 34.097803, -118.328661);
        List<DealerDto> dealerDtos = new ArrayList<>();
        dealerDtos.add(DealerMapper.mapToDealerDto(dealer1));
        dealerDtos.add(DealerMapper.mapToDealerDto(dealer2));
        when(dealerService.getAllDealers()).thenReturn(dealerDtos);

        // Act
        ResponseEntity<List<DealerDto>> response  = dealerController.getDealersByZipcode("");

        // Assert
        assertNotNull(response);
        assertEquals(2, response.getBody().size());
        assertEquals("Metro Auto", response.getBody().get(0).getName());
    }

    @Test
    public void testGetDealersByZipcodeWithZipCode() {
        // Arrange
        String zipCode = "90028";
        Dealer dealer2 = new Dealer(2L, "City Motors", "456 Sunset Blvd", "Los Angeles", "CA", "90028", 34.097803, -118.328661);
        Dealer dealer3 = new Dealer(2L, "City Motors New Branch", "456 Sunset Blvd", "Los Angeles", "CA", "90028", 34.097803, -118.328661);
        List<DealerDto> dealerDtos = new ArrayList<>();
        dealerDtos.add(DealerMapper.mapToDealerDto(dealer2));
        dealerDtos.add(DealerMapper.mapToDealerDto(dealer3));
        when(dealerService.getDealersByZipCode(zipCode)).thenReturn(dealerDtos);

        // Act
        ResponseEntity<List<DealerDto>> response  = dealerController.getDealersByZipcode(zipCode);

        // Assert
        assertNull(response);
        assertEquals(2, response.getBody().size());
        assertEquals("City Motors New Branch", response.getBody().get(1).getName());
    }
}

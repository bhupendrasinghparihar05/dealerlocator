package com.cdk.dealerlocator.controller;

import com.cdk.dealerlocator.dto.DealerDto;
import com.cdk.dealerlocator.service.DealerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/dealers")
public class DealerController {

    private DealerService dealerService;

    // Build Add Dealer REST API
    @PostMapping
    public ResponseEntity<DealerDto> createDealer(@RequestBody DealerDto dealerDto) {
        DealerDto savedDealer = dealerService.createDealer(dealerDto);
        return new ResponseEntity<>(savedDealer, HttpStatus.CREATED);
    }


    // Build get Dealer REST API
    @GetMapping("{id}")
    public ResponseEntity<DealerDto> getDealerById(@PathVariable("id") Long dealerId) {
        DealerDto dealerDto  = dealerService.getDealerById(dealerId);
        return ResponseEntity.ok(dealerDto);
    }

    // Build get all dealers REST API
//    @GetMapping
//    public ResponseEntity<List<DealerDto>> getAllDealers() {
//        List<DealerDto> dealerDtos = dealerService.getAllDealers();
//        return ResponseEntity.ok(dealerDtos);
//    }

    // Update dealer REST API
    @PutMapping("{id}")
    public ResponseEntity<DealerDto> updateDealer(@PathVariable("id") Long dealerId,
                                                  @RequestBody DealerDto updatedDealer) {
        DealerDto dealerDto = dealerService.updateDealer(dealerId, updatedDealer);

        return ResponseEntity.ok(dealerDto);
    }

    // Delete dealer REST API by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDealer(@PathVariable("id") Long dealerId) {
        dealerService.deleteDealer(dealerId);
        return ResponseEntity.ok("Dealer deleted successfully");
    }

    // Get Dealers by Zipcode
    @GetMapping
    public ResponseEntity<List<DealerDto>> getDealersByZipcode(@RequestParam(name = "zipCode", required = false) String zipCode) {
        List<DealerDto> dealerDtos;
        if (zipCode == null || zipCode.isEmpty()) {
            dealerDtos = dealerService.getAllDealers();
        } else {
            dealerDtos = dealerService.getDealersByZipCode(zipCode);
        }

        return ResponseEntity.ok(dealerDtos);
    }
}

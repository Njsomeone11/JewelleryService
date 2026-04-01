package com.shop.jewelleryservice.controllers;

import com.shop.jewelleryservice.services.JewelleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jewellery")
@RequiredArgsConstructor
public class JewelleryController {

    private final JewelleryService jewelleryService;

    @GetMapping("/")
    public void getJewelleryItems(){}

    @PostMapping("/")
    public void createJewelleryItem(){}

    @PatchMapping("/")
    public void updateJewelleryItem(){}

    @DeleteMapping("/")
    public void deleteJewelleryItem(){}
}

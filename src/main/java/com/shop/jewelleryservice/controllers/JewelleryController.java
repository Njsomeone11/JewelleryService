package com.shop.jewelleryservice.controllers;

import com.shop.jewelleryservice.dto.JewelleryItemsDto;
import com.shop.jewelleryservice.entity.JewelleryItems;
import com.shop.jewelleryservice.services.JewelleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/jewellery")
@RequiredArgsConstructor
public class JewelleryController {

    private final JewelleryService jewelleryService;

    /**@Paramters : No input parameters are passed as everytime we call this API, it will get the list of items
     * @apiNote : This API gets the list of Jewellery items from the database
     * @return : It returns a list of Jewellery items in JSON format with HTTP status 200
     */
    @GetMapping("/getItems")
    public ResponseEntity<Map<String, Object>> getJewelleryItems(){
        return ResponseEntity.ok(jewelleryService.getJewelleryItems());
    }

    /**@Paramters : User filled details like name, metal type, quantity, making charges, final charge, availability will be received from screen
     * @apiNote : This API creates the jewellery item user wants to create
     * @return : It returns the JewelleryItems entity details back as response
     */
    @PostMapping("/createItem")
    public ResponseEntity<JewelleryItems> createJewelleryItem(@RequestBody JewelleryItemsDto values){
        return ResponseEntity.status(HttpStatus.CREATED).body(jewelleryService.createJewelleryItem(values));
    }

    @PatchMapping("/updateItem")
    public ResponseEntity<JewelleryItems> updateJewelleryItem(@RequestBody Map<String, Object> values){
        return ResponseEntity.ok(jewelleryService.updateJewelleryItem(values));
    }

    @DeleteMapping("/deleteItem")
    public ResponseEntity<Map<String, Object>> deleteJewelleryItem(@RequestBody Map<String, Object> values){
        return ResponseEntity.ok(jewelleryService.deleteJewelleryItem(values));
    }
}

package com.shop.jewelleryservice.controllers;

import com.shop.jewelleryservice.dto.JewelleryItemsDto;
import com.shop.jewelleryservice.entity.JewelleryItems;
import com.shop.jewelleryservice.services.JewelleryService;
import jakarta.validation.Valid;
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
    public ResponseEntity<JewelleryItems> createJewelleryItem(@RequestBody @Valid JewelleryItemsDto values){
        return ResponseEntity.status(HttpStatus.CREATED).body(jewelleryService.createJewelleryItem(values));
    }

    /**@Paramters : User may update the fields like name, metal type, quantity, making charges, final charge, availability will be received from screen
     * @apiNote : This API updates the jewellery item fields user wants to update
     * @return : It returns the JewelleryItems entity details back as response
     */
    @PutMapping("/updateItem/{id}")
    public ResponseEntity<JewelleryItems> updateJewelleryItem(@PathVariable Long id, @RequestBody JewelleryItemsDto values){
        return ResponseEntity.ok(jewelleryService.updateJewelleryItem(id, values));
    }

    /**@Paramters : User may delete the item from the list
     * @apiNote : This API deletes the jewellery item
     * @return : It returns a String "Item deleted"
     */
    @DeleteMapping("/deleteItem/{id}")
    public ResponseEntity<String> deleteJewelleryItem(@PathVariable Long id){
        return ResponseEntity.ok(jewelleryService.deleteJewelleryItem(id));
    }
}

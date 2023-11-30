package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {
    private final List<String> contacts = new ArrayList<>();
    // curl localhost:8080/contacts
    @GetMapping("contacts")
    public ResponseEntity<List<String>> getContacts() {
        return ResponseEntity.ok(contacts);
    }
    // curl -X POST localhost:8080/contacts -d "data"
    @PostMapping("contacts")
    public ResponseEntity<Void> addContacts(@RequestBody String text) {
        contacts.add(text);
        return ResponseEntity.accepted().build();
    }
    // curl -X DELETE localhost:8080/contacts/0
    @DeleteMapping("contacts/{index}")
    public ResponseEntity<Void> deleteContacts(@PathVariable("index") Integer
                                                   index) {
        contacts.remove((int) index);
        return ResponseEntity.noContent().build();
    }
    // curl -X PUT localhost:8080/contacts/0 -d "data"
    @PutMapping("contacts/{index}")
    public ResponseEntity<Void> updateContacts(
            @PathVariable("index") Integer i,
            @RequestBody String contact) {
        contacts.remove((int) i);
        contacts.add(i, contact);
        return ResponseEntity.accepted().build();
    }


    // curl -X POST localhost:8080/contacts/1/create -d "data"
    @PostMapping("/contacts/{index}/create")
    public ResponseEntity<Void> createContacts(
            @PathVariable("index") Integer i,
            @RequestBody String contact) {
        contacts.add(i, contact);
        return ResponseEntity.accepted().build();
    }
    // curl -X DELETE localhost:8080/contacts/search/data
    @DeleteMapping("/contacts/search/{text}")
    public ResponseEntity<Void> searchAndDeleteContacts(@PathVariable("text") String text) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).contains(text)) {
                contacts.remove(i);
                i--;
            }
        }
        return ResponseEntity.accepted().build();
    }






}
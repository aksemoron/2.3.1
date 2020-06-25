package ru.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/admin/rest")
public class RestControllers {

    @GetMapping("/infoOfUser")
    public ResponseEntity<String> printUsers() {
        System.out.println("-------------------------------------------------------------------------");
        return new ResponseEntity<>("вввв", HttpStatus.OK);
    }

}

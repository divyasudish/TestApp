package com.divya.testGateway;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by divyashreenair on 23/3/16.
 */
@RestController
public class TestGateway {
    HashMap<String, List<Test>> map;
    ObjectList obj1;
    @RequestMapping("/Hello1")
    public String hello(){
        return "Hello string";
    }

    @RequestMapping(value = "/user123", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<ObjectList> createUser1(@RequestBody String jsonString) throws IOException {
        System.out.println("Json String " + jsonString);
        obj1 = new ObjectList();
        convertJsonStringToObject1(jsonString);
        obj1.setClients(map);
        return new ResponseEntity<ObjectList>(obj1, HttpStatus.OK);
    }
    @RequestMapping(value = "/user123", method = RequestMethod.GET)
    public ResponseEntity<ObjectList> getClients1(){
        obj1.getClients();
        return new ResponseEntity<ObjectList>(obj1, HttpStatus.OK);
    }

    public void convertJsonStringToObject1(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = new HashMap<String, List<Test>>();
            map = mapper.readValue(jsonString, new TypeReference<HashMap<String, List<Test>>>(){});
            if (map == null) {
                throw new NullPointerException("Json conversion failed");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

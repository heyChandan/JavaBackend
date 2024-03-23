package com.jbdl63.library.Controller;

import com.jbdl63.library.Model.Author;
import com.jbdl63.library.Service.RedisService;
import com.jbdl63.library.dto.RangeDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/redis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RedisController {

    @Autowired
    RedisService redisService;

    @PostMapping
    public void addNewAuthor(@RequestBody Author author){
        redisService.addNewData(author);
    }

    @GetMapping("/{id}")
    public Author getAuthorDetailsById(@PathVariable Integer id){
        return redisService.getAuthorDetailsById(id);
    }

    @PostMapping("/list")
    public void addAuthorToList(@RequestBody Author author){
        redisService.addDataToList(author);
    }

    @GetMapping("/list/{start}/{end}")
    public RangeDataDto fetchAuthorFromList(@PathVariable("start") Integer start, @PathVariable("end") Integer end){
        return redisService.fetchAuthorFromList(start, end);
    }

    @PostMapping("/set")
    public void addNewDataToSet(@RequestBody Author author){
        redisService.addNewDataToSet(author);
    }

    @GetMapping("/set/{count}")
    public List<Author> addNewDataToSet(@PathVariable Integer count){
        return redisService.getRandomNumbers(count);
    }

    @PostMapping("/hash")
    public void addNewAuthorInHash(@RequestBody Author author){
        redisService.addNewDataToHash(author);
    }

}

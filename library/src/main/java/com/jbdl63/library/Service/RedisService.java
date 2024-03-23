package com.jbdl63.library.Service;

import com.jbdl63.library.Model.Author;
import com.jbdl63.library.dto.RangeDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@Service
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    private final String key = "author::";

    /*
    * 1. String
    * 2. List
    * 3. Set
    * 4. HashMap
     */
    public void addNewData(Author author){
        redisTemplate.opsForValue().setIfAbsent(key+author.getAuthorId(), author);
    }

    public Author getAuthorDetailsById(Integer id) {
        return (Author)redisTemplate.opsForValue().get(key+id);
    }

    //List
    public void addDataToList(Author author){
        redisTemplate.opsForList().leftPush(key+"_list", author);
    }

    public RangeDataDto fetchAuthorFromList(Integer start, Integer end){
        List<Author> authorList = redisTemplate.opsForList().range(key+"_list", start, end);
        return RangeDataDto.builder().size(authorList.size()).data(authorList).build();
    }

    //Set
    public void addDataToSet(Author author){
        redisTemplate.opsForSet().add(key+"_list", author);
    }


    public void addNewDataToSet(Author author) {
        Random random = new Random();
        author.setAuthorId(random.nextInt(1000));
        redisTemplate.opsForSet().add(key+"_set", author);
    }

    public List<Author> getRandomNumbers(Integer count) {
        return redisTemplate.opsForSet().randomMembers(key+"_set", count);
    }







    public void addNewDataToHash(Author author) {
        redisTemplate.opsForHash().put(key+"_hash", author.getAuthorId(), author);

        Map<Integer, Author> entries = redisTemplate.opsForHash().entries(key+"_hash");
        Set<Map.Entry<Integer,Author>> set = entries.entrySet();
        for(Map.Entry<Integer, Author> o: set){
            System.out.println(o.getKey()+"\t"+o.getValue());
        }
    }
}

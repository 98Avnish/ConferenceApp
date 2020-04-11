package com.learning.conference.controllers;

import com.learning.conference.models.Session;
import com.learning.conference.repos.SessionRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {

    @Autowired
    private SessionRepo sessionRepo;

    @GetMapping
    public List<Session> finaAll(){
        return sessionRepo.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session findOne(@PathVariable Long id){
        return sessionRepo.getOne(id);
    }

    @PostMapping
    public Session create(@RequestBody final Session session){
        return sessionRepo.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        sessionRepo.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session){
        Session existingSession = sessionRepo.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepo.saveAndFlush(existingSession);
    }
}

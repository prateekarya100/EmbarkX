package com.social.media.controller;

import com.social.media.models.SocialUser;
import com.social.media.service.PostService;
import com.social.media.service.SocialGroupService;
import com.social.media.service.SocialProfileService;
import com.social.media.service.SocialUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SocialControllers {

    @Autowired
    private SocialUserService socialUserService;

    @Autowired
    private SocialProfileService socialProfileService;

    @Autowired
    private SocialGroupService socialGroupService;

    @Autowired
    private PostService postService;

    @PostMapping("/social/user")
    public ResponseEntity<SocialUser> saveSocialUser(@RequestBody SocialUser socialUser){
        return new ResponseEntity<>(socialUserService.createSocialUser(socialUser), HttpStatus.CREATED);
    }

    @GetMapping("/social/users")
    public ResponseEntity<List<SocialUser>> getAllSocialUsers(){
        return new ResponseEntity<>(socialUserService.getAllSocialUsers(), HttpStatus.OK);
    }
}

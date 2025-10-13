package com.social.media.service;

import com.social.media.models.SocialUser;
import com.social.media.repositories.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialUserService {

    @Autowired
    private SocialUserRepository socialUserRepository;


    public SocialUser createSocialUser(SocialUser socialUser) {
        return socialUserRepository.save(socialUser);
    }

    public List<SocialUser> getAllSocialUsers() {
        return socialUserRepository.findAll();
    }
}

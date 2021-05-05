package com.example.demo.account.profile;

import com.example.demo.account.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileService {

    private final AccountService accountService;
    private final ProfileRepository profileRepository;

    public Optional<List<Profile>> getProfiles(){
        return profileRepository.findProfilesByAccount(accountService.getCurrentAccount());
    }
    public Optional<Profile> getProfile(Long profileID) {
        return profileRepository.findProfileByID(profileID, accountService.getCurrentAccount());
    }
}
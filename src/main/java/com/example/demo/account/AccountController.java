package com.example.demo.account;

import com.example.demo.account.profile.Profile;
import com.example.demo.account.profile.ProfileRequest;
import com.example.demo.account.profile.ProfileService;
import com.example.demo.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/account")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final ProfileService profileService;
    private final AccountRepository accountRepository;

    @GetMapping
    public List<Account> account(){
        return accountService.grabAllAccounts();
    }

    @PostMapping(path = "profile")
    public String profile(@RequestBody ProfileRequest request) { return accountService.profile(request);
    }
    @GetMapping(path = "profile")
    public Optional<List<Profile>> getProfiles(){
        return profileService.getProfiles();
    }
    @GetMapping(path = "profile/{profileID}")
    public Optional<Profile> getProfileByID(@PathVariable Long profileID){
        return profileService.getProfile(profileID);
    }

}

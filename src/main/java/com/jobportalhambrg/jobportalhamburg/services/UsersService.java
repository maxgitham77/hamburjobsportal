package com.jobportalhambrg.jobportalhamburg.services;

import com.jobportalhambrg.jobportalhamburg.entity.JobSeekerProfile;
import com.jobportalhambrg.jobportalhamburg.entity.RecruiterProfile;
import com.jobportalhambrg.jobportalhamburg.entity.Users;
import com.jobportalhambrg.jobportalhamburg.repository.JobSeekerProfileRepository;
import com.jobportalhambrg.jobportalhamburg.repository.RecruiterProfileRepository;
import com.jobportalhambrg.jobportalhamburg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {

    private final UserRepository userRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    public UsersService(
            UserRepository userRepository,
            JobSeekerProfileRepository jobSeekerProfileRepository,
            RecruiterProfileRepository recruiterProfileRepository) {
        this.userRepository = userRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
    }

    public Users addUser(Users users) {
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        int userTypeId = users.getUserTypeId().getUserTypeId();
        Users savedUser = userRepository.save(users);
        if (userTypeId == 1) {
            recruiterProfileRepository.save(new RecruiterProfile(savedUser));
        } else {
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
        }
        return savedUser;
    }

    public Optional<Users> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}

package com.server.deeply.profile.repositorty;

import com.server.deeply.profile.dto.ProfileSearchRequestDto;
import com.server.deeply.profile.jpa.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {

}

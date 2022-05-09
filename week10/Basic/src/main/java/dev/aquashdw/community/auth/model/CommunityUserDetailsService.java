package dev.aquashdw.community.auth.model;

import dev.aquashdw.community.entity.AreaEntity;
import dev.aquashdw.community.entity.UserEntity;
import dev.aquashdw.community.repository.AreaRepository;
import dev.aquashdw.community.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CommunityUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CommunityUserDetailsService.class);
    private final UserRepository userRepository;
    private final AreaRepository areaRepository;
    private final PasswordEncoder passwordEncoder;

    public CommunityUserDetailsService(UserRepository userRepository, AreaRepository areaRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.areaRepository = areaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(
                ()->new UsernameNotFoundException(username)
        );
        logger.info("username : {}",username);
        return new User(userEntity.getUsername(), userEntity.getPassword(),new ArrayList<>());
        // UserDetails를 상속받는 User를 반환함으로써 사용 가능
        // 마지막 인자는 아무 Collection이나 넣으면 됨
    }

    public void createUser(String username, String password, Boolean isShopOwner){
        UserEntity newUser = new UserEntity();


        List<AreaEntity> areaEntityList = new ArrayList<>();
        Iterable<AreaEntity> areaIterable = this.areaRepository.findAll();;
        areaIterable.forEach(entity -> {areaEntityList.add(entity);});
        Random random =new Random();
        AreaEntity aEnt= areaEntityList.get(random.nextInt(areaEntityList.size()));
        // Random AreaEntity 뽑기

        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setResidence(aEnt);
        newUser.setShopOwner(isShopOwner);
        this.userRepository.save(newUser);
    }
}

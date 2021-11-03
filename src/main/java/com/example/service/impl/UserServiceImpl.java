package com.example.service.impl;

import com.example.repository.UserRepository;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.until.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author:abc
 * @Description:
 * @params:$params$
 * @return: $returns$
 * 对于打工来说，选择一家公司是多么重要
 * How important it chooses a company for a worker
 * @Date: $date$ $time$
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private IdGenerator idGenerator;

    @Override
    public boolean add(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean del(String userId) {
        userRepository.deleteById(userId);
        return false;
    }

    @Override
    public List<User> findAll() {
        List<User> all = userRepository.findAll();
        return all;
    }

    @Override
    public UserDto sel(String userId) {
        UserDto userDto = new UserDto();
        Optional<User> opUser = userRepository.findById(userId);
        User user = opUser.get();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    @Override
    public List<User> seach(String userName,String phone, String realName) {
        List<User> list = userRepository.seach(userName,phone, realName);
        return list;
    }

    //page add
    @Override
    public Page<User> getUserList(int pageNum, int pageSize){
        Sort sort = new Sort(Sort.Direction.DESC, "phone");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        Page<User> users = userRepository.findAll(pageable);
        return  users;
    }


//    @Override
//    public List<User> fuzzyQuery(UserDto userDto) {
//        List<User> users = userRepository.fuzzyQuery(userDto);
//        return users;
//    }
}

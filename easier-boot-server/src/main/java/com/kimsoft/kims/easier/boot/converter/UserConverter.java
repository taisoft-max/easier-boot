package com.kimsoft.kims.easier.boot.converter;


import com.kimsoft.kims.easier.boot.dto.UserDto;
import com.kimsoft.kims.easier.boot.user.entity.User;

/**
 * @author Kimi
 * @date 2020/4/12
 */
public class UserConverter {
    public static UserDto user2DTO(User copy){
        UserDto dto = new UserDto();
        dto.setId(copy.getId());
        if (copy.getName()!=null){
            dto.setName(copy.getName());
        }
        if (copy.getEnName() != null){
            dto.setEnName(copy.getEnName());
        }
        if (copy.getAccount()!=null){
            dto.setAccount(copy.getAccount());
        }
        if (copy.getTitle()!=null){
            dto.setTitle(copy.getTitle());
        }
        if (copy.getDepartmentId()!=null){
            dto.setDepartmentId(copy.getDepartmentId());
        }
        if (copy.getDepartmentName()!=null){
            dto.setDepartmentName(copy.getDepartmentName());
        }
        if (copy.getPhone()!=null){
            dto.setPhone(copy.getPhone());
        }
        if (copy.getEmail()!=null){
            dto.setEmail(copy.getEmail());
        }

        return dto;
    }
}

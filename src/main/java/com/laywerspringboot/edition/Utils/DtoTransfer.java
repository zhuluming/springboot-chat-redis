package com.laywerspringboot.edition.Utils;

import com.laywerspringboot.edition.entity.Cases;
import com.laywerspringboot.edition.entity.Newspaper;
import com.laywerspringboot.edition.entity.Notice;
import com.laywerspringboot.edition.entity.User;
import com.laywerspringboot.edition.entity.dto.RegisterUser;
import com.laywerspringboot.edition.entity.dto.SearchDetailDto;
import com.laywerspringboot.edition.entity.dto.UploadCaseNoticePriceDto;
import com.laywerspringboot.edition.entity.dto.UserCheck;

import java.util.Date;

/**
 * @Author:小七
 * @createTime:2020-10-24-13-35
 */
public class DtoTransfer {
    /**
     * 注册用户转换成user
     * @param registerUser
     * @return
     */
    public static User transferUser(RegisterUser registerUser){
        User user = new User();
        user.setUsername(registerUser.getUsername());
        user.setPassword(registerUser.getPassword());
        user.setRealname(registerUser.getRealname());
        user.setPhoneid(registerUser.getPhoneid());
        user.setIdcard(registerUser.getIdcard());
        user.setPhotoaddress(registerUser.getPhotoaddress());
        return user;
    }
    public static User transferLoginUser(RegisterUser registerUser){
        User user = new User();
        user.setUsername(registerUser.getUsername());
        user.setPassword(registerUser.getPassword());
        return user;
    }
    public static User transferPhoneLoginUser(RegisterUser registerUser){
        User user = new User();
        user.setPhoneid(registerUser.getPhoneid());
        user.setUuid(registerUser.getUuid());
        return user;
    }
    public static Cases transferUpDtoToCases(UploadCaseNoticePriceDto dto){
        Cases cases = new Cases();
        cases.setCaseid(dto.getCaseid());
        cases.setParty(dto.getParty());
        cases.setPhone(dto.getPhone());
        cases.setIdcard(dto.getIdcard());
        cases.setCourt(dto.getCourt());
        cases.setRegion(dto.getRegion());
        cases.setLawyer(dto.getLawyer());
        return cases;
    }
    public static Notice transferUpDtoToNotice(UploadCaseNoticePriceDto dto){
        Notice notice = new Notice();
        notice.setNoticename(dto.getNoticename());
        notice.setType(dto.getType());
        notice.setCaseaddress(dto.getCaseid());
        notice.setPicture(dto.getPicture());
        notice.setCategory(dto.getCategory());
        Date date = new Date(System.currentTimeMillis());
        notice.setReleasetime(date);
        notice.setNoticeStatus("0");
        notice.setFinished("0");
        return notice;
    }

    public static Newspaper transferUpDtoToNewspaper(UploadCaseNoticePriceDto dto){
        Newspaper newspaper = new Newspaper();
        newspaper.setRegion(dto.getRegion());
        newspaper.setState("0");
        newspaper.setDay(dto.getDay());
        return newspaper;
    }
    public static SearchDetailDto transferToSearchDetailDto(Cases cases, Notice notice, Newspaper newspaper){
        SearchDetailDto searchDetailDto =
                new SearchDetailDto(cases.getCaseid(), cases.getParty(), cases.getPhone(),
                cases.getIdcard(), cases.getCourt(), cases.getRegion(), cases.getLawyer(),
                        notice.getNoticeStatus(), newspaper.getState());

        return searchDetailDto;
    }
    public static Newspaper transferUpDtoToNewspaper(UserCheck userCheck){
        Newspaper newspaper = new Newspaper();
        newspaper.setPlace(userCheck.getPlace());
        newspaper.setAddress(userCheck.getAddress());
        newspaper.setDetail(userCheck.getDetail());
        return newspaper;
    }

}

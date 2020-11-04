package com.laywerspringboot.edition.service;

import com.laywerspringboot.edition.entity.dto.SearchDto;
import com.laywerspringboot.edition.entity.dto.UserSearchDto;

import java.util.List;

/**
 * @Author:小七
 * @createTime:2020-10-31-15-28
 */
public interface SearchDtoService {
    /**
     * 通过法官名和案件id查
     * @param realname
     * @param caseId
     * @return
     */
    SearchDto SearchByLaywerNameAndCaseID(String realname, String caseId);

    /**
     * 通过法官名和当事人名查
     * @param realname
     * @param party
     * @return
     */
    SearchDto SearchByLaywerNameAndParty(String realname, String party);

    /**
     * 通过法官名查
     * @param layWer
     * @return
     */
    List<SearchDto> SearchByLaywerName(String layWer);
    /**
     * 通过用户真实姓名查
     * @param tokenRealName
     * @return
     */
    UserSearchDto SearchByPartyAndCaseID(String tokenRealName, String caseId);
    /**
     * 通过用户真实姓名查所有
     * @param name
     * @return
     */
    List<SearchDto> SearchByParty(String name);
}

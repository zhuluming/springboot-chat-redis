package com.laywerspringboot.edition.dao;

import com.laywerspringboot.edition.entity.dto.SearchDto;
import com.laywerspringboot.edition.entity.dto.UserSearchDto;

import java.util.List;

/**
 * @Author:小七
 * @createTime:2020-10-31-17-11
 */
public interface SearchDtoDao {
    /**
     * 通过法官名和案件id查
     * @param lawyer
     * @param caseId
     * @return
     */
    SearchDto SearchByCaseID(String lawyer, String caseId);

    /**
     * 通过法官名和当事人名查
     * @param lawyer
     * @param party
     * @return
     */
    SearchDto SearchByParty(String lawyer, String party);

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
    List<UserSearchDto> SearchByPartyAndCaseID(String tokenRealName, String caseId);
    /**
     * 通过用户真实姓名查所有
     * @param name
     * @return
     */
    List<SearchDto> SearchByName(String name);

    List<UserSearchDto> SearchByLaywerAndCaseID(String tokenRealName, String caseId);

    List<UserSearchDto> SearchByAdminAndCaseID(String tokenRealName, String caseId);
}

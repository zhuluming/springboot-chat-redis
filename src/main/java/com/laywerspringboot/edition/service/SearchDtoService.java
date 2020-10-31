package com.laywerspringboot.edition.service;

import com.laywerspringboot.edition.entity.dto.SearchDto;

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
}

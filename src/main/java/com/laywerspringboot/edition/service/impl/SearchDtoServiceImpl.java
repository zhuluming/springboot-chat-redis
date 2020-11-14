package com.laywerspringboot.edition.service.impl;

import com.laywerspringboot.edition.dao.NewspaperDao;
import com.laywerspringboot.edition.dao.NoticeDao;
import com.laywerspringboot.edition.dao.SearchDtoDao;
import com.laywerspringboot.edition.entity.Newspaper;
import com.laywerspringboot.edition.entity.Notice;
import com.laywerspringboot.edition.entity.dto.SearchDto;
import com.laywerspringboot.edition.entity.dto.UserSearchDto;
import com.laywerspringboot.edition.service.SearchDtoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 先查询缓存，如果缓存中没有，则查询mysql
 * @Author:小七
 * @createTime:2020-10-31-15-28
 */
@Service
public class SearchDtoServiceImpl implements SearchDtoService {
    @Resource
    private SearchDtoDao searchDtoDao;
    @Resource
    private NewspaperDao newspaperDao;
    @Resource
    private NoticeDao noticeDao;
    /**
     * 通过法官名和案件id查
     * @param realname
     * @param caseId
     * @return
     */
    //TODO redis中查
    @Override
    public SearchDto SearchByLaywerNameAndCaseID(String realname, String caseId) {
        return searchDtoDao.SearchByCaseID(realname, caseId);
    }
    /**
     * 通过法官名和当事人名查
     * @param realname
     * @param party
     * @return
     */
    //TODO redis中查
    @Override
    public SearchDto SearchByLaywerNameAndParty(String realname, String party) {
        return searchDtoDao.SearchByParty(realname, party);
    }
    /**
     * 通过法官名查
     * @param layWer
     * @return
     */
    //TODO redis中查
    @Override
    public List<SearchDto> SearchByLaywerName(String layWer) {
        return searchDtoDao.SearchByLaywerName(layWer);
    }

    @Override
    public List<UserSearchDto>  SearchByPartyAndCaseID(String tokenRole,String tokenRealName, String caseId) {
        List<UserSearchDto> userSearchDtoList = new ArrayList<UserSearchDto>();
        if (tokenRole.equals("用户")){
            userSearchDtoList = searchDtoDao.SearchByPartyAndCaseID(tokenRealName, caseId);
        }
        if (tokenRole.equals("法官")){
            userSearchDtoList = searchDtoDao.SearchByLaywerAndCaseID(tokenRealName, caseId);
        }
        if (tokenRole.equals("报社")){
            userSearchDtoList = searchDtoDao.SearchByAdminAndCaseID(tokenRealName, caseId);
        }
        if (userSearchDtoList.size() != 0){

            for (UserSearchDto userSearchDto : userSearchDtoList) {
                Notice notice = noticeDao.queryByCaseAddress(userSearchDto.getCaseid());
                Newspaper newspaper = newspaperDao.queryById(notice.getNId());
                userSearchDto.setState(newspaper.getState());
            }
        }

        return userSearchDtoList;
    }

    @Override
    public List<SearchDto> SearchByParty(String name) {
        List<SearchDto> searchDtos = searchDtoDao.SearchByName(name);
        for (SearchDto searchDto : searchDtos) {
            if (searchDto.getState() == null){
                searchDto.setState("0");
            }
        }
        return searchDtos;
    }
}

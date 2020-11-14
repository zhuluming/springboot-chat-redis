package com.laywerspringboot.edition.controller;


import com.laywerspringboot.edition.Utils.DtoTransfer;
import com.laywerspringboot.edition.Utils.JWTUtils;
import com.laywerspringboot.edition.Utils.R;
import com.laywerspringboot.edition.entity.Cases;
import com.laywerspringboot.edition.entity.Newspaper;
import com.laywerspringboot.edition.entity.Notice;
import com.laywerspringboot.edition.entity.dto.SearchDetailDto;
import com.laywerspringboot.edition.entity.dto.SearchDto;
import com.laywerspringboot.edition.entity.dto.UserSearchDto;
import com.laywerspringboot.edition.service.CasesService;
import com.laywerspringboot.edition.service.NewspaperService;
import com.laywerspringboot.edition.service.NoticeService;
import com.laywerspringboot.edition.service.SearchDtoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author:小七
 * @createTime:2020-10-31-10-25
 */
@CrossOrigin()
@RestController()
@RequestMapping("searchInfo")
@Slf4j
@Api(description = "搜索模块api",value = "搜索")
public class SearchController {
    @Resource
    private SearchDtoService searchDtoService;
    @Resource
    private CasesService casesService;
    @Resource
    private NoticeService noticeService;
    @Resource
    private NewspaperService newspaperService;
    /**
     * 先根据用户id，查询所能看的案号，通过案号搜索，返回案件信息
     * @param caseId
     * @param request
     * @return
     */
    @CrossOrigin()
    @GetMapping("/byCaseId/{caseId}")
    @ApiOperation(value = "法官根据案号查询自身处理过的案子")
    public R searchByCaseId(@ApiParam("通过案号搜索") @PathVariable("caseId")String caseId, HttpServletRequest request){
        String tokenRole = JWTUtils.getTokenRole(request);
        String tokenRealName = JWTUtils.getTokenRealName(request);

        //如果是法官
        if (tokenRole.equals("法官")){
            //返回对应的案件粗略信息
            SearchDto searchDto = searchDtoService.SearchByLaywerNameAndCaseID(tokenRealName,caseId);
         return searchDto == null? R.error("你无权查询次案件，请联系发布此案件的法官查询")
                 :R.searchOk("1").put("SearchDto",searchDto);
        }
        return R.error("你无权查询次案件，请联系发布此案件的法官查询");
    }
    @CrossOrigin()
    @GetMapping("/byRealname/{party}")
    @ApiOperation(value = "法官根据当事人姓名查询自身处理过的案子")
    public R searchByParty(@ApiParam("通过当事人姓名搜索") @PathVariable("party")String party, HttpServletRequest request) {
        String tokenRole = JWTUtils.getTokenRole(request);
        String tokenRealName = JWTUtils.getTokenRealName(request);

        //如果是法官
        if (tokenRole.equals("法官")){
            //返回对应的案件粗略信息
            SearchDto searchDto = searchDtoService.SearchByLaywerNameAndParty(tokenRealName,party);
            return searchDto == null? R.error("你无权查询次案件，请联系发布此案件的法官查询")
                    :R.searchOk("1").put("SearchDto",searchDto);
        }

        return R.error("你无权查询次案件，请联系发布此案件的法官查询");
    }

    @CrossOrigin()
    @GetMapping("/ByLawyer/{name}")
    @ApiOperation(value = "法官根据当事人姓名查询自身处理过的所有案子或者是当事人查询自身案件的详细信息")
    public R searchByLaywer(@ApiParam("通过法官或者当事人姓名搜索") @PathVariable("name")String name, HttpServletRequest request) {
        String tokenRole = JWTUtils.getTokenRole(request);
        String tokenRealName = JWTUtils.getTokenRealName(request);
        if (!name.equals(tokenRealName) ){
            return R.error("你的真实姓名输入有误");
        }
        //String tokenRole = "法官";
        //如果是法官
        if (tokenRole.equals("法官")){
            //返回对应的案件粗略信息

            List<SearchDto> searchDtos = searchDtoService.SearchByLaywerName(name);
            return searchDtos.size() == 0? R.error("未处理任何案件哦，加油")
                    :R.searchOk("1").put("SearchDto",searchDtos);

        }
        if (tokenRole.equals("用户")){
            List<SearchDto> searchDtos = searchDtoService.SearchByParty(name);
            return searchDtos.size() == 0? R.error("未碰见任何案件哦，很棒")
                    :R.searchOk("1").put("SearchDto",searchDtos);
        }

        return R.error("你无权访问哦");
    }

    @CrossOrigin()
    @GetMapping("/showDetail/{caseId}")
    @ApiOperation(value = "法官展示案件详情或者用户展示案件详情")
    public R searchShowDetail(@PathVariable("caseId")String caseId, HttpServletRequest request) {
        String tokenRole = JWTUtils.getTokenRole(request);
        String tokenRealName = JWTUtils.getTokenRealName(request);
        //String tokenRole = "法官";
        //如果是法官
        if (tokenRole.equals("法官")||tokenRole.equals("用户")){
            //返回对应的案件详细信息
            Cases cases = casesService.queryByCaseId(caseId);
            Notice notice = noticeService.queryByCaseAddress(caseId);
            Newspaper newspaper =  newspaperService.queryById(notice.getNId());
            SearchDetailDto searchDetailDto = DtoTransfer.transferToSearchDetailDto(cases, notice, newspaper);
            long currentTimeMillis = System.currentTimeMillis();
            long time = notice.getReleasetime().getTime();
            long add = newspaper.getDay() * 24 * 86400000;
            int res = (int) ((time + add - currentTimeMillis) / 3600000);
            searchDetailDto.setRemainingTime(res);
            searchDetailDto.setAddress(notice.getPicture());
            return R.searchOk("以下是详细信息").put("SearchDetai", searchDetailDto);
        }

        return R.error("你无权访问哦");
    }

    @CrossOrigin()
    @GetMapping("/byCaseIdAndName/{caseId}")
    @ApiOperation(value = "当事人根据案号查询自身处理过的案子")
    public R searchByCaseIdAndName(@ApiParam("通过案号搜索") @PathVariable("caseId")String caseId, HttpServletRequest request){

        String tokenRealName = JWTUtils.getTokenRealName(request);
        Cases cases = casesService.queryByCaseId(caseId);
        String tokenRole = JWTUtils.getTokenRole(request);
        //如果是本人
        if (cases.getParty().equals(tokenRealName)||cases.getLawyer().equals(tokenRealName)||cases.getAdmin().equals(tokenRealName)){
            //返回对应的案件粗略信息
            List<UserSearchDto> searchDto = searchDtoService.SearchByPartyAndCaseID(tokenRole,tokenRealName,caseId);
            return searchDto.size() == 0? R.error("你没有案件哦，请联系案件当事人查询")
                    :R.searchOk("1").put("SearchDto",searchDto);
        }
        return R.error("你无权查询次案件，请联系案件当事人查询");
    }

    @CrossOrigin()
    @GetMapping("/byParty/{party}")
    @ApiOperation(value = "当事人根据案号查询自身处理过的案子")
    public R searchByUserParty( @ApiParam("通过当事人真实姓名搜索") @PathVariable("party")String party,HttpServletRequest request){

        String tokenRealName = JWTUtils.getTokenRealName(request);
        Cases cases = casesService.queryByParty(tokenRealName);
        String tokenRole = JWTUtils.getTokenRole(request);
        //如果是本人
        if (cases.getParty().equals(tokenRealName)){
            //返回对应的案件粗略信息
            List<UserSearchDto> searchDto = searchDtoService.SearchByPartyAndCaseID(tokenRole,tokenRealName,cases.getCaseid());
            return searchDto.size() == 0? R.error("你没有案件哦，请联系案件当事人查询")
                    :R.searchOk("1").put("SearchDto",searchDto);
        }
        return R.error("你无权查询次案件，请联系案件当事人查询");
    }

    //todo 已解决，一个根据名字，根据名字查询searchByUserParty，一个根据案号searchByCaseIdAndName,


    @CrossOrigin()
    @GetMapping("/show")
    @ApiOperation(value = "展示")
    public R searchByUserParty(HttpServletRequest request){

        String tokenRole = JWTUtils.getTokenRole(request);
        String tokenRealName = JWTUtils.getTokenRealName(request);

        //String tokenRole = "法官";
        //如果是法官
        if (tokenRole.equals("法官")){
            //返回对应的案件粗略信息

            List<SearchDto> searchDtos = searchDtoService.SearchByLaywerName(tokenRealName);
            return searchDtos.size() == 0? R.error("未处理任何案件哦，加油")
                    :R.searchOk("1").put("SearchDto",searchDtos);

        }
        if (tokenRole.equals("用户")){
            List<SearchDto> searchDtos = searchDtoService.SearchByParty(tokenRealName);
            return searchDtos.size() == 0? R.error("未碰见任何案件哦，很棒")
                    :R.searchOk("1").put("SearchDto",searchDtos);
        }
        if (tokenRole.equals("报社")){
            //todo 要改成变为名字
            List<SearchDto> searchDtos = searchDtoService.SearchByParty(tokenRealName);
            return searchDtos.size() == 0? R.error("未碰见任何案件哦，很棒")
                    :R.searchOk("1").put("SearchDto",searchDtos);
        }
        return null;
    }




}

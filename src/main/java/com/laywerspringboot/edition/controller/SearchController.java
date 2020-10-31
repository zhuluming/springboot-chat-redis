package com.laywerspringboot.edition.controller;

import com.laywerspringboot.edition.Utils.JWTUtils;
import com.laywerspringboot.edition.Utils.R;
import com.laywerspringboot.edition.entity.Role;
import com.laywerspringboot.edition.entity.User;
import com.laywerspringboot.edition.entity.Userrole;
import com.laywerspringboot.edition.entity.dto.SearchDto;
import com.laywerspringboot.edition.service.RoleService;
import com.laywerspringboot.edition.service.UserService;
import com.laywerspringboot.edition.service.UserroleService;
import com.laywerspringboot.edition.service.impl.SearchDtoServiceImpl;
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
@RestController()
@RequestMapping("searchInfo")
@CrossOrigin()
@Slf4j
@Api(description = "搜索模块api",value = "搜索")
public class SearchController {
    @Resource
    private SearchDtoServiceImpl searchDtoService;
    @Resource
    private UserroleService userroleService;
    @Resource
    private RoleService roleService;
    @Resource
    private UserService userService;
    /**
     * 先根据用户id，查询所能看的案号，通过案号搜索，返回案件信息
     * @param caseId
     * @param request
     * @return
     */
    @GetMapping("/byCaseId/{caseId}")
    @ApiOperation(value = "法官根据案号查询自身处理过的案子")
    public R searchByCaseId(@ApiParam("通过案号搜索") @PathVariable("caseId")String caseId, HttpServletRequest request){
        Integer tokenId = JWTUtils.getTokenId(request);
        User user = userService.queryById(tokenId);
        Userrole userrole = userroleService.queryById(tokenId);
        Role role = roleService.queryById(userrole.getRId());
        //如果是法官
        if (role.getRolename().equals("法官")){
            //返回对应的案件粗略信息
            //TODO redis中查
            SearchDto searchDto = searchDtoService.SearchByLaywerNameAndCaseID(user.getRealname(),caseId);
         return searchDto == null? R.error("你无权查询次案件，请联系发布此案件的法官查询")
                 :R.searchOk("1").put("SearchDto",searchDto);

        }

        return R.error("你无权查询次案件，请联系发布此案件的法官查询");
    }
    @RequestMapping("/byRealname/{party}")
    @ApiOperation(value = "法官根据当事人姓名查询自身处理过的案子")
    public R searchByParty(@ApiParam("通过当事人姓名搜索") @PathVariable("party")String party, HttpServletRequest request) {
        Integer tokenId = JWTUtils.getTokenId(request);
        User user = userService.queryById(tokenId);
        Userrole userrole = userroleService.queryById(tokenId);
        Role role = roleService.queryById(userrole.getRId());
        //如果是法官
        if (role.getRolename().equals("法官")){
            //返回对应的案件粗略信息
            //TODO redis中查
            SearchDto searchDto = searchDtoService.SearchByLaywerNameAndParty(user.getRealname(),party);
            return searchDto == null? R.error("你无权查询次案件，请联系发布此案件的法官查询")
                    :R.searchOk("1").put("SearchDto",searchDto);
        }

        return R.error("你无权查询次案件，请联系发布此案件的法官查询");
    }

    @RequestMapping("/ByLawyer/{layWer}")
    @ApiOperation(value = "法官根据当事人姓名查询自身处理过的所有案子")
    public R searchByLaywer(@ApiParam("通过法官姓名搜索") @PathVariable("layWer")String layWer, HttpServletRequest request) {
        Integer tokenId = JWTUtils.getTokenId(request);
        User user = userService.queryById(tokenId);
        Userrole userrole = userroleService.queryById(tokenId);
        Role role = roleService.queryById(userrole.getRId());
        if (!layWer.equals(user.getRealname()) ){
            return R.error("你的真实姓名输入有误");
        }
        //如果是法官
        if (role.getRolename().equals("法官")){
            //返回对应的案件粗略信息
            //TODO redis中查
            List<SearchDto> searchDtos = searchDtoService.SearchByLaywerName(layWer);
            return searchDtos.size() == 0? R.error("未处理任何案件哦，加油")
                    :R.searchOk("1").put("SearchDto",searchDtos);

        }

        return R.error("你无权访问哦");
    }

    @RequestMapping("/showDetail")
    @ApiOperation(value = "法官展示案件详情")
    public R searchShowDetail( HttpServletRequest request) {
        return null;
    }

}

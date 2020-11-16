package com.laywerspringboot.edition.controller;

import com.laywerspringboot.edition.Utils.DtoTransfer;
import com.laywerspringboot.edition.Utils.JWTUtils;
import com.laywerspringboot.edition.Utils.R;
import com.laywerspringboot.edition.entity.Newspaper;
import com.laywerspringboot.edition.entity.Notice;
import com.laywerspringboot.edition.entity.Prices;
import com.laywerspringboot.edition.entity.dto.UploadCaseNoticePriceDto;
import com.laywerspringboot.edition.service.CasesService;
import com.laywerspringboot.edition.service.NewspaperService;
import com.laywerspringboot.edition.service.NoticeService;
import com.laywerspringboot.edition.service.PricesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author:小七
 * @createTime:2020-10-31-17-37
 */
@CrossOrigin()
@RestController()
@RequestMapping("lawyer")
@Slf4j
@Api(description = "法官上传公告",value = "上传")
public class LayWerUpload {
    @Resource
    private CasesService casesService;
    @Resource
    private NoticeService noticeService;
    @Resource
    private NewspaperService newspaperService;
    @Resource
    private PricesService pricesService;
    /**
     * 查询案号是否
     * @param caseID
     * @param request
     * @return
     */
    @CrossOrigin()
    @GetMapping("/noticeUpload/{caseId}")
    @ApiOperation(value = "ajax判断案号是否存在")
    public R searchShowDetail(@PathVariable("caseId") String caseID, HttpServletRequest request) {
        JWTUtils.verify(request.getHeader("token"));
        return casesService.queryByCaseId(caseID) == null?R.searchOk("可以用"):R.error("已存在");
    }

    @CrossOrigin()
    @PostMapping("/noticeUpload")
    @ApiOperation(value = "发布公告")
    public R noticeUpload(@RequestBody UploadCaseNoticePriceDto upDto, HttpServletRequest request){
        String tokenRole = JWTUtils.getTokenRole(request);
        String tokenRealName = JWTUtils.getTokenRealName(request);
        //todo 已解决 发布公告时候案件id没入库，案号没入库是对的，但是不应该让重复更改
        //如果是法官
        if (tokenRole.equals("法官")){
            upDto.setLawyer(tokenRealName);
            //先插入case表
            casesService.insert(DtoTransfer.transferUpDtoToCases(upDto));
            //再插入notice
            Notice notice = noticeService.insert(DtoTransfer.transferUpDtoToNotice(upDto));
            //最后用notice的主键插入newspaper
            Newspaper newspaper = DtoTransfer.transferUpDtoToNewspaper(upDto);
            newspaper.setPId(notice.getNId());

            Prices prices = new Prices();
            prices.setCaseid(upDto.getCaseid());
                pricesService.insert(prices);


            //System.out.println(newspaper.getPId());
           return newspaperService.insert(newspaper) == null? R.error("请重试"):R.updateOk("成功上传") ;
        }
        return R.error("你的身份有误");
    }



}

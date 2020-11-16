package com.laywerspringboot.edition.controller;

import com.laywerspringboot.edition.Utils.JWTUtils;
import com.laywerspringboot.edition.Utils.R;
import com.laywerspringboot.edition.entity.Notice;
import com.laywerspringboot.edition.entity.Prices;
import com.laywerspringboot.edition.service.CasesService;
import com.laywerspringboot.edition.service.NewspaperService;
import com.laywerspringboot.edition.service.NoticeService;
import com.laywerspringboot.edition.service.PricesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author:小七
 * @createTime:2020-11-13-08-25
 */
@CrossOrigin
@RestController
@RequestMapping("admin")
public class CheckController {
    @Resource
    private CasesService casesService;
    @Resource
    private NoticeService noticeService;
    @Resource
    private NewspaperService newspaperService;
    @Resource
    private PricesService pricesService;
    /**
     * 先查支付状态
     * 传案号，然后把notice表中对应的finished改为1
     * @param caseId
     * @param request
     * @return
     */
    @RequestMapping("/update/{caseId}")
    @ApiOperation(value = "更改公告状态，把已发布改成未发布")
    public R checker(@ApiParam("案号")@PathVariable("caseId") String caseId, HttpServletRequest request){
        String tokenRole = JWTUtils.getTokenRole(request);
        if (tokenRole.equals("报社")){
            //todo  已解决，初步认为SQL语句问题,把公告状态改为发布
            Prices prices = pricesService.queryByCaseId(caseId);
            if (prices.getState().equals("1")){
                Notice notice = noticeService.queryByCaseAddress(caseId);
                notice.setFinished("1");
                noticeService.update(notice);
                return R.isOk(1, "已发布");
            }
        }
        return R.error("你无权发布");
    }


}

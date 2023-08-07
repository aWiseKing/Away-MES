package com.awise.file.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.away.common.annotation.Log;
import com.away.common.core.controller.BaseController;
import com.away.common.core.domain.AjaxResult;
import com.away.common.enums.BusinessType;
import com.away.common.utils.poi.ExcelUtil;
import com.away.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/awise/file")
public class AwFileController {


    /**
     * 查询客户信息列表
     */
    @PreAuthorize("@ss.hasPermi('awise:file:update')")
    @PostMapping("/update")
    public int update(@RequestParam("file") MultipartFile file)
    {

        return 1;
    }
}

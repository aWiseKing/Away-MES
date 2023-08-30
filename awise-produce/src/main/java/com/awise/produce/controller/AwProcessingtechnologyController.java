package com.awise.produce.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.away.common.annotation.Log;
import com.away.common.core.controller.BaseController;
import com.away.common.core.domain.AjaxResult;
import com.away.common.enums.BusinessType;
import com.awise.produce.domain.AwProcessingtechnology;
import com.awise.produce.service.IAwProcessingtechnologyService;
import com.away.common.utils.poi.ExcelUtil;
import com.away.common.core.page.TableDataInfo;

/**
 * 加工工艺信息Controller
 * 
 * @author awise
 * @date 2023-08-21
 */
@RestController
@RequestMapping("/produce/processingtechnology")
public class AwProcessingtechnologyController extends BaseController
{
    @Autowired
    private IAwProcessingtechnologyService awProcessingtechnologyService;

    /**
     * 查询加工工艺信息列表
     */
    @PreAuthorize("@ss.hasPermi('produce:processingtechnology:list')")
    @GetMapping("/list")
    public TableDataInfo list(AwProcessingtechnology awProcessingtechnology)
    {
        startPage();
        List<AwProcessingtechnology> list = awProcessingtechnologyService.selectAwProcessingtechnologyList(awProcessingtechnology);
        return getDataTable(list);
    }

    /**
     * 获取生产任务对应加工工艺信息
     * @return
     */
    @PreAuthorize("@ss.hasPermi('produce:processingtechnology:query')")
    @GetMapping(value = "/productionTasks")
    public AjaxResult getByProductionTasksIDInfo(@RequestParam("productionTasksID") String productionTasksID)
    {
        AwProcessingtechnology awProcessingtechnology = awProcessingtechnologyService.selectAwProcessingtechnologyByProductionTasksID(productionTasksID);
        AjaxResult ajaxResult = success();
        if(awProcessingtechnology == null){
            ajaxResult.put("data","null");
        }else {
            ajaxResult.put("data",awProcessingtechnology);
        }
        return ajaxResult;
    }

    /**
     * 导出加工工艺信息列表
     */
    @PreAuthorize("@ss.hasPermi('produce:processingtechnology:export')")
    @Log(title = "加工工艺信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AwProcessingtechnology awProcessingtechnology)
    {
        List<AwProcessingtechnology> list = awProcessingtechnologyService.selectAwProcessingtechnologyList(awProcessingtechnology);
        ExcelUtil<AwProcessingtechnology> util = new ExcelUtil<AwProcessingtechnology>(AwProcessingtechnology.class);
        util.exportExcel(response, list, "加工工艺信息数据");
    }

    /**
     * 获取加工工艺信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('produce:processingtechnology:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(awProcessingtechnologyService.selectAwProcessingtechnologyById(id));
    }

    /**
     * 新增加工工艺信息
     */
    @PreAuthorize("@ss.hasPermi('produce:processingtechnology:add')")
    @Log(title = "加工工艺信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AwProcessingtechnology awProcessingtechnology)
    {
        return toAjax(awProcessingtechnologyService.insertAwProcessingtechnology(awProcessingtechnology));
    }

    /**
     * 修改加工工艺信息
     */
    @PreAuthorize("@ss.hasPermi('produce:processingtechnology:edit')")
    @Log(title = "加工工艺信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AwProcessingtechnology awProcessingtechnology)
    {
        return toAjax(awProcessingtechnologyService.updateAwProcessingtechnology(awProcessingtechnology));
    }

    /**
     * 删除加工工艺信息
     */
    @PreAuthorize("@ss.hasPermi('produce:processingtechnology:remove')")
    @Log(title = "加工工艺信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(awProcessingtechnologyService.deleteAwProcessingtechnologyByIds(ids));
    }
}
